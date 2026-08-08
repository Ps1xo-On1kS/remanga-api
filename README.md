<p align="center">
  <a href="https://remanga.org"><img src="docs/assets/remanga-logo.png" width="112" alt="ReManga"></a>
</p>

<h1 align="center">ReManga API</h1>

<p align="center">Неофициальная русскоязычная документация и генератор справочника API</p>

<p align="center">
  <a href="https://github.com/Ps1xo-On1kS/remanga-api/releases/latest"><img alt="Последний выпуск" src="https://img.shields.io/github/v/release/Ps1xo-On1kS/remanga-api?display_name=tag&style=for-the-badge&color=38bdf8"></a>
  <a href="https://github.com/Ps1xo-On1kS/remanga-api/actions/workflows/update-api-docs.yml"><img alt="Автообновление" src="https://img.shields.io/github/actions/workflow/status/Ps1xo-On1kS/remanga-api/update-api-docs.yml?style=for-the-badge&label=%D0%B0%D0%B2%D1%82%D0%BE%D0%BE%D0%B1%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D0%B5"></a>
  <a href="https://discord.gg/vF47ExMQpn"><img alt="Discord с подробными обновлениями" src="https://img.shields.io/badge/Discord-%D0%BE%D0%B1%D0%BD%D0%BE%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F-5865F2?style=for-the-badge&logo=discord&logoColor=white"></a>
  <a href="https://t.me/ps1xo_on1ks"><img alt="Telegram автора" src="https://img.shields.io/badge/Telegram-@ps1xo__on1ks-26A5E4?style=for-the-badge&logo=telegram&logoColor=white"></a>
  <img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-2.4.10-8b5cf6?style=for-the-badge&logo=kotlin&logoColor=white">
  <img alt="Windows x64" src="https://img.shields.io/badge/Windows-x64-2563eb?style=for-the-badge&logo=windows11&logoColor=white">
</p>

Русскоязычный справочник маршрутов API сайта [ReManga](https://remanga.org), автоматически собранный из публичного JavaScript-клиента.

**Репозиторий обновляется автоматически.** Фоновый GitHub Actions runner каждые 12 часов проверяет frontend-сборку ReManga. При обнаружении новой версии документация пересобирается, изменения публикуются в `main`, а готовые Markdown, JSON и CSV добавляются в новый Release.

> Проект не связан с администрацией ReManga и не является официальным SDK или официальной документацией.

## Что находится в репозитории

- [Полный справочник](API_REFERENCE.md) - 312 маршрутов, сгруппированных по разделам;
- [Практическое руководство](GUIDE_RU.md) - каталог карточек, профиль и инвентарь пользователя;
- `endpoints.json` - полный машиночитаемый снимок;
- `endpoints.csv` - таблица для Excel и фильтрации;
- `snapshot-summary.json` - версия frontend-сборки и статистика;
- `generator/` - открытые исходники основного Kotlin-генератора;
- `generate_api_docs.ps1` - независимый резервный генератор на PowerShell.

Текущий снимок построен по frontend-сборке `5272b905` и содержит:

| HTTP-метод | Количество |
|---|---:|
| GET | 168 |
| POST | 104 |
| PUT | 25 |
| DELETE | 15 |
| **Всего** | **312** |

## Готовые выпуски

В разделе [Releases](https://github.com/Ps1xo-On1kS/remanga-api/releases) для каждой обнаруженной frontend-сборки публикуются:

- готовый Markdown-файл `ReManga-API-<версия>.md`;
- `endpoints.json`;
- `endpoints.csv`;
- `snapshot-summary.json`.

Название тега имеет вид `frontend-<хеш>`, например `frontend-9596a55d`. Старые выпуски сохраняются, поэтому документацию разных сборок можно сравнивать.

Готовый генератор публикуется отдельно с тегами `generator-vX.Y.Z`. Portable-архив для Windows x64 содержит GUI, CLI и собственную Java-среду, поэтому устанавливать Java отдельно не требуется.

## Автоматическое обновление

GitHub Actions проверяет версию frontend ReManga каждые двенадцать часов.

Обычная проверка загружает только HTML-страницу каталога и сравнивает её хеш с уже опубликованными Releases. Workflow сначала обращается к основному домену, а затем пробует официальное зеркало `реманга.орг`.

Если обнаружена новая сборка, workflow автоматически:

1. скачивает зафиксированный выпуск Kotlin-генератора и проверяет SHA-256;
2. запускает консольный EXE;
3. проверяет полученный JSON;
4. коммитит обновлённую документацию в `main`;
5. создаёт новый GitHub Release;
6. прикладывает готовые Markdown, JSON и CSV.
7. формирует список добавленных, изменённых и удалённых API-методов;
8. отправляет сводку и списки в канал подробных обновлений Discord.

Состояние автоматической проверки видно на вкладке **Actions → Обновление документации API**.

## Сообщество и подробные обновления

Подробные уведомления о новых версиях и изменениях API публикуются в [Discord](https://discord.gg/vF47ExMQpn).

Связаться с автором напрямую можно в Telegram: [@ps1xo_on1ks](https://t.me/ps1xo_on1ks).

При каждой новой frontend-сборке в Discord публикуются версия, ссылка на Release и полные списки добавленных, изменённых и удалённых API-методов. Подробнее: [уведомления о версиях](DISCORD_NOTIFICATIONS.md).

## Быстрый старт

Для просмотра документации откройте [API_REFERENCE.md](API_REFERENCE.md).

Для ручной генерации скачайте последний архив `remanga-api-gen-<версия>-windows-x64.zip` из [Releases](https://github.com/Ps1xo-On1kS/remanga-api/releases), распакуйте его и запустите:

- `remanga_api_gen.exe` - окно с выбором папки, прогрессом и журналом;
- `remanga_api_gen_cli.exe` - консольный запуск и автоматизация.

Пример CLI:

```powershell
.\remanga_api_gen_cli.exe --page-url "https://remanga.org/card" --output "."
```

Резервный способ без Portable-архива:

```powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\generate_api_docs.ps1
```

Сборка Kotlin-версии из исходников требует JDK 21, но не требует установленного Gradle:

```powershell
cd .\generator
.\gradlew.bat clean test portableSha256 --no-daemon
```

Готовый ZIP и SHA-256 появятся в `generator\build\distributions`.

Генератор:

1. загружает страницу каталога ReManga;
2. определяет актуальную frontend-сборку;
3. читает публичные JavaScript-файлы;
4. извлекает HTTP-методы и пути;
5. формирует Markdown, JSON и CSV.

Он не выполняет POST, PUT, PATCH или DELETE-запросы, не входит в аккаунт и не требует токена.

## Ограничения

Production-сборка позволяет достоверно извлечь HTTP-методы, пути, параметры пути и некоторые заголовки. Полные схемы query-параметров, тела и ответа в ней отсутствуют, поэтому они не выдумываются.

Поле `bearer_capable` означает только то, что клиент способен приложить Bearer-токен. Оно не доказывает обязательность авторизации для конкретного маршрута.

API не заявлено как публичное и стабильное. Маршруты могут измениться или исчезнуть без предупреждения.

## Проекты, использующие API

Здесь публикуются завершённые инструменты и приложения, которые используют описанные маршруты ReManga API.

Пока список пуст. Загрузчик карточек будет добавлен после завершения и публикации стабильной версии.

Автор стороннего проекта может добавить его через Pull Request. Формат записи:

```markdown
- [Название проекта](https://github.com/author/project) - краткое описание и поддерживаемые платформы.
```

Проект должен быть доступен для проверки, иметь понятную инструкцию по запуску и не собирать чужие пароли, cookies или токены. Подробные требования находятся в [CONTRIBUTING.md](CONTRIBUTING.md#добавление-проекта-в-список).

## Сторонние проекты

- [vraestoren/remanga.py](https://github.com/vraestoren/remanga.py) - неофициальная Python-обёртка;
- [otaku-melons/remanga](https://github.com/otaku-melons/remanga) - неофициальный модуль парсера для Melon.

Они используются только как дополнительные ориентиры и не подтверждают официальный статус API.

## Ответственное использование

- Не проверяйте изменяющие методы на реальных аккаунтах.
- Не собирайте пароли, cookies или токены пользователей.
- Используйте кэш и разумные интервалы между запросами.
- Соблюдайте условия ReManga, `robots.txt` и права владельцев контента.
- Для публичных и коммерческих интеграций получите разрешение ReManga.

Справочник описывает обнаруженные технические маршруты, но не предоставляет дополнительных прав на API или контент сайта.
