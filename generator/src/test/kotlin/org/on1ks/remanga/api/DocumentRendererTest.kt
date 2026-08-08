package org.on1ks.remanga.api

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DocumentRendererTest {
    private val snapshot = ApiSnapshot(
        generatedAtUtc = "2026-08-01T00:00:00Z",
        generatedAt = "03:00 01.08.2026",
        sourcePage = "https://remanga.org/card",
        frontendRelease = "test",
        endpointCount = 1,
        endpoints = listOf(Endpoint("GET", "/api/search/", "search", emptyList(), false, null, listOf("test.js"))),
    )

    @Test
    fun `renders all expected files with stable encodings`() {
        val files = DocumentRenderer.render(snapshot)
        assertEquals(setOf("endpoints.json", "endpoints.csv", "snapshot-summary.json", "API_REFERENCE.md"), files.keys)
        assertEquals(listOf(0xEF, 0xBB, 0xBF), files.getValue("endpoints.csv").take(3).map { it.toInt() and 0xFF })
        assertTrue(files.getValue("endpoints.csv").toString(Charsets.UTF_8).contains("\r\n"))
        assertFalse(files.getValue("endpoints.json").toString(Charsets.UTF_8).contains("\r\n"))
        assertTrue(files.getValue("endpoints.json").toString(Charsets.UTF_8).contains("\"generated_at\": \"03:00 01.08.2026\""))
        assertTrue(files.getValue("snapshot-summary.json").toString(Charsets.UTF_8).contains("\"generated_at\": \"03:00 01.08.2026\""))
        assertTrue(files.getValue("API_REFERENCE.md").toString(Charsets.UTF_8).contains("- Дата генерации: `03:00 01.08.2026`"))
        assertTrue(files.getValue("API_REFERENCE.md").toString(Charsets.UTF_8).contains("сайта [ReManga](https://remanga.org)."))
        assertTrue(files.getValue("API_REFERENCE.md").toString(Charsets.UTF_8).contains("## Поиск - `search` (1)"))
    }
}
