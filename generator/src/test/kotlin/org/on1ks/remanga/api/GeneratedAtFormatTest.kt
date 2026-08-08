package org.on1ks.remanga.api

import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

class GeneratedAtFormatTest {
    @Test
    fun `formats time in Moscow using the documentation format`() {
        assertEquals("10:30 01.08.2026", formatGeneratedAt(Instant.parse("2026-08-01T07:30:00Z")))
    }
}
