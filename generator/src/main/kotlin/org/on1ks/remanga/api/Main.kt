package org.on1ks.remanga.api

import java.awt.AlphaComposite
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Cursor
import java.awt.Desktop
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Image
import java.awt.RenderingHints
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JProgressBar
import javax.swing.JScrollPane
import javax.swing.JTextArea
import javax.swing.JTextField
import javax.swing.SwingConstants
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.border.EmptyBorder

const val GENERATOR_VERSION = "0.2.1"
private const val DEFAULT_PAGE = "https://remanga.org/card"

fun main(args: Array<String>) {
    SwingUtilities.invokeLater {
        runCatching { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()) }
        GeneratorWindow().isVisible = true
    }
}

internal fun runCli(args: List<String>): Int {
    if ("--help" in args) {
        println("Использование: remanga_api_gen_cli.exe --page-url <URL> --output <папка>")
        println("Параметры: --help, --version")
        return 0
    }
    if ("--version" in args) {
        println("remanga_api_gen $GENERATOR_VERSION")
        return 0
    }
    var pageUrl = DEFAULT_PAGE
    var output: Path = Paths.get(".")
    var index = 0
    try {
        while (index < args.size) {
            when (args[index]) {
                "--page-url" -> pageUrl = args.getOrNull(++index) ?: return cliError("Не указано значение --page-url")
                "--output" -> output = Paths.get(args.getOrNull(++index) ?: return cliError("Не указано значение --output"))
                else -> return cliError("Неизвестный параметр: ${args[index]}")
            }
            index++
        }
        ApiGenerator().generate(pageUrl, output, ProgressListener { percent, message -> println("[$percent%] $message") })
        return 0
    } catch (error: IllegalArgumentException) {
        return cliError(error.message ?: "Некорректные аргументы")
    } catch (error: NetworkException) {
        System.err.println("Ошибка сети: ${error.message}")
        return 3
    } catch (error: ExtractionException) {
        System.err.println("Ошибка извлечения: ${error.message}")
        return 4
    } catch (error: OutputException) {
        System.err.println("Ошибка записи: ${error.message}")
        return 5
    } catch (_: GenerationCancelledException) {
        System.err.println("Генерация отменена")
        return 130
    } catch (error: Exception) {
        System.err.println("Непредвиденная ошибка: ${error.message}")
        return 4
    }
}

private fun cliError(message: String): Int {
    System.err.println(message)
    System.err.println("Используйте --help для справки")
    return 2
}

private object SiteColors {
    val background = Color(16, 18, 20)
    val surface = Color(22, 24, 27)
    val surfaceHover = Color(28, 31, 35)
    val border = Color(38, 41, 46)
    val text = Color(244, 244, 245)
    val secondary = Color(164, 167, 173)
    val accent = Color(55, 145, 245)
    val accentHover = Color(78, 160, 250)
    val danger = Color(235, 99, 111)
}

private class RoundedSurface(
    private val radius: Int,
    fill: Color,
    private val outline: Color? = null,
) : JPanel() {
    init {
        isOpaque = false
        background = fill
    }

    override fun paintComponent(graphics: Graphics) {
        val g = graphics.create() as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.color = background
        g.fillRoundRect(0, 0, width - 1, height - 1, radius, radius)
        outline?.let {
            g.color = it
            g.drawRoundRect(0, 0, width - 1, height - 1, radius, radius)
        }
        g.dispose()
        super.paintComponent(graphics)
    }
}

private class PillButton(
    text: String,
    private val normal: Color,
    private val hovered: Color,
    foregroundColor: Color = SiteColors.text,
) : JButton(text) {
    private var isHovered = false

    init {
        isOpaque = false
        isContentAreaFilled = false
        isBorderPainted = false
        isFocusPainted = false
        foreground = foregroundColor
        font = Font("Segoe UI", Font.BOLD, 13)
        border = EmptyBorder(10, 18, 10, 18)
        cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(event: MouseEvent) { isHovered = true; repaint() }
            override fun mouseExited(event: MouseEvent) { isHovered = false; repaint() }
        })
    }

    override fun paintComponent(graphics: Graphics) {
        val g = graphics.create() as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        val fill = when {
            !isEnabled -> normal.darker()
            isHovered -> hovered
            else -> normal
        }
        g.color = fill
        g.fillRoundRect(0, 0, width, height, height, height)
        g.dispose()
        super.paintComponent(graphics)
    }
}

private class SlimProgressBar : JProgressBar(0, 100) {
    init {
        isOpaque = false
        isBorderPainted = false
        preferredSize = Dimension(100, 7)
    }

    override fun paintComponent(graphics: Graphics) {
        val g = graphics.create() as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.color = SiteColors.surface
        g.fillRoundRect(0, 0, width, height, height, height)
        val filledWidth = (width * value / maximum.toDouble()).toInt()
        if (filledWidth > 0) {
            g.color = SiteColors.accent
            g.fillRoundRect(0, 0, filledWidth, height, height, height)
        }
        g.dispose()
    }
}

internal class GeneratorWindow : JFrame("ReManga API · Генератор документации") {
    private val outputField = JTextField(Paths.get(".").toAbsolutePath().normalize().toString())
    private val progress = SlimProgressBar()
    private val progressPercent = JLabel("0 %")
    private val status = JLabel("Готов к сборке")
    private val log = JTextArea()
    private val start = PillButton("Собрать", SiteColors.accent, SiteColors.accentHover, Color.WHITE)
    private val cancel = PillButton("Отменить", SiteColors.surface, SiteColors.surfaceHover, SiteColors.danger).apply { isEnabled = false }
    private val open = PillButton("Открыть папку", SiteColors.surface, SiteColors.surfaceHover)
    private val choose = PillButton("Выбрать", SiteColors.surfaceHover, Color(36, 40, 45))
    private val executor = Executors.newSingleThreadExecutor()
    private var cancelled = AtomicBoolean(false)

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        minimumSize = Dimension(820, 560)
        preferredSize = Dimension(860, 590)
        background = SiteColors.background
        iconImage = javaClass.getResource("/icons/remanga-official.png")?.let { ImageIcon(it).image }
        contentPane = root()

        start.addActionListener { startGeneration() }
        cancel.addActionListener {
            cancelled.set(true)
            status.text = "Отменяем…"
            appendLog("Запрошена отмена…")
        }
        open.addActionListener { openOutput() }
        choose.addActionListener { chooseOutput() }

        pack()
        setLocationRelativeTo(null)
    }

    private fun root() = JPanel(BorderLayout(0, 18)).apply {
        background = SiteColors.background
        border = EmptyBorder(22, 26, 18, 26)
        add(header(), BorderLayout.NORTH)
        add(workspace(), BorderLayout.CENTER)
        add(footer(), BorderLayout.SOUTH)
    }

    private fun header(): JPanel {
        val rawLogo = javaClass.getResource("/icons/remanga-official.png")?.let { ImageIcon(it).image }
        val logo = JLabel(rawLogo?.let { whiteIcon(it, 40) }, SwingConstants.CENTER).apply {
            preferredSize = Dimension(44, 44)
        }
        val title = JLabel("Генератор документации").apply {
            foreground = SiteColors.text
            font = Font("Segoe UI", Font.BOLD, 23)
        }
        val subtitle = JLabel("ReManga API · Markdown, JSON и CSV").apply {
            foreground = SiteColors.secondary
            font = Font("Segoe UI", Font.PLAIN, 13)
        }
        val text = JPanel().apply {
            isOpaque = false
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(title)
            add(Box.createVerticalStrut(3))
            add(subtitle)
        }
        return JPanel(BorderLayout(13, 0)).apply {
            isOpaque = false
            border = EmptyBorder(0, 0, 17, 0)
            add(logo, BorderLayout.WEST)
            add(text, BorderLayout.CENTER)
        }
    }

    private fun workspace(): JPanel {
        val label = JLabel("Папка для готовой документации").apply {
            foreground = SiteColors.text
            font = Font("Segoe UI", Font.BOLD, 14)
            alignmentX = LEFT_ALIGNMENT
        }
        outputField.apply {
            isOpaque = false
            border = EmptyBorder(0, 2, 0, 10)
            foreground = SiteColors.text
            disabledTextColor = SiteColors.secondary
            caretColor = SiteColors.text
            font = Font("Segoe UI", Font.PLAIN, 13)
        }
        val folderRow = RoundedSurface(44, SiteColors.surface, SiteColors.border).apply {
            layout = BorderLayout(8, 0)
            border = EmptyBorder(5, 14, 5, 5)
            preferredSize = Dimension(100, 48)
            maximumSize = Dimension(Int.MAX_VALUE, 48)
            alignmentX = LEFT_ALIGNMENT
            add(outputField, BorderLayout.CENTER)
            add(choose, BorderLayout.EAST)
        }

        status.apply { foreground = SiteColors.text; font = Font("Segoe UI", Font.BOLD, 14) }
        progressPercent.apply { foreground = SiteColors.secondary; font = Font("Segoe UI", Font.PLAIN, 12) }
        val statusRow = JPanel(BorderLayout()).apply {
            isOpaque = false
            add(status, BorderLayout.WEST)
            add(progressPercent, BorderLayout.EAST)
        }
        val progressBlock = JPanel(BorderLayout(0, 9)).apply {
            isOpaque = false
            alignmentX = LEFT_ALIGNMENT
            add(statusRow, BorderLayout.NORTH)
            add(progress, BorderLayout.SOUTH)
        }

        log.apply {
            isEditable = false
            isOpaque = false
            lineWrap = true
            wrapStyleWord = true
            foreground = Color(203, 206, 212)
            caretColor = SiteColors.text
            font = Font("Consolas", Font.PLAIN, 12)
            border = EmptyBorder(13, 15, 13, 15)
            text = "Журнал сборки появится здесь.\n"
        }
        val scroll = JScrollPane(log).apply {
            isOpaque = false
            border = EmptyBorder(0, 0, 0, 0)
            viewport.isOpaque = false
        }
        val logSurface = RoundedSurface(18, SiteColors.surface, SiteColors.border).apply {
            layout = BorderLayout()
            add(scroll, BorderLayout.CENTER)
        }

        val top = JPanel().apply {
            isOpaque = false
            layout = BoxLayout(this, BoxLayout.Y_AXIS)
            add(label)
            add(Box.createVerticalStrut(8))
            add(folderRow)
            add(Box.createVerticalStrut(18))
            add(progressBlock)
            add(Box.createVerticalStrut(16))
        }
        return JPanel(BorderLayout()).apply {
            isOpaque = false
            add(top, BorderLayout.NORTH)
            add(logSurface, BorderLayout.CENTER)
        }
    }

    private fun footer(): JPanel {
        val buttons = JPanel(FlowLayout(FlowLayout.RIGHT, 8, 0)).apply {
            isOpaque = false
            add(open)
            add(cancel)
            add(start)
        }
        val disclaimer = JLabel("Неофициальный ресурс. Не связан с администрацией и командой ReManga.").apply {
            foreground = Color(125, 129, 136)
            font = Font("Segoe UI", Font.PLAIN, 11)
        }
        val version = JLabel("v$GENERATOR_VERSION").apply {
            foreground = Color(125, 129, 136)
            font = Font("Segoe UI", Font.PLAIN, 11)
        }
        val meta = JPanel(BorderLayout()).apply {
            isOpaque = false
            border = EmptyBorder(11, 0, 0, 0)
            add(disclaimer, BorderLayout.WEST)
            add(version, BorderLayout.EAST)
        }
        return JPanel(BorderLayout()).apply {
            isOpaque = false
            border = EmptyBorder(16, 0, 0, 0)
            add(buttons, BorderLayout.NORTH)
            add(meta, BorderLayout.SOUTH)
        }
    }

    private fun whiteIcon(source: Image, size: Int): ImageIcon {
        val image = BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB)
        val g = image.createGraphics()
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.drawImage(source, 0, 0, size, size, null)
        g.composite = AlphaComposite.SrcIn
        g.color = Color.WHITE
        g.fillRect(0, 0, size, size)
        g.dispose()
        return ImageIcon(image)
    }

    private fun chooseOutput() {
        val chooser = JFileChooser(outputField.text).apply { fileSelectionMode = JFileChooser.DIRECTORIES_ONLY }
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) outputField.text = chooser.selectedFile.absolutePath
    }

    private fun startGeneration() {
        if (outputField.text.isBlank()) {
            JOptionPane.showMessageDialog(this, "Выберите папку для сохранения", "Не указана папка", JOptionPane.WARNING_MESSAGE)
            return
        }
        start.isEnabled = false
        cancel.isEnabled = true
        choose.isEnabled = false
        outputField.isEnabled = false
        progress.value = 0
        progressPercent.text = "0 %"
        status.text = "Подготовка…"
        log.text = ""
        cancelled = AtomicBoolean(false)
        val output = Paths.get(outputField.text.trim())
        executor.submit {
            try {
                val snapshot = ApiGenerator().generate(DEFAULT_PAGE, output, ProgressListener { percent, message ->
                    SwingUtilities.invokeLater {
                        progress.value = percent
                        progressPercent.text = "$percent %"
                        status.text = message
                        appendLog(message)
                    }
                }, cancelled)
                SwingUtilities.invokeLater {
                    status.text = "Готово · ${snapshot.endpointCount} маршрутов"
                    JOptionPane.showMessageDialog(this, "Документация собрана.\nМаршрутов: ${snapshot.endpointCount}", "Готово", JOptionPane.INFORMATION_MESSAGE)
                }
            } catch (_: GenerationCancelledException) {
                SwingUtilities.invokeLater { status.text = "Сборка отменена"; appendLog("Генерация отменена") }
            } catch (error: Exception) {
                SwingUtilities.invokeLater {
                    status.text = "Ошибка"
                    appendLog("Ошибка: ${error.message}")
                    JOptionPane.showMessageDialog(this, error.message, "Ошибка", JOptionPane.ERROR_MESSAGE)
                }
            } finally {
                SwingUtilities.invokeLater {
                    start.isEnabled = true
                    cancel.isEnabled = false
                    choose.isEnabled = true
                    outputField.isEnabled = true
                }
            }
        }
    }

    private fun appendLog(message: String) {
        log.append("$message\n")
        log.caretPosition = log.document.length
    }

    private fun openOutput() {
        try { Desktop.getDesktop().open(Paths.get(outputField.text).toFile()) }
        catch (error: Exception) { JOptionPane.showMessageDialog(this, error.message, "Ошибка", JOptionPane.ERROR_MESSAGE) }
    }
}
