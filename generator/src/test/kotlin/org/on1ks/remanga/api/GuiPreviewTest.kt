package org.on1ks.remanga.api

import java.awt.GraphicsEnvironment
import java.awt.Container
import java.awt.image.BufferedImage
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO
import javax.swing.JTextArea
import javax.swing.SwingUtilities
import kotlin.test.Test
import kotlin.test.assertTrue

class GuiPreviewTest {
    @Test
    fun `renders GUI preview for visual verification`() {
        if (GraphicsEnvironment.isHeadless()) return
        SwingUtilities.invokeAndWait {
            val window = GeneratorWindow()
            window.setSize(860, 590)
            findTextArea(window.contentPane)?.text = (1..30).joinToString("\n") {
                "[$it] Проверка фирменной полосы прокрутки Re:Manga API"
            }
            render(window.contentPane, Path.of("build", "ui-preview.png"))
            window.dispose()

            val splash = GeneratorSplash()
            render(splash.contentPane, Path.of("build", "splash-preview.png"))
            splash.dispose()
        }
    }

    private fun render(container: Container, output: Path) {
        container.doLayout()
        layoutTree(container)
        val image = BufferedImage(container.width, container.height, BufferedImage.TYPE_INT_ARGB)
        val graphics = image.createGraphics()
        container.paint(graphics)
        graphics.dispose()
        Files.createDirectories(output.parent)
        assertTrue(ImageIO.write(image, "png", output.toFile()))
    }

    private fun layoutTree(container: Container) {
        container.doLayout()
        container.components.filterIsInstance<Container>().forEach(::layoutTree)
    }

    private fun findTextArea(container: Container): JTextArea? {
        container.components.forEach { component ->
            if (component is JTextArea) return component
            if (component is Container) findTextArea(component)?.let { return it }
        }
        return null
    }
}
