package org.on1ks.remanga.api

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.nio.charset.StandardCharsets

object DocumentRenderer {
    private val json = Json {
        prettyPrint = true
        prettyPrintIndent = "  "
        encodeDefaults = true
        explicitNulls = true
    }

    private val groupNames = mapOf(
        "activity" to "Активность", "battlepass" to "Боевой пропуск", "billing" to "Платежи",
        "clubs" to "Клубы", "dashboard" to "Панель управления", "entries" to "Записи",
        "events" to "События", "forms" to "Формы", "forum" to "Форум",
        "functions" to "Служебные функции", "inventory" to "Инвентарь",
        "publishers" to "Издатели", "quizzes" to "Викторины", "search" to "Поиск",
        "shop" to "Магазин", "subscription" to "Подписка", "titles" to "Тайтлы",
        "users" to "Пользователи",
    )

    fun render(snapshot: ApiSnapshot): Map<String, ByteArray> {
        val summary = SnapshotSummary(
            generatedAtUtc = snapshot.generatedAtUtc,
            generatedAt = snapshot.generatedAt,
            sourcePage = snapshot.sourcePage,
            frontendRelease = snapshot.frontendRelease,
            endpointCount = snapshot.endpointCount,
            groups = snapshot.endpoints.groupingBy { it.group }.eachCount()
                .map { NamedCount(it.key, it.value) }.sortedWith(compareByDescending<NamedCount> { it.count }.thenBy { it.name }),
            methods = snapshot.endpoints.groupingBy { it.method }.eachCount()
                .map { NamedCount(it.key, it.value) }.sortedBy { it.name },
        )
        return linkedMapOf(
            "endpoints.json" to utf8Lf(json.encodeToString(snapshot) + "\n"),
            "endpoints.csv" to csv(snapshot.endpoints),
            "snapshot-summary.json" to utf8Lf(json.encodeToString(summary) + "\n"),
            "API_REFERENCE.md" to utf8Lf(markdown(snapshot)),
        )
    }

    private fun csv(endpoints: List<Endpoint>): ByteArray {
        fun quote(value: String) = "\"${value.replace("\"", "\"\"")}\""
        val lines = mutableListOf(
            listOf("method", "path", "group", "path_parameters", "bearer_capable", "content_type", "source_bundles")
                .joinToString(",", transform = ::quote),
        )
        endpoints.forEach { endpoint ->
            lines += listOf(
                quote(endpoint.method), quote(endpoint.path), quote(endpoint.group), quote(endpoint.pathParameters.joinToString(",")),
                quote(if (endpoint.bearerCapable) "True" else "False"), endpoint.contentType?.let(::quote).orEmpty(),
                quote(endpoint.sourceBundles.joinToString(",")),
            ).joinToString(",")
        }
        val text = lines.joinToString("\r\n", postfix = "\r\n")
        return byteArrayOf(0xEF.toByte(), 0xBB.toByte(), 0xBF.toByte()) + text.toByteArray(StandardCharsets.UTF_8)
    }

    private fun markdown(snapshot: ApiSnapshot): String = buildString {
        appendLine("# Неофициальный справочник маршрутов ReManga API")
        appendLine()
        appendLine("Справочник автоматически сформирован из публичных JavaScript-сборок сайта [ReManga](https://remanga.org).")
        appendLine()
        appendLine("- Дата генерации: `${snapshot.generatedAt}`")
        appendLine("- Версия frontend: `${snapshot.frontendRelease}`")
        appendLine("- Базовый адрес API: `${snapshot.apiBaseUrl}`")
        appendLine("- Базовый адрес медиа: `${snapshot.mediaBaseUrl}`")
        appendLine("- Извлечено маршрутов: **${snapshot.endpointCount}**")
        appendLine()
        appendLine("> Это не официальная документация ReManga. Маршруты могут измениться без предупреждения. Клиент сайта помечает маршруты как совместимые с Bearer-токеном, но это не доказывает обязательность авторизации. Полные схемы запросов и ответов отсутствуют в production-сборке, поэтому справочник их не выдумывает.")
        appendLine()
        appendLine("## Безопасность и правила использования")
        appendLine()
        appendLine("Не проверяйте изменяющие методы на реальных аккаунтах. Маршруты POST, PUT, PATCH и DELETE перечислены только для полноты. Соблюдайте условия ReManga, ограничения частоты запросов, авторские права и robots.txt. Никогда не собирайте пароли пользователей или cookies браузера.")
        appendLine()
        appendLine("## Сводка по HTTP-методам")
        appendLine()
        appendLine("| Метод | Количество |")
        appendLine("|---|---:|")
        snapshot.endpoints.groupingBy { it.method }.eachCount().toSortedMap().forEach { (method, count) -> appendLine("| $method | $count |") }
        appendLine()
        appendLine("## Проверенные полезные маршруты только для чтения")
        appendLine()
        appendLine("На момент генерации эти маршруты отдельно проверены и возвращали публичные данные без токена доступа:")
        appendLine()
        appendLine("| Назначение | Маршрут |")
        appendLine("|---|---|")
        appendLine("| Каталог карточек | `GET /api/inventory/catalog/` |")
        appendLine("| Публичный профиль пользователя | `GET /api/v2/users/{user_id}/` |")
        appendLine("| Инвентарь карточек с группировкой | `GET /api/v3/inventory/items/cards/{user_id}/` |")
        appendLine("| Отдельные экземпляры карточек | `GET /api/v2/inventory/items/cards/{user_id}/` |")
        appendLine()
        appendLine("В веб-клиенте обнаружены стандартные параметры каталога: `count`, `page`, `rank` и `ordering`. Значения рангов: `rank_a`, `rank_s`, `rank_re` и `rank_ev`.")

        snapshot.endpoints.groupBy { it.group }.toSortedMap().forEach { (group, endpoints) ->
            appendLine()
            appendLine("## ${groupNames[group] ?: group} - `$group` (${endpoints.size})")
            appendLine()
            appendLine("| Метод | Путь | Параметры пути | Bearer | Тип содержимого |")
            appendLine("|---|---|---|:---:|---|")
            endpoints.forEach { endpoint ->
                appendLine("| ${endpoint.method} | `${endpoint.path}` | ${endpoint.pathParameters.joinToString(", ")} | ${if (endpoint.bearerCapable) "да" else "не обнаружен"} | ${endpoint.contentType.orEmpty()} |")
            }
        }
    }

    private fun utf8Lf(value: String): ByteArray = value.replace("\r\n", "\n").toByteArray(StandardCharsets.UTF_8)
}
