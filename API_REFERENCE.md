# Неофициальный справочник маршрутов ReManga API

Справочник автоматически сформирован из публичных JavaScript-сборок сайта [ReManga](https://remanga.org).

- Дата генерации: `17:56 17.08.2026`
- Версия frontend: `d849bc37`
- Базовый адрес API: `https://api.remanga.org`
- Базовый адрес медиа: `https://remanga.org`
- Извлечено маршрутов: **312**

> Это не официальная документация ReManga. Маршруты могут измениться без предупреждения. Клиент сайта помечает маршруты как совместимые с Bearer-токеном, но это не доказывает обязательность авторизации. Полные схемы запросов и ответов отсутствуют в production-сборке, поэтому справочник их не выдумывает.

## Безопасность и правила использования

Не проверяйте изменяющие методы на реальных аккаунтах. Маршруты POST, PUT, PATCH и DELETE перечислены только для полноты. Соблюдайте условия ReManga, ограничения частоты запросов, авторские права и robots.txt. Никогда не собирайте пароли пользователей или cookies браузера.

## Сводка по HTTP-методам

| Метод | Количество |
|---|---:|
| DELETE | 15 |
| GET | 168 |
| POST | 104 |
| PUT | 25 |

## Проверенные полезные маршруты только для чтения

На момент генерации эти маршруты отдельно проверены и возвращали публичные данные без токена доступа:

| Назначение | Маршрут |
|---|---|
| Каталог карточек | `GET /api/inventory/catalog/` |
| Публичный профиль пользователя | `GET /api/v2/users/{user_id}/` |
| Инвентарь карточек с группировкой | `GET /api/v3/inventory/items/cards/{user_id}/` |
| Отдельные экземпляры карточек | `GET /api/v2/inventory/items/cards/{user_id}/` |

В веб-клиенте обнаружены стандартные параметры каталога: `count`, `page`, `rank` и `ordering`. Значения рангов: `rank_a`, `rank_s`, `rank_re` и `rank_ev`.

## Активность - `activity` (10)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/activity/vote-post/` |  | да | application/json |
| GET | `/api/v2/activity/active-gift/` |  | да |  |
| GET | `/api/v2/activity/notes/` |  | да |  |
| POST | `/api/v2/activity/notes/` |  | да | application/json |
| DELETE | `/api/v2/activity/notes/{note_id}/` | note_id | да |  |
| PUT | `/api/v2/activity/notes/{note_id}/` | note_id | да | application/json |
| GET | `/api/v2/activity/partner-gift-reward/` |  | да |  |
| POST | `/api/v2/activity/partner-gift-reward/` |  | да | application/json |
| POST | `/api/v2/activity/view-page/` |  | да | application/json |
| POST | `/api/v2/activity/vote/` |  | да | application/json |

## Боевой пропуск - `battlepass` (3)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/battlepass/current/preview/` |  | да |  |
| GET | `/api/v2/battlepass/levels/skip/` |  | да |  |
| POST | `/api/v2/battlepass/levels/skip/` |  | да | application/json |

## Платежи - `billing` (8)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/billing/promo-codes/` |  | да | application/json |
| DELETE | `/api/billing/withdraw/{withdraw_id}/` | withdraw_id | да |  |
| GET | `/api/v2/billing/coins-exchange/` |  | да |  |
| POST | `/api/v2/billing/coins-exchange/` |  | да | application/json |
| GET | `/api/v2/billing/lightning-balance/` |  | да |  |
| GET | `/api/v2/billing/lightning-payments/` |  | да |  |
| GET | `/api/v2/billing/users/payments/` |  | да |  |
| POST | `/api/v2/billing/withdraw/{withdraw_id}/receipt/` | withdraw_id | да | application/json |

## card-gen - `card-gen` (2)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/v2/card-gen/generate/` |  | да | application/json |
| GET | `/api/v2/card-gen/status/{task_id}/` | task_id | да |  |

## Клубы - `clubs` (35)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/clubs/` |  | да |  |
| POST | `/api/v2/clubs/` |  | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/buy-perk/{perk_id}/` | club_dir, perk_id | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/change_ranks/` | club_dir | да | application/json |
| GET | `/api/v2/clubs/{club_dir}/fee-history/` | club_dir | да |  |
| GET | `/api/v2/clubs/{club_dir}/fee-member/{user_id}/` | club_dir, user_id | да |  |
| PUT | `/api/v2/clubs/{club_dir}/fee-member/{user_id}/` | club_dir, user_id | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/fee-member/{user_id}/adjust/` | club_dir, user_id | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/fee-member/auto-pay/` | club_dir | да | application/json |
| GET | `/api/v2/clubs/{club_dir}/fee-members/` | club_dir | да |  |
| GET | `/api/v2/clubs/{club_dir}/fee-settings/` | club_dir | да |  |
| PUT | `/api/v2/clubs/{club_dir}/fee-settings/` | club_dir | да | application/json |
| GET | `/api/v2/clubs/{club_dir}/items-requests/` | club_dir | да |  |
| POST | `/api/v2/clubs/{club_dir}/items-requests/` | club_dir | да | application/json |
| PUT | `/api/v2/clubs/{club_dir}/items-requests/{request_id}/` | club_dir, request_id | да | application/json |
| GET | `/api/v2/clubs/{club_dir}/members/` | club_dir | да |  |
| GET | `/api/v2/clubs/{club_dir}/ranks/` | club_dir | да |  |
| DELETE | `/api/v2/clubs/{club_dir}/ranks/{rank_id}/delete/` | club_dir, rank_id | да |  |
| PUT | `/api/v2/clubs/{club_dir}/ranks/{rank_id}/edit/` | club_dir, rank_id | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/ranks/create/` | club_dir | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/regression/` | club_dir | да | application/json |
| DELETE | `/api/v2/clubs/{dir}/` | dir | да |  |
| GET | `/api/v2/clubs/{dir}/` | dir | да |  |
| POST | `/api/v2/clubs/{dir}/` | dir | да | application/json |
| PUT | `/api/v2/clubs/{dir}/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/change_roles/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/donate/` | dir | да | application/json |
| GET | `/api/v2/clubs/{dir}/donate/history/` | dir | да |  |
| POST | `/api/v2/clubs/{dir}/kick_members/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/leave/` | dir | да | application/json |
| GET | `/api/v2/clubs/{dir}/requests/` | dir | да |  |
| PUT | `/api/v2/clubs/{dir}/requests/{id}/` | dir, id | да | application/json |
| GET | `/api/v2/clubs/create-cost/` |  | да |  |
| GET | `/api/v2/clubs/perks/` |  | да |  |
| GET | `/api/v2/clubs/regressions/` |  | да |  |

## Панель управления - `dashboard` (10)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/dashboard/{publisher_id}/promo/` | publisher_id | да |  |
| POST | `/api/v2/dashboard/{publisher_id}/promo/{promo_id}/stop/` | publisher_id, promo_id | да | application/json |
| POST | `/api/v2/dashboard/{publisher_id}/promo/add/` | publisher_id | да | application/json |
| POST | `/api/v2/dashboard/{publisher_id}/promo/admin/{promo_id}/stop/` | publisher_id, promo_id | да | application/json |
| GET | `/api/v2/dashboard/{publisher_id}/promo/aggregation/` | publisher_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/billing/` | publisher_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/billing/buy/` | publisher_id | да |  |
| POST | `/api/v2/dashboard/{publisher_id}/promo/billing/buy/` | publisher_id | да | application/json |
| GET | `/api/v2/dashboard/{publisher_id}/promo/statistics/{promo_id}/` | publisher_id, promo_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/statistics/title/{title_id}/` | publisher_id, title_id | да |  |

## Записи - `entries` (1)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/entries/{entry_id}/` | entry_id | да |  |

## События - `events` (79)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/events/` |  | да |  |
| GET | `/api/v2/events/advent-calendar/` |  | да |  |
| POST | `/api/v2/events/advent-calendar/` |  | да | application/json |
| GET | `/api/v2/events/advent-calendar/opens/` |  | да |  |
| GET | `/api/v2/events/card-battle/cards/` |  | да |  |
| GET | `/api/v2/events/card-battle/catacombs/levels/` |  | да |  |
| POST | `/api/v2/events/card-battle/catacombs/levels/{level}/enter/` | level | да | application/json |
| POST | `/api/v2/events/card-battle/catacombs/mini-game/{attempt_id}/resolve/` | attempt_id | да | application/json |
| GET | `/api/v2/events/card-battle/catacombs/runs/` |  | да |  |
| GET | `/api/v2/events/card-battle/catacombs/runs/{run_id}/` | run_id | да |  |
| POST | `/api/v2/events/card-battle/catacombs/scrolls/cap-raise/` |  | да | application/json |
| POST | `/api/v2/events/card-battle/catacombs/scrolls/enhance/` |  | да | application/json |
| POST | `/api/v2/events/card-battle/catacombs/scrolls/resurrect/` |  | да | application/json |
| GET | `/api/v2/events/card-battle/catacombs/state/` |  | да |  |
| GET | `/api/v2/events/card-battle/daily/` |  | да |  |
| POST | `/api/v2/events/card-battle/daily/{id}/claim/` | id | да | application/json |
| GET | `/api/v2/events/card-battle/event-points/buy/` |  | да |  |
| POST | `/api/v2/events/card-battle/event-points/buy/` |  | да | application/json |
| POST | `/api/v2/events/card-battle/join/` |  | да | application/json |
| GET | `/api/v2/events/card-battle/locations/` |  | да |  |
| POST | `/api/v2/events/card-battle/locations/{id}/raid/` | id | да | application/json |
| GET | `/api/v2/events/card-battle/potions/` |  | да |  |
| POST | `/api/v2/events/card-battle/potions/{id}/use/` | id | да | application/json |
| GET | `/api/v2/events/card-battle/profile/` |  | да |  |
| GET | `/api/v2/events/card-battle/pvp/history/` |  | да |  |
| GET | `/api/v2/events/card-battle/pvp/leaderboard/` |  | да |  |
| POST | `/api/v2/events/card-battle/pvp/match/` |  | да |  |
| GET | `/api/v2/events/card-battle/pvp/match/{id}/` | id | да |  |
| GET | `/api/v2/events/card-battle/pvp/player/{id}/` | id | да |  |
| GET | `/api/v2/events/card-battle/pvp/season/` |  | да |  |
| GET | `/api/v2/events/card-battle/rewards/` |  | да |  |
| GET | `/api/v2/events/card-battle/squad/` |  | да |  |
| POST | `/api/v2/events/card-battle/squad/` |  | да | application/json |
| POST | `/api/v2/events/card-battle/squad/slot/` |  | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/boss/` |  | да |  |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/attack/` | id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/claim-rewards/` | id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/enter/` | id | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/my-chests/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/state/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/top/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/config/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/my-active/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/top/total/clubs/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/top/total/users/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/buy-event-points-by-balance/` |  | да |  |
| POST | `/api/v2/events/dungeon-hunters/buy-event-points-by-balance/` |  | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/dungeon/` |  | да |  |
| POST | `/api/v2/events/dungeon-hunters/dungeon/{dungeon_id}/enter/` | dungeon_id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/dungeon/runs/{dungeon_run_id}/mini-game-reward/` | dungeon_run_id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/dungeon/runs/{dungeon_run_id}/reward/` | dungeon_run_id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/dungeon/runs/{run_id}/cancel/` | run_id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/equipment/{user_equipment_id}/equip/` | user_equipment_id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/equipment/{user_equipment_id}/unequip/` | user_equipment_id | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/equipment/inventory/` |  | да |  |
| POST | `/api/v2/events/dungeon-hunters/join/` |  | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/my-potions/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/my-profile/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/my-runs/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/top/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/top/clubs/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/top/place/` |  | да |  |
| POST | `/api/v2/events/dungeon-hunters/use-mp-potion/` |  | да | application/json |
| GET | `/api/v2/events/eventpoint-balance/` |  | да |  |
| POST | `/api/v2/events/new-year-madness/action/` |  | да | application/json |
| POST | `/api/v2/events/new-year-madness/join/` |  | да | application/json |
| GET | `/api/v2/events/new-year-madness/snowman/progress/` |  | да |  |
| GET | `/api/v2/events/new-year-madness/snowman/top-users/` |  | да |  |
| GET | `/api/v2/events/new-year-madness/user-team/` |  | да |  |
| GET | `/api/v2/events/valentine_day/` |  | да |  |
| POST | `/api/v2/events/valentine_day/action/` |  | да | application/json |
| POST | `/api/v2/events/valentine_day/buy/` |  | да | application/json |
| POST | `/api/v2/events/valentine_day/choose/` |  | да | application/json |
| GET | `/api/v2/events/valentine_day/collect/` |  | да |  |
| POST | `/api/v2/events/valentine_day/collect/` |  | да | application/json |
| GET | `/api/v2/events/valentine_day/progress/` |  | да |  |
| GET | `/api/v2/events/valentine_day/top-users/` |  | да |  |
| GET | `/api/v2/events/valentine_day/top-users/me/` |  | да |  |
| GET | `/api/v2/events/valentine_day/user-waifu/` |  | да |  |

## Формы - `forms` (2)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/forms/coins-payments/` |  | да |  |
| GET | `/api/forms/payments/` |  | да |  |

## Форум - `forum` (8)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/forum/` |  | да |  |
| POST | `/api/v2/forum/` |  | да | application/json |
| GET | `/api/v2/forum/{dir}/` | dir | да |  |
| PUT | `/api/v2/forum/{dir}/` | dir | да | application/json |
| GET | `/api/v2/forum/{dir}/reactions/` | dir | да |  |
| POST | `/api/v2/forum/ban/{dir}/` | dir | да | application/json |
| GET | `/api/v2/forum/search/` |  | да |  |
| GET | `/api/v2/forum/tags/` |  | да |  |

## Служебные функции - `functions` (3)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/functions/popup/` |  | да |  |
| GET | `/api/functions/sliders-schemes/` |  | да |  |
| GET | `/api/v2/functions/banner-sliders/` |  | да |  |

## Инвентарь - `inventory` (55)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/inventory/{title_id}/cards/` | title_id | да |  |
| POST | `/api/inventory/{user_id}/cards/merge/` | user_id | да | application/json |
| POST | `/api/inventory/cards/` |  | да | application/json |
| GET | `/api/inventory/cards/{id}/` | id | да |  |
| PUT | `/api/inventory/cards/{id}/` | id | да | application/json |
| GET | `/api/inventory/catalog/` |  | да |  |
| GET | `/api/inventory/character/{character_id}/cards/` | character_id | да |  |
| PUT | `/api/v2/inventory/{user_id}/cards/manage/exchangeable/` | user_id | да | application/json |
| PUT | `/api/v2/inventory/{user_id}/cards/manage/favorite/` | user_id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/collections/` | user_id | да |  |
| POST | `/api/v2/inventory/{user_id}/collections/` | user_id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/exchanges/` | user_id | да |  |
| POST | `/api/v2/inventory/{user_id}/exchanges/` | user_id | да | application/json |
| PUT | `/api/v2/inventory/{user_id}/exchanges/{id}/` | user_id, id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/rare-collections/` | user_id | да |  |
| GET | `/api/v2/inventory/cards/{card_id}/is_locked/` | card_id | да |  |
| GET | `/api/v2/inventory/cards/{id}/sources/` | id | да |  |
| GET | `/api/v2/inventory/cards/{inventory_card_id}/` | inventory_card_id | да |  |
| GET | `/api/v2/inventory/cards/album/` |  | да |  |
| GET | `/api/v2/inventory/cards/album/users/` |  | да |  |
| POST | `/api/v2/inventory/cards/awaken/` |  | да | application/json |
| POST | `/api/v2/inventory/cards/dust/` |  | да | application/json |
| GET | `/api/v2/inventory/cards/dust/exchange/` |  | да |  |
| POST | `/api/v2/inventory/cards/dust/exchange/` |  | да | application/json |
| POST | `/api/v2/inventory/cards/enhance/` |  | да | application/json |
| POST | `/api/v2/inventory/cards/full-reroll/` |  | да | application/json |
| GET | `/api/v2/inventory/cards/has_cards/` |  | да |  |
| POST | `/api/v2/inventory/cards/reroll/` |  | да | application/json |
| DELETE | `/api/v2/inventory/collections/{collection_id}/` | collection_id | да |  |
| GET | `/api/v2/inventory/collections/{collection_id}/` | collection_id | да |  |
| PUT | `/api/v2/inventory/collections/{collection_id}/` | collection_id | да | application/json |
| GET | `/api/v2/inventory/collections/recent/` |  | да |  |
| POST | `/api/v2/inventory/complete-collection/{collection_id}/` | collection_id | да | application/json |
| GET | `/api/v2/inventory/decks/` |  | да |  |
| POST | `/api/v2/inventory/decks/{deck_id}/choose/` | deck_id | да | application/json |
| POST | `/api/v2/inventory/decks/{deck_id}/open/` | deck_id | да | application/json |
| GET | `/api/v2/inventory/decks/awakened/` |  | да |  |
| POST | `/api/v2/inventory/decks/awakened/{deck_id}/open/` | deck_id | да | application/json |
| GET | `/api/v2/inventory/items/cards/{user_id}/` | user_id | да |  |
| POST | `/api/v2/inventory/items/chests/{user_chest_id}/open/` | user_chest_id | да | application/json |
| GET | `/api/v2/inventory/items/chests/{user_id}/` | user_id | да |  |
| GET | `/api/v2/inventory/items/customizations/{user_id}/` | user_id | да |  |
| GET | `/api/v2/inventory/items/moments/{user_id}/` | user_id | да |  |
| POST | `/api/v2/inventory/items/put-on/` |  | да | application/json |
| POST | `/api/v2/inventory/items/take-off/` |  | да | application/json |
| GET | `/api/v2/inventory/requests/status/` |  | да |  |
| GET | `/api/v2/inventory/upgrades/` |  | да |  |
| POST | `/api/v2/inventory/wishes/` |  | да | application/json |
| DELETE | `/api/v2/inventory/wishes/{card_id}/` | card_id | да |  |
| GET | `/api/v2/inventory/wishes/{card_id}/` | card_id | да |  |
| GET | `/api/v2/inventory/wishes/{card_id}/{user_id}/` | card_id, user_id | да |  |
| GET | `/api/v2/inventory/wishes/{user_id}/has_wishes/` | user_id | да |  |
| GET | `/api/v2/inventory/wishes/intersection/{partner_id}/` | partner_id | да |  |
| GET | `/api/v2/inventory/wishes/users/{user_id}/` | user_id | да |  |
| GET | `/api/v3/inventory/items/cards/{user_id}/` | user_id | да |  |

## Издатели - `publishers` (10)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/publishers/{publisher_id}/cards/` | publisher_id | да |  |
| GET | `/api/publishers/{publisher_id}/contract/` | publisher_id | да |  |
| GET | `/api/publishers/contracts/{contract_id}/acts/` | contract_id | да |  |
| GET | `/api/v2/publishers/{dir}/members/` | dir | да |  |
| GET | `/api/v2/publishers/{publisher_dir}/` | publisher_dir | да |  |
| GET | `/api/v2/publishers/{publisher_id}/withdraw/` | publisher_id | да |  |
| GET | `/api/v2/publishers/invitations/` |  | да |  |
| POST | `/api/v2/publishers/invitations/` |  | да | application/json |
| DELETE | `/api/v2/publishers/invitations/{invitation_id}/` | invitation_id | да |  |
| PUT | `/api/v2/publishers/invitations/{invitation_id}/` | invitation_id | да | application/json |

## Викторины - `quizzes` (10)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/quizzes/` |  | да |  |
| POST | `/api/v2/quizzes/` |  | да | application/json |
| DELETE | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да |  |
| GET | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да |  |
| PUT | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да | application/json |
| POST | `/api/v2/quizzes/{quiz_id}/answers/` | quiz_id | да | application/json |
| POST | `/api/v2/quizzes/{quiz_id}/ban/` | quiz_id | да | application/json |
| POST | `/api/v2/quizzes/{quiz_id}/recover/` | quiz_id | да | application/json |
| GET | `/api/v2/quizzes/{quiz_id}/users/{option_id}/` | quiz_id, option_id | да |  |
| GET | `/api/v2/quizzes/statistics/` |  | да |  |

## Поиск - `search` (1)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/search/characters/catalog/` |  | да |  |

## Магазин - `shop` (4)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/shop/` |  | да |  |
| POST | `/api/v2/shop/buy/{id}/` | id | да | application/json |
| GET | `/api/v2/shop/decks/awakened/` |  | да |  |
| GET | `/api/v2/shop/decks/awakened/{id}/` | id | да |  |

## Подписка - `subscription` (2)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/subscription/` |  | да |  |
| POST | `/api/v2/subscription/trial/` |  | да | application/json |

## Тайтлы - `titles` (15)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/titles/{title_dir}/` | title_dir | да |  |
| GET | `/api/v2/titles/characters/{id}/` | id | да |  |
| GET | `/api/v2/titles/collections/` |  | да |  |
| POST | `/api/v2/titles/collections/` |  | да | application/json |
| DELETE | `/api/v2/titles/collections/{id}/` | id | да |  |
| GET | `/api/v2/titles/collections/{id}/` | id | да |  |
| PUT | `/api/v2/titles/collections/{id}/` | id | да | application/json |
| GET | `/api/v2/titles/moments/` |  | да |  |
| POST | `/api/v2/titles/moments/` |  | да | application/json |
| DELETE | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да |  |
| GET | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да |  |
| POST | `/api/v2/titles/moments/{moment_dir}/view/` | moment_dir | да | application/json |
| GET | `/api/v2/titles/moments/catalog/` |  | да |  |
| GET | `/api/v2/titles/moments/tags/` |  | да |  |
| GET | `/api/v3/titles/` |  | да |  |

## Пользователи - `users` (54)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| DELETE | `/api/users/bookmarks/` |  | да |  |
| POST | `/api/users/bookmarks/` |  | да | application/json |
| POST | `/api/users/login/` |  | да | application/json |
| POST | `/api/users/password-reset/` |  | да | application/json |
| PUT | `/api/users/password-reset/` |  | да | application/json |
| POST | `/api/users/signup/` |  | да | application/json |
| POST | `/api/users/social/` |  | да | application/json |
| GET | `/api/v2/users/{user_id}/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/achievements/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/badges/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/bookmarks/` | user_id | да |  |
| PUT | `/api/v2/users/{user_id}/bookmarks/` | user_id | да | application/json |
| GET | `/api/v2/users/{user_id}/clubs/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/history/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/history/delete/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/results/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да |  |
| POST | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| PUT | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| PUT | `/api/v2/users/achievements/update/` |  | да | application/json |
| GET | `/api/v2/users/badges/{badge_id}/` | badge_id | да |  |
| GET | `/api/v2/users/badges/{badge_id}/owners/` | badge_id | да |  |
| PUT | `/api/v2/users/badges/update/` |  | да | application/json |
| GET | `/api/v2/users/bans/` |  | да |  |
| GET | `/api/v2/users/black-list/` |  | да |  |
| POST | `/api/v2/users/black-list/` |  | да | application/json |
| DELETE | `/api/v2/users/black-list/{block_id}/` | block_id | да |  |
| PUT | `/api/v2/users/black-list/{block_id}/` | block_id | да | application/json |
| DELETE | `/api/v2/users/current/` |  | да |  |
| GET | `/api/v2/users/current/` |  | да |  |
| PUT | `/api/v2/users/current/` |  | да | application/json |
| GET | `/api/v2/users/current/notify-settings/` |  | да |  |
| POST | `/api/v2/users/current/tour-reward/` |  | да | application/json |
| POST | `/api/v2/users/email-change/` |  | да | application/json |
| GET | `/api/v2/users/have-card/{card_id}/` | card_id | да |  |
| GET | `/api/v2/users/my-recommendations/` |  | да |  |
| GET | `/api/v2/users/notifications/` |  | да |  |
| POST | `/api/v2/users/notifications/delete/` |  | да | application/json |
| POST | `/api/v2/users/notifications/set-read/` |  | да | application/json |
| GET | `/api/v2/users/notifications/summary-by-title/` |  | да |  |
| GET | `/api/v2/users/notifications/summary-by-type/` |  | да |  |
| GET | `/api/v2/users/notifications/summary-total/` |  | да |  |
| POST | `/api/v2/users/password-change/` |  | да | application/json |
| PUT | `/api/v2/users/publishers-order/` |  | да | application/json |
| GET | `/api/v2/users/requests/` |  | да |  |
| GET | `/api/v2/users/requests/{id}/` | id | да |  |
| PUT | `/api/v2/users/requests/{id}/cancel/` | id | да | application/json |
| POST | `/api/v2/users/set-email/` |  | да | application/json |
| POST | `/api/v2/users/social/vk/` |  | да | application/json |
| POST | `/api/v2/users/social/vk/bind/` |  | да | application/json |
| GET | `/api/v2/users/tickets/` |  | да |  |
| GET | `/api/v2/users/top/` |  | да |  |
| GET | `/api/v2/users/top/place/` |  | да |  |
