package org.on1ks.remanga.api

import java.nio.file.Paths
import java.text.Collator
import java.util.Locale

object ApiExtractor {
    private val escapedReleaseRegex = Regex("""\\\"GIT_HASH\\\":\\\"([^\\\"]+)\\\"""")
    private val plainReleaseRegex = Regex(""""GIT_HASH":"([^"]+)"""")
    private val scriptRegex = Regex("""/_next/static/[^"' ]+\.js""")
    private val urlRegex = Regex("""url:"(?<path>/api/[^"?#]+)"""")
    private val methodRegex = Regex("""\.(?<method>get|post|put|patch|delete)\(""")
    private val contentTypeRegex = Regex("""Content-Type":"([^"]+)""")
    private val pathParameterRegex = Regex("""\{([^}]+)}""")

    fun pageMetadata(html: String): PageMetadata {
        val release = escapedReleaseRegex.find(html)?.groupValues?.get(1)
            ?: plainReleaseRegex.find(html)?.groupValues?.get(1)
            ?: "unknown"
        val scripts = scriptRegex.findAll(html).map { it.value }.distinct().sorted().toList()
        if (scripts.isEmpty()) throw ExtractionException("На странице не найдены JavaScript-сборки")
        return PageMetadata(release, scripts)
    }

    fun endpoints(bundles: List<BundleSource>): List<Endpoint> {
        val matches = mutableListOf<Endpoint>()
        for (bundle in bundles.sortedBy { it.path }) {
            for (urlMatch in urlRegex.findAll(bundle.javascript)) {
                val contextStart = maxOf(0, urlMatch.range.first - 900)
                val prefix = bundle.javascript.substring(contextStart, urlMatch.range.first)
                val method = methodRegex.findAll(prefix).lastOrNull()?.groups?.get("method")?.value?.uppercase()
                    ?: continue
                val snippetEnd = minOf(bundle.javascript.length, urlMatch.range.first + 450)
                val snippet = bundle.javascript.substring(contextStart, snippetEnd)
                val path = urlMatch.groups["path"]!!.value
                val contentType = if (method in setOf("POST", "PUT", "PATCH")) {
                    contentTypeRegex.find(snippet)?.groupValues?.get(1)
                } else null
                matches += Endpoint(
                    method = method,
                    path = path,
                    group = groupFor(path),
                    pathParameters = pathParameterRegex.findAll(path).map { it.groupValues[1] }.distinct().toList(),
                    bearerCapable = "scheme:\"bearer\"" in snippet,
                    contentType = contentType,
                    sourceBundles = listOf(Paths.get(bundle.path).fileName.toString()),
                )
            }
        }

        val collator = Collator.getInstance(Locale.forLanguageTag("ru-RU"))
        val endpointComparator = Comparator<Endpoint> { left, right ->
            collator.compare(left.group, right.group).takeIf { it != 0 }
                ?: collator.compare(left.path, right.path).takeIf { it != 0 }
                ?: collator.compare(left.method, right.method)
        }
        val deduplicated = matches.groupBy { it.method to it.path }.values.map { group ->
            val first = group.first()
            first.copy(sourceBundles = group.flatMap { it.sourceBundles }.distinct().sorted())
        }.sortedWith(endpointComparator)

        if (deduplicated.isEmpty()) throw ExtractionException("Не удалось извлечь ни одного маршрута API")
        return deduplicated
    }

    internal fun groupFor(path: String): String {
        val parts = path.trim('/').split('/')
        if (parts.size < 2) return "other"
        var index = 1
        if (parts[index].matches(Regex("v\\d+"))) index++
        return parts.getOrNull(index) ?: "other"
    }
}
