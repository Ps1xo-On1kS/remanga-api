package org.on1ks.remanga.api

import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.file.AtomicMoveNotSupportedException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Comparator
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean

private val displayTimestampFormat = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy").withZone(ZoneId.of("Europe/Moscow"))

internal fun formatGeneratedAt(instant: Instant): String = displayTimestampFormat.format(instant)

class ApiGenerator(
    private val client: HttpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(90)).build(),
) {
    private val timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'").withZone(ZoneOffset.UTC)

    fun generate(
        pageUrl: String,
        outputDirectory: Path,
        listener: ProgressListener = ProgressListener { _, _ -> },
        cancelled: AtomicBoolean = AtomicBoolean(false),
    ): ApiSnapshot {
        val pageUri = try { URI.create(pageUrl) } catch (error: IllegalArgumentException) {
            throw IllegalArgumentException("Некорректный адрес страницы: $pageUrl", error)
        }
        if (pageUri.scheme !in setOf("http", "https") || pageUri.host.isNullOrBlank()) {
            throw IllegalArgumentException("Поддерживаются только адреса HTTP и HTTPS")
        }
        checkCancelled(cancelled)
        listener.update(2, "Чтение $pageUrl")
        val html = getText(pageUri, cancelled)
        val metadata = ApiExtractor.pageMetadata(html)
        listener.update(5, "Frontend ${metadata.release}; JavaScript-сборок: ${metadata.scriptPaths.size}")

        val origin = URI("${pageUri.scheme}://${pageUri.authority}")
        val executor = Executors.newFixedThreadPool(4)
        val completed = java.util.concurrent.atomic.AtomicInteger(0)
        val progressLock = Any()
        val bundles = try {
            val futures = metadata.scriptPaths.map { path ->
                executor.submit(Callable {
                    checkCancelled(cancelled)
                    val source = BundleSource(path, getText(origin.resolve(path), cancelled))
                    synchronized(progressLock) {
                        val count = completed.incrementAndGet()
                        listener.update(5 + count * 70 / metadata.scriptPaths.size, "Загружено сборок: $count / ${metadata.scriptPaths.size}")
                    }
                    source
                })
            }
            futures.mapNotNull { future ->
                try { future.get() } catch (error: Exception) {
                    val cause = error.cause ?: error
                    if (cause is GenerationCancelledException) throw cause
                    listener.update(5 + completed.get() * 70 / metadata.scriptPaths.size, "Пропущена недоступная JavaScript-сборка: ${cause.message}")
                    null
                }
            }
        } finally {
            executor.shutdownNow()
        }
        checkCancelled(cancelled)
        listener.update(80, "Извлечение маршрутов API")
        val endpoints = ApiExtractor.endpoints(bundles)
        val generatedAt = Instant.now()
        val snapshot = ApiSnapshot(
            generatedAtUtc = timestampFormat.format(generatedAt),
            generatedAt = formatGeneratedAt(generatedAt),
            sourcePage = pageUrl,
            frontendRelease = metadata.release,
            endpointCount = endpoints.size,
            endpoints = endpoints,
        )
        listener.update(90, "Подготовка файлов документации")
        writeAtomically(outputDirectory, DocumentRenderer.render(snapshot), cancelled)
        listener.update(100, "Готово. Извлечено маршрутов: ${endpoints.size}")
        return snapshot
    }

    private fun getText(uri: URI, cancelled: AtomicBoolean): String {
        var lastError: Throwable? = null
        repeat(3) { attempt ->
            checkCancelled(cancelled)
            try {
                val request = HttpRequest.newBuilder(uri)
                    .timeout(Duration.ofSeconds(90))
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) ReMangaApiDocGenerator/$GENERATOR_VERSION")
                    .GET().build()
                val response = client.send(request, HttpResponse.BodyHandlers.ofString())
                if (response.statusCode() in 200..299) return response.body()
                lastError = IOException("HTTP ${response.statusCode()} для $uri")
            } catch (error: InterruptedException) {
                Thread.currentThread().interrupt()
                throw GenerationCancelledException()
            } catch (error: IOException) {
                lastError = error
            }
            if (attempt < 2) {
                try { Thread.sleep((1L shl attempt) * 1000L) } catch (_: InterruptedException) {
                    Thread.currentThread().interrupt()
                    throw GenerationCancelledException()
                }
            }
        }
        throw NetworkException("Не удалось загрузить $uri после трёх попыток", lastError)
    }

    private fun writeAtomically(output: Path, files: Map<String, ByteArray>, cancelled: AtomicBoolean) {
        try {
            Files.createDirectories(output)
            val normalizedOutput = output.toAbsolutePath().normalize()
            val temporary = Files.createTempDirectory(normalizedOutput, ".remanga-api-gen-")
            try {
                val staging = Files.createDirectory(temporary.resolve("staging"))
                val backup = Files.createDirectory(temporary.resolve("backup"))
                files.forEach { (name, bytes) ->
                    checkCancelled(cancelled)
                    Files.write(staging.resolve(name), bytes)
                }
                files.keys.forEach { name ->
                    val destination = normalizedOutput.resolve(name)
                    if (Files.exists(destination)) Files.copy(destination, backup.resolve(name), StandardCopyOption.REPLACE_EXISTING)
                }
                val replaced = mutableListOf<String>()
                try {
                    files.keys.forEach { name ->
                        checkCancelled(cancelled)
                        moveReplacing(staging.resolve(name), normalizedOutput.resolve(name))
                        replaced += name
                    }
                } catch (error: Exception) {
                    replaced.asReversed().forEach { name ->
                        val previous = backup.resolve(name)
                        val destination = normalizedOutput.resolve(name)
                        if (Files.exists(previous)) moveReplacing(previous, destination) else Files.deleteIfExists(destination)
                    }
                    throw error
                }
            } finally {
                if (temporary.startsWith(normalizedOutput) && Files.exists(temporary)) {
                    Files.walk(temporary).sorted(Comparator.reverseOrder()).forEach(Files::deleteIfExists)
                }
            }
        } catch (error: GenerationCancelledException) {
            throw error
        } catch (error: Exception) {
            throw OutputException("Не удалось записать документацию в $output", error)
        }
    }

    private fun moveReplacing(source: Path, destination: Path) {
        try {
            Files.move(source, destination, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING)
        } catch (_: AtomicMoveNotSupportedException) {
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING)
        }
    }

    private fun checkCancelled(cancelled: AtomicBoolean) {
        if (cancelled.get() || Thread.currentThread().isInterrupted) throw GenerationCancelledException()
    }
}
