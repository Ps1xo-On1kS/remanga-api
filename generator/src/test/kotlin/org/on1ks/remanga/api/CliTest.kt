package org.on1ks.remanga.api

import kotlin.test.Test
import kotlin.test.assertEquals

class CliTest {
    @Test fun `help succeeds`() = assertEquals(0, runCli(listOf("--help")))
    @Test fun `version succeeds`() = assertEquals(0, runCli(listOf("--version")))
    @Test fun `unknown argument fails`() = assertEquals(2, runCli(listOf("--unknown")))
}
