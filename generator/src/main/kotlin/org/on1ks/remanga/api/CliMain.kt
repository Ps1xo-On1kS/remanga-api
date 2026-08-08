package org.on1ks.remanga.api

import java.io.PrintStream
import java.nio.charset.StandardCharsets
import kotlin.system.exitProcess

object CliMain {
    @JvmStatic
    fun main(args: Array<String>) {
        if (System.getenv("GITHUB_ACTIONS").equals("true", ignoreCase = true)) {
            System.setOut(PrintStream(System.out, true, StandardCharsets.UTF_8))
            System.setErr(PrintStream(System.err, true, StandardCharsets.UTF_8))
        }
        exitProcess(runCli(args.toList()))
    }
}
