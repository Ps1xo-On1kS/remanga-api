package org.on1ks.remanga.api

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.nio.file.Files
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class ApiGeneratorTest {
    @Test
    fun `retries get requests and writes complete snapshot`() {
        val requests = AtomicInteger()
        val server = HttpServer.create(InetSocketAddress("127.0.0.1", 0), 0)
        server.createContext("/card") { exchange ->
            respond(exchange, 200, """{\"GIT_HASH\":\"fixture\"}<script src="/_next/static/app.js"></script>""")
        }
        server.createContext("/_next/static/app.js") { exchange ->
            val attempt = requests.incrementAndGet()
            if (attempt < 3) respond(exchange, 503, "retry")
            else respond(exchange, 200, """client.get({url:"/api/search/"})""")
        }
        server.start()
        val output = Files.createTempDirectory("remanga-api-generator-test-")
        try {
            val snapshot = ApiGenerator().generate("http://127.0.0.1:${server.address.port}/card", output)
            assertEquals(3, requests.get())
            assertEquals(1, snapshot.endpointCount)
            assertTrue(listOf("API_REFERENCE.md", "endpoints.json", "endpoints.csv", "snapshot-summary.json").all { Files.exists(output.resolve(it)) })
        } finally {
            server.stop(0)
            Files.walk(output).sorted(Comparator.reverseOrder()).forEach(Files::deleteIfExists)
        }
    }

    @Test
    fun `cancelled generation does not create output`() {
        val output = Files.createTempDirectory("remanga-api-generator-cancel-")
        try {
            assertFailsWith<GenerationCancelledException> {
                ApiGenerator().generate("https://remanga.org/card", output, cancelled = AtomicBoolean(true))
            }
            assertEquals(0, Files.list(output).use { it.count() })
        } finally {
            Files.deleteIfExists(output)
        }
    }

    private fun respond(exchange: com.sun.net.httpserver.HttpExchange, status: Int, body: String) {
        val bytes = body.toByteArray()
        exchange.sendResponseHeaders(status, bytes.size.toLong())
        exchange.responseBody.use { it.write(bytes) }
    }
}
