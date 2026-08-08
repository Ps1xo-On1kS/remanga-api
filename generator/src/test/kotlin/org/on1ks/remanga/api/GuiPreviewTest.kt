package org.on1ks.remanga.api

import java.awt.GraphicsEnvironment
import java.awt.Container
import java.awt.image.BufferedImage
import java.nio.file.Files
import java.nio.file.Path
import javax.imageio.ImageIO
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
            window.doLayout()
            layoutTree(window.contentPane)

            val image = BufferedImage(window.contentPane.width, window.contentPane.height, BufferedImage.TYPE_INT_ARGB)
            val graphics = image.createGraphics()
            window.contentPane.paint(graphics)
            graphics.dispose()

            val output = Path.of("build", "ui-preview.png")
            Files.createDirectories(output.parent)
            assertTrue(ImageIO.write(image, "png", output.toFile()))
            window.dispose()
        }
    }

    private fun layoutTree(container: Container) {
        container.doLayout()
        container.components.filterIsInstance<Container>().forEach(::layoutTree)
    }
}
