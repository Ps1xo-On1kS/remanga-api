# Неофициальный справочник маршрутов ReManga API

Справочник автоматически сформирован из публичных JavaScript-сборок сайта [ReManga](https://remanga.org).

- Дата генерации: `16:23 21.08.2026`
- Версия frontend: `8fed73b4`
- Базовый адрес API: `https://api.remanga.org`
- Базовый адрес медиа: `https://remanga.org`
- Извлечено маршрутов: **837**

> Это не официальная документация ReManga. Маршруты могут измениться без предупреждения. Клиент сайта помечает маршруты как совместимые с Bearer-токеном, но это не доказывает обязательность авторизации. Полные схемы запросов и ответов отсутствуют в production-сборке, поэтому справочник их не выдумывает.

## Безопасность и правила использования

Не проверяйте изменяющие методы на реальных аккаунтах. Маршруты POST, PUT, PATCH и DELETE перечислены только для полноты. Соблюдайте условия ReManga, ограничения частоты запросов, авторские права и robots.txt. Никогда не собирайте пароли пользователей или cookies браузера.

## Сводка по HTTP-методам

| Метод | Количество |
|---|---:|
| DELETE | 48 |
| GET | 434 |
| PATCH | 28 |
| POST | 241 |
| PUT | 86 |

## Проверенные полезные маршруты только для чтения

На момент генерации эти маршруты отдельно проверены и возвращали публичные данные без токена доступа:

| Назначение | Маршрут |
|---|---|
| Каталог карточек | `GET /api/inventory/catalog/` |
| Публичный профиль пользователя | `GET /api/v2/users/{user_id}/` |
| Инвентарь карточек с группировкой | `GET /api/v3/inventory/items/cards/{user_id}/` |
| Отдельные экземпляры карточек | `GET /api/v2/inventory/items/cards/{user_id}/` |

В веб-клиенте обнаружены стандартные параметры каталога: `count`, `page`, `rank` и `ordering`. Значения рангов: `rank_a`, `rank_s`, `rank_re` и `rank_ev`.

## Активность - `activity` (38)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/activity/comments/` |  | да |  |
| POST | `/api/activity/comments/` |  | да | application/json |
| DELETE | `/api/activity/comments/{comment_id}/` | comment_id | да |  |
| GET | `/api/activity/comments/{comment_id}/` | comment_id | да |  |
| PUT | `/api/activity/comments/{comment_id}/` | comment_id | да | application/json |
| PUT | `/api/activity/comments/{comment_id}/ban/` | comment_id | да | application/json |
| GET | `/api/activity/comments/count/` |  | да |  |
| POST | `/api/activity/ratings/` |  | да | application/json |
| GET | `/api/activity/title-data/{title_id}/` | title_id | да |  |
| PUT | `/api/activity/title-data/{title_id}/` | title_id | да | application/json |
| DELETE | `/api/activity/views/` |  | да |  |
| POST | `/api/activity/views/` |  | да | application/json |
| POST | `/api/activity/vote-post/` |  | да | application/json |
| POST | `/api/activity/votes/` |  | да | application/json |
| POST | `/api/activity/vote-similar/` |  | да | application/json |
| GET | `/api/v2/activity/active-gift/` |  | да |  |
| GET | `/api/v2/activity/comments/` |  | да |  |
| POST | `/api/v2/activity/comments/` |  | да | application/json |
| DELETE | `/api/v2/activity/comments/{comment_id}/` | comment_id | да |  |
| GET | `/api/v2/activity/comments/{comment_id}/` | comment_id | да |  |
| PUT | `/api/v2/activity/comments/{comment_id}/` | comment_id | да | application/json |
| PUT | `/api/v2/activity/comments/{comment_id}/ban/` | comment_id | да | application/json |
| GET | `/api/v2/activity/comments/count/` |  | да |  |
| GET | `/api/v2/activity/notes/` |  | да |  |
| POST | `/api/v2/activity/notes/` |  | да | application/json |
| DELETE | `/api/v2/activity/notes/{note_id}/` | note_id | да |  |
| PUT | `/api/v2/activity/notes/{note_id}/` | note_id | да | application/json |
| GET | `/api/v2/activity/partner-gift-reward/` |  | да |  |
| POST | `/api/v2/activity/partner-gift-reward/` |  | да | application/json |
| POST | `/api/v2/activity/random-reward/` |  | да | application/json |
| POST | `/api/v2/activity/view-page/` |  | да | application/json |
| POST | `/api/v2/activity/vote/` |  | да | application/json |
| GET | `/api/v3/activity/comments/` |  | да |  |
| POST | `/api/v3/activity/comments/` |  | да | application/json |
| DELETE | `/api/v3/activity/comments/{comment_id}/` | comment_id | да |  |
| GET | `/api/v3/activity/comments/{comment_id}/` | comment_id | да |  |
| PUT | `/api/v3/activity/comments/{comment_id}/` | comment_id | да | application/json |
| POST | `/api/v3/activity/comments/{comment_id}/ban/` | comment_id | да | application/json |

## Боевой пропуск - `battlepass` (12)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/battlepass/check-access/` |  | да | application/json |
| GET | `/api/battlepass/current/` |  | да |  |
| POST | `/api/battlepass/current/` |  | да | application/json |
| GET | `/api/battlepass/current/preview/` |  | да |  |
| POST | `/api/battlepass/join/` |  | да | application/json |
| GET | `/api/battlepass/manage/` |  | да |  |
| POST | `/api/battlepass/manage-minigames/` |  | да | application/json |
| GET | `/api/battlepass/tasks/` |  | да |  |
| POST | `/api/battlepass/tasks/` |  | да | application/json |
| PUT | `/api/battlepass/tasks/` |  | да | application/json |
| GET | `/api/v2/battlepass/levels/skip/` |  | да |  |
| POST | `/api/v2/battlepass/levels/skip/` |  | да | application/json |

## Платежи - `billing` (34)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/billing/buy-chapter/` |  | да | application/json |
| GET | `/api/billing/charge/` |  | да |  |
| POST | `/api/billing/charge/` |  | да | application/json |
| POST | `/api/billing/donate/` |  | да | application/json |
| POST | `/api/billing/get-payment-ckassa/` |  | да | application/json |
| POST | `/api/billing/get-payment-cloudpayments/` |  | да | application/json |
| POST | `/api/billing/get-payment-cryptocloud/` |  | да | application/json |
| GET | `/api/billing/get-payment-monetaru/` |  | да |  |
| GET | `/api/billing/get-payment-monetaru-foreign/` |  | да |  |
| POST | `/api/billing/get-payment-payselection/` |  | да |  |
| POST | `/api/billing/get-payment-payze/{signature}/` | signature | да |  |
| POST | `/api/billing/get-payment-robokassa/` |  | да | application/json |
| POST | `/api/billing/get-payment-tinkoff/` |  | да | application/json |
| POST | `/api/billing/get-payment-yookassa/` |  | да | application/json |
| POST | `/api/billing/get-subscription-tinkoff/` |  | да | application/json |
| GET | `/api/billing/premium/` |  | да |  |
| POST | `/api/billing/premium/` |  | да | application/json |
| PUT | `/api/billing/premium/` |  | да | application/json |
| POST | `/api/billing/promo-codes/` |  | да | application/json |
| POST | `/api/billing/withdraw/` |  | да | application/json |
| DELETE | `/api/billing/withdraw/{withdraw_id}/` | withdraw_id | да |  |
| GET | `/api/v2/billing/charge/` |  | да |  |
| POST | `/api/v2/billing/charge/` |  | да | application/json |
| GET | `/api/v2/billing/coins-exchange/` |  | да |  |
| POST | `/api/v2/billing/coins-exchange/` |  | да | application/json |
| GET | `/api/v2/billing/exchange/` |  | да |  |
| GET | `/api/v2/billing/lightning-balance/` |  | да |  |
| GET | `/api/v2/billing/lightning-payments/` |  | да |  |
| POST | `/api/v2/billing/paypal/action/` |  | да | application/json |
| POST | `/api/v2/billing/paypal/webhook/` |  | да | application/json |
| GET | `/api/v2/billing/users/payments/` |  | да |  |
| POST | `/api/v2/billing/withdraw/` |  | да | application/json |
| DELETE | `/api/v2/billing/withdraw/{withdraw_id}/` | withdraw_id | да |  |
| POST | `/api/v2/billing/withdraw/{withdraw_id}/receipt/` | withdraw_id | да | application/json |

## card-gen - `card-gen` (2)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/v2/card-gen/generate/` |  | да | application/json |
| GET | `/api/v2/card-gen/status/{task_id}/` | task_id | да |  |

## Клубы - `clubs` (39)

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
| GET | `/api/v2/clubs/{club_dir}/items-requests/{request_id}/` | club_dir, request_id | да |  |
| PUT | `/api/v2/clubs/{club_dir}/items-requests/{request_id}/` | club_dir, request_id | да | application/json |
| GET | `/api/v2/clubs/{club_dir}/members/` | club_dir | да |  |
| GET | `/api/v2/clubs/{club_dir}/ranks/` | club_dir | да |  |
| DELETE | `/api/v2/clubs/{club_dir}/ranks/{rank_id}/delete/` | club_dir, rank_id | да |  |
| PUT | `/api/v2/clubs/{club_dir}/ranks/{rank_id}/edit/` | club_dir, rank_id | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/ranks/create/` | club_dir | да | application/json |
| POST | `/api/v2/clubs/{club_dir}/regression/` | club_dir | да | application/json |
| DELETE | `/api/v2/clubs/{dir}/` | dir | да |  |
| GET | `/api/v2/clubs/{dir}/` | dir | да |  |
| PATCH | `/api/v2/clubs/{dir}/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/` | dir | да | application/json |
| PUT | `/api/v2/clubs/{dir}/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/change_roles/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/donate/` | dir | да | application/json |
| GET | `/api/v2/clubs/{dir}/donate/history/` | dir | да |  |
| POST | `/api/v2/clubs/{dir}/kick_members/` | dir | да | application/json |
| POST | `/api/v2/clubs/{dir}/leave/` | dir | да | application/json |
| GET | `/api/v2/clubs/{dir}/requests/` | dir | да |  |
| GET | `/api/v2/clubs/{dir}/requests/{id}/` | dir, id | да |  |
| PATCH | `/api/v2/clubs/{dir}/requests/{id}/` | dir, id | да | application/json |
| PUT | `/api/v2/clubs/{dir}/requests/{id}/` | dir, id | да | application/json |
| GET | `/api/v2/clubs/create-cost/` |  | да |  |
| GET | `/api/v2/clubs/perks/` |  | да |  |
| GET | `/api/v2/clubs/regressions/` |  | да |  |

## config - `config` (1)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/config/` |  | да |  |

## Панель управления - `dashboard` (13)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/dashboard/promo/render/` |  | да |  |
| POST | `/api/dashboard/promo/render/` |  | да | application/json |
| GET | `/api/v2/dashboard/{publisher_id}/promo/` | publisher_id | да |  |
| PUT | `/api/v2/dashboard/{publisher_id}/promo/` | publisher_id | да | application/json |
| POST | `/api/v2/dashboard/{publisher_id}/promo/{promo_id}/stop/` | publisher_id, promo_id | да | application/json |
| POST | `/api/v2/dashboard/{publisher_id}/promo/add/` | publisher_id | да | application/json |
| POST | `/api/v2/dashboard/{publisher_id}/promo/admin/{promo_id}/stop/` | publisher_id, promo_id | да | application/json |
| GET | `/api/v2/dashboard/{publisher_id}/promo/aggregation/` | publisher_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/billing/` | publisher_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/billing/buy/` | publisher_id | да |  |
| POST | `/api/v2/dashboard/{publisher_id}/promo/billing/buy/` | publisher_id | да | application/json |
| GET | `/api/v2/dashboard/{publisher_id}/promo/statistics/{promo_id}/` | publisher_id, promo_id | да |  |
| GET | `/api/v2/dashboard/{publisher_id}/promo/statistics/title/{title_id}/` | publisher_id, title_id | да |  |

## dnd - `dnd` (8)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/v2/dnd/admin/add_item/` |  | да | application/json |
| GET | `/api/v2/dnd/characters/{user_id}/` | user_id | да |  |
| GET | `/api/v2/dnd/characters/classes/` |  | да |  |
| POST | `/api/v2/dnd/characters/create/` |  | да | application/json |
| GET | `/api/v2/dnd/characters/races/` |  | да |  |
| GET | `/api/v2/dnd/characters/skills/` |  | да |  |
| GET | `/api/v2/dnd/inventory/` |  | да |  |
| GET | `/api/v2/dnd/locations/` |  | да |  |

## Записи - `entries` (4)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/entries/` |  | да |  |
| GET | `/api/entries/{entry_id}/` | entry_id | да |  |
| GET | `/api/v2/entries/` |  | да |  |
| GET | `/api/v2/entries/{entry_id}/` | entry_id | да |  |

## События - `events` (95)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/events/` |  | да |  |
| GET | `/api/v2/events/advent-calendar/` |  | да |  |
| POST | `/api/v2/events/advent-calendar/` |  | да | application/json |
| GET | `/api/v2/events/advent-calendar/opens/` |  | да |  |
| GET | `/api/v2/events/card-battle/cards/` |  | да |  |
| GET | `/api/v2/events/card-battle/catacombs/levels/` |  | да |  |
| GET | `/api/v2/events/card-battle/catacombs/levels/{level}/` | level | да |  |
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
| GET | `/api/v2/events/card-battle/pvp/me/` |  | да |  |
| GET | `/api/v2/events/card-battle/pvp/player/{id}/` | id | да |  |
| GET | `/api/v2/events/card-battle/pvp/player/{id}/history/` | id | да |  |
| GET | `/api/v2/events/card-battle/pvp/season/` |  | да |  |
| GET | `/api/v2/events/card-battle/rewards/` |  | да |  |
| GET | `/api/v2/events/card-battle/runs/` |  | да |  |
| GET | `/api/v2/events/card-battle/runs/{id}/` | id | да |  |
| GET | `/api/v2/events/card-battle/squad/` |  | да |  |
| POST | `/api/v2/events/card-battle/squad/` |  | да | application/json |
| POST | `/api/v2/events/card-battle/squad/slot/` |  | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/boss/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/` | id | да |  |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/attack/` | id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/claim-rewards/` | id | да | application/json |
| POST | `/api/v2/events/dungeon-hunters/boss/{id}/enter/` | id | да | application/json |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/my-chests/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/my-participation/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/state/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/top/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/{id}/top/my-place/` | id | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/config/` |  | да |  |
| GET | `/api/v2/events/dungeon-hunters/boss/list-killed/` |  | да |  |
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
| GET | `/api/v2/events/dungeon-hunters/equipment/catalog/` |  | да |  |
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
| GET | `/api/v2/events/eventpoint-payments/` |  | да |  |
| POST | `/api/v2/events/new-year-madness/action/` |  | да | application/json |
| POST | `/api/v2/events/new-year-madness/buy/` |  | да | application/json |
| GET | `/api/v2/events/new-year-madness/change-team/` |  | да |  |
| POST | `/api/v2/events/new-year-madness/change-team/` |  | да | application/json |
| GET | `/api/v2/events/new-year-madness/collect/` |  | да |  |
| POST | `/api/v2/events/new-year-madness/collect/` |  | да | application/json |
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

## Формы - `forms` (11)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/forms/billing/tickets/` |  | да |  |
| GET | `/api/forms/cards/` |  | да |  |
| GET | `/api/forms/chapters/` |  | да |  |
| GET | `/api/forms/coins-payments/` |  | да |  |
| GET | `/api/forms/creators/` |  | да |  |
| GET | `/api/forms/friends/statuses/` |  | да |  |
| GET | `/api/forms/panel/` |  | да |  |
| GET | `/api/forms/payments/` |  | да |  |
| GET | `/api/forms/publishers/` |  | да |  |
| GET | `/api/forms/titles/` |  | да |  |
| GET | `/api/forms/users/` |  | да |  |

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

## Служебные функции - `functions` (18)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/functions/check-vk-group/` |  | да |  |
| GET | `/api/functions/feature-flags/site/` |  | да |  |
| GET | `/api/functions/feature-flags/user/` |  | да |  |
| GET | `/api/functions/health-check/` |  | да |  |
| GET | `/api/functions/popup/` |  | да |  |
| POST | `/api/functions/search-engine-indexing/` |  | да | application/json |
| GET | `/api/functions/sliders-schemes/` |  | да |  |
| POST | `/api/functions/vk-bot-v2/` |  | да | application/json |
| GET | `/api/v2/functions/ai/title-character-config/` |  | да |  |
| GET | `/api/v2/functions/ai/title-character-config/{character_id}/` | character_id | да |  |
| GET | `/api/v2/functions/banned-words/` |  | да |  |
| GET | `/api/v2/functions/banner-sliders/` |  | да |  |
| GET | `/api/v2/functions/mobile-app/versions/` |  | да |  |
| POST | `/api/v2/functions/mobile-app/versions/` |  | да | application/json |
| GET | `/api/v2/functions/mobile-app/versions/{version}/` | version | да |  |
| PATCH | `/api/v2/functions/mobile-app/versions/{version}/` | version | да | application/json |
| PUT | `/api/v2/functions/mobile-app/versions/{version}/` | version | да | application/json |
| GET | `/api/v2/functions/mobile-app/versions/{version}/check/` | version | да |  |

## houses - `houses` (29)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/houses/characters/` |  | да |  |
| GET | `/api/houses/furniture/` |  | да |  |
| GET | `/api/houses/furniture-collections/` |  | да |  |
| GET | `/api/houses/furniture-collections/{furniture_collection_id}/` | furniture_collection_id | да |  |
| GET | `/api/houses/gacha/history/` |  | да |  |
| POST | `/api/houses/gacha/pull/` |  | да | application/json |
| GET | `/api/houses/game-shop-item-purchases/` |  | да |  |
| GET | `/api/houses/game-shop-items/` |  | да |  |
| GET | `/api/houses/game-shop-items/{game_shop_item_id}/` | game_shop_item_id | да |  |
| POST | `/api/houses/game-shop-items/buy/` |  | да | application/json |
| POST | `/api/houses/hard-currency/buy/` |  | да | application/json |
| POST | `/api/houses/join/` |  | да | application/json |
| GET | `/api/houses/player-characters/` |  | да |  |
| GET | `/api/houses/player-characters/{player_character_id}/` | player_character_id | да |  |
| POST | `/api/houses/player-characters/level-up/` |  | да | application/json |
| POST | `/api/houses/player-characters/upgrade-potential/` |  | да | application/json |
| POST | `/api/houses/player-floors/buy/` |  | да | application/json |
| GET | `/api/houses/player-furniture/` |  | да |  |
| GET | `/api/houses/player-furniture/{player_furniture_id}/` | player_furniture_id | да |  |
| GET | `/api/houses/player-houses/{player_house_id}/` | player_house_id | да |  |
| POST | `/api/houses/player-houses/buy/` |  | да | application/json |
| GET | `/api/houses/player-rooms/{player_room_id}/` | player_room_id | да |  |
| POST | `/api/houses/player-rooms/buy/` |  | да | application/json |
| POST | `/api/houses/player-rooms/collect-income/` |  | да | application/json |
| POST | `/api/houses/player-rooms/place-character/` |  | да | application/json |
| POST | `/api/houses/player-rooms/remove-character/` |  | да | application/json |
| POST | `/api/houses/player-rooms/update-furniture/` |  | да | application/json |
| GET | `/api/houses/players/` |  | да |  |
| GET | `/api/houses/players/{player_id}/` | player_id | да |  |

## Инвентарь - `inventory` (65)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/inventory/{title_id}/cards/` | title_id | да |  |
| POST | `/api/inventory/{user_id}/cards/merge/` | user_id | да | application/json |
| POST | `/api/inventory/cards/` |  | да | application/json |
| GET | `/api/inventory/cards/{id}/` | id | да |  |
| PUT | `/api/inventory/cards/{id}/` | id | да | application/json |
| PUT | `/api/inventory/cards/{id}/replace/` | id | да | application/json |
| GET | `/api/inventory/catalog/` |  | да |  |
| GET | `/api/inventory/character/{character_id}/cards/` | character_id | да |  |
| GET | `/api/v2/inventory/{user_id}/` | user_id | да |  |
| PUT | `/api/v2/inventory/{user_id}/cards/manage/exchangeable/` | user_id | да | application/json |
| PUT | `/api/v2/inventory/{user_id}/cards/manage/favorite/` | user_id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/collections/` | user_id | да |  |
| POST | `/api/v2/inventory/{user_id}/collections/` | user_id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/exchanges/` | user_id | да |  |
| POST | `/api/v2/inventory/{user_id}/exchanges/` | user_id | да | application/json |
| GET | `/api/v2/inventory/{user_id}/exchanges/{id}/` | user_id, id | да |  |
| PATCH | `/api/v2/inventory/{user_id}/exchanges/{id}/` | user_id, id | да | application/json |
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
| GET | `/api/v2/inventory/decks/aggregated/` |  | да |  |
| GET | `/api/v2/inventory/decks/awakened/` |  | да |  |
| POST | `/api/v2/inventory/decks/awakened/{deck_id}/open/` | deck_id | да | application/json |
| POST | `/api/v2/inventory/delete-card/` |  | да | application/json |
| GET | `/api/v2/inventory/items/cards/{user_id}/` | user_id | да |  |
| POST | `/api/v2/inventory/items/chests/{user_chest_id}/open/` | user_chest_id | да | application/json |
| GET | `/api/v2/inventory/items/chests/{user_id}/` | user_id | да |  |
| GET | `/api/v2/inventory/items/customizations/{user_id}/` | user_id | да |  |
| GET | `/api/v2/inventory/items/moments/{user_id}/` | user_id | да |  |
| GET | `/api/v2/inventory/items/promocodes/` |  | да |  |
| POST | `/api/v2/inventory/items/put-on/` |  | да | application/json |
| POST | `/api/v2/inventory/items/take-off/` |  | да | application/json |
| GET | `/api/v2/inventory/requests/status/` |  | да |  |
| GET | `/api/v2/inventory/upgrades/` |  | да |  |
| POST | `/api/v2/inventory/wishes/` |  | да | application/json |
| DELETE | `/api/v2/inventory/wishes/{card_id}/` | card_id | да |  |
| GET | `/api/v2/inventory/wishes/{card_id}/` | card_id | да |  |
| GET | `/api/v2/inventory/wishes/{card_id}/{user_id}/` | card_id, user_id | да |  |
| GET | `/api/v2/inventory/wishes/{user_id}/has_wishes/` | user_id | да |  |
| DELETE | `/api/v2/inventory/wishes/bulk/` |  | да |  |
| PUT | `/api/v2/inventory/wishes/bulk/` |  | да | application/json |
| GET | `/api/v2/inventory/wishes/intersection/{partner_id}/` | partner_id | да |  |
| GET | `/api/v2/inventory/wishes/users/{user_id}/` | user_id | да |  |
| GET | `/api/v3/inventory/items/cards/{user_id}/` | user_id | да |  |
| GET | `/api/v3/inventory/wishes/intersection/{partner_id}/` | partner_id | да |  |

## panel - `panel` (134)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| POST | `/api/panel/withdraw/` |  | да | application/json |
| GET | `/api/v2/panel/advices/` |  | да |  |
| GET | `/api/v2/panel/bans/` |  | да |  |
| POST | `/api/v2/panel/bans/` |  | да | application/json |
| DELETE | `/api/v2/panel/bans/{id}/` | id | да |  |
| GET | `/api/v2/panel/bans/{id}/` | id | да |  |
| PUT | `/api/v2/panel/bans/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/feedbacks/` |  | да |  |
| DELETE | `/api/v2/panel/feedbacks/{id}/` | id | да |  |
| GET | `/api/v2/panel/feedbacks/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/feedbacks/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/feedbacks/{id}/` | id | да | application/json |
| POST | `/api/v2/panel/feedbacks/send/` |  | да | application/json |
| GET | `/api/v2/panel/info/` |  | да |  |
| GET | `/api/v2/panel/items-requests/` |  | да |  |
| GET | `/api/v2/panel/items-requests/{request_id}/` | request_id | да |  |
| PUT | `/api/v2/panel/items-requests/{request_id}/` | request_id | да | application/json |
| GET | `/api/v2/panel/models/achievements/` |  | да |  |
| GET | `/api/v2/panel/models/badges/` |  | да |  |
| GET | `/api/v2/panel/models/black-list/` |  | да |  |
| GET | `/api/v2/panel/models/branches/` |  | да |  |
| GET | `/api/v2/panel/models/branches/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/models/branches/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/models/branches/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/models/cards/` |  | да |  |
| GET | `/api/v2/panel/models/cards/{id}/` | id | да |  |
| POST | `/api/v2/panel/models/cards/{id}/notify-delete/` | id | да | application/json |
| GET | `/api/v2/panel/models/cards/history/` |  | да |  |
| GET | `/api/v2/panel/models/categories/` |  | да |  |
| POST | `/api/v2/panel/models/categories/` |  | да | application/json |
| DELETE | `/api/v2/panel/models/categories/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/categories/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/models/categories/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/models/categories/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/models/chapters/` |  | да |  |
| GET | `/api/v2/panel/models/characters/` |  | да |  |
| POST | `/api/v2/panel/models/characters/` |  | да | application/json |
| DELETE | `/api/v2/panel/models/characters/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/characters/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/models/characters/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/models/characters/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/models/clubs/` |  | да |  |
| GET | `/api/v2/panel/models/coins-payments/` |  | да |  |
| POST | `/api/v2/panel/models/coins-payments/` |  | да | application/json |
| GET | `/api/v2/panel/models/coins-payments/{uuid}/` | uuid | да |  |
| GET | `/api/v2/panel/models/comments/` |  | да |  |
| DELETE | `/api/v2/panel/models/comments/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/comments/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/models/comments/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/models/comments/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/models/creators/` |  | да |  |
| GET | `/api/v2/panel/models/creators/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/forum/posts/` |  | да |  |
| GET | `/api/v2/panel/models/forum/posts/tags/` |  | да |  |
| GET | `/api/v2/panel/models/genres/` |  | да |  |
| POST | `/api/v2/panel/models/genres/` |  | да | application/json |
| DELETE | `/api/v2/panel/models/genres/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/genres/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/models/genres/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/models/genres/{id}/` | id | да | application/json |
| DELETE | `/api/v2/panel/models/inventory-cards/` |  | да |  |
| GET | `/api/v2/panel/models/inventory-cards/` |  | да |  |
| POST | `/api/v2/panel/models/inventory-cards/` |  | да | application/json |
| DELETE | `/api/v2/panel/models/inventory-cards/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/moments/` |  | да |  |
| GET | `/api/v2/panel/models/payments/` |  | да |  |
| GET | `/api/v2/panel/models/payments/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/payments/user/{user_id}/money/` | user_id | да |  |
| GET | `/api/v2/panel/models/payments/user/{user_id}/tickets/` | user_id | да |  |
| POST | `/api/v2/panel/models/promotions/` |  | да | application/json |
| GET | `/api/v2/panel/models/publisher_invitations/` |  | да |  |
| GET | `/api/v2/panel/models/publisher_invitations/{invitation_id}/` | invitation_id | да |  |
| GET | `/api/v2/panel/models/publishers/` |  | да |  |
| GET | `/api/v2/panel/models/publishers/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/shop/decks/` |  | да |  |
| GET | `/api/v2/panel/models/shop/decks/guarantors/` |  | да |  |
| GET | `/api/v2/panel/models/shop/decks/levels/` |  | да |  |
| GET | `/api/v2/panel/models/shop/emojis/` |  | да |  |
| GET | `/api/v2/panel/models/shop/emojis/packs/` |  | да |  |
| GET | `/api/v2/panel/models/shop/image-items/` |  | да |  |
| GET | `/api/v2/panel/models/shop/items/` |  | да |  |
| GET | `/api/v2/panel/models/shop/themes/` |  | да |  |
| POST | `/api/v2/panel/models/shop/themes/` |  | да | application/json |
| GET | `/api/v2/panel/models/titles/` |  | да |  |
| GET | `/api/v2/panel/models/titles/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/user_buy/` |  | да |  |
| GET | `/api/v2/panel/models/user_buy/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/user-notifications/` |  | да |  |
| POST | `/api/v2/panel/models/user-notifications/` |  | да | application/json |
| GET | `/api/v2/panel/models/users/` |  | да |  |
| GET | `/api/v2/panel/models/users/{id}/` | id | да |  |
| GET | `/api/v2/panel/models/users/{user_id}/payments` | user_id | да |  |
| GET | `/api/v2/panel/models/users/{user_id}/payments/{id}/` | user_id, id | да |  |
| GET | `/api/v2/panel/models/users/{user_id}/upgrades/` | user_id | да |  |
| GET | `/api/v2/panel/models/users/decks/` |  | да |  |
| POST | `/api/v2/panel/models/users/decks/` |  | да |  |
| GET | `/api/v2/panel/models/volumes/` |  | да |  |
| GET | `/api/v2/panel/moderator-requests/` |  | да |  |
| GET | `/api/v2/panel/moderator-requests/{id}/` | id | да |  |
| PUT | `/api/v2/panel/moderator-requests/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/moderator-requests/info/` |  | да |  |
| GET | `/api/v2/panel/notes/` |  | да |  |
| POST | `/api/v2/panel/notes/` |  | да | application/json |
| DELETE | `/api/v2/panel/notes/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/notes/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/notes/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/reports/` |  | да |  |
| GET | `/api/v2/panel/reports/{id}/` | id | да |  |
| PATCH | `/api/v2/panel/reports/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/reports/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/reports/send/` |  | да |  |
| POST | `/api/v2/panel/reports/send/` |  | да | application/json |
| GET | `/api/v2/panel/requests/` |  | да |  |
| GET | `/api/v2/panel/requests/{id}/` | id | да |  |
| PUT | `/api/v2/panel/requests/{id}/` | id | да | application/json |
| GET | `/api/v2/panel/requests/config/` |  | да |  |
| GET | `/api/v2/panel/requests/config/{id}/` | id | да |  |
| PUT | `/api/v2/panel/requests/config/{id}/` | id | да | application/json |
| PUT | `/api/v2/panel/requests/config/parameters/cards/` |  | да | application/json |
| GET | `/api/v2/panel/requests/count/` |  | да |  |
| GET | `/api/v2/panel/statistics/` |  | да |  |
| GET | `/api/v2/panel/strikes/` |  | да |  |
| POST | `/api/v2/panel/strikes/` |  | да | application/json |
| DELETE | `/api/v2/panel/strikes/{id}/` | id | да |  |
| GET | `/api/v2/panel/strikes/{id}/` | id | да |  |
| GET | `/api/v2/panel/tickets/` |  | да |  |
| POST | `/api/v2/panel/tickets/` |  | да | application/json |
| GET | `/api/v2/panel/tickets/{id}/` | id | да |  |
| POST | `/api/v2/panel/withdraw/` |  | да | application/json |
| GET | `/api/v2/panel/withdraw/queue/` |  | да |  |
| GET | `/api/v2/panel/withdraw/queue/{queue_id}/` | queue_id | да |  |
| GET | `/api/v2/panel/withdraw/receipts/` |  | да |  |
| GET | `/api/v2/panel/withdraw/receipts/{receipt_id}/` | receipt_id | да |  |
| PUT | `/api/v2/panel/withdraw/receipts/{receipt_id}/` | receipt_id | да | application/json |

## Издатели - `publishers` (46)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/publishers/` |  | да |  |
| POST | `/api/publishers/` |  | да | application/json |
| PUT | `/api/publishers/` |  | да | application/json |
| GET | `/api/publishers/{publisher_dir}/` | publisher_dir | да |  |
| POST | `/api/publishers/{publisher_dir}/` | publisher_dir | да | application/json |
| PUT | `/api/publishers/{publisher_dir}/` | publisher_dir | да | application/json |
| GET | `/api/publishers/{publisher_dir}/statistics/` | publisher_dir | да |  |
| DELETE | `/api/publishers/{publisher_id}/cards/` | publisher_id | да |  |
| GET | `/api/publishers/{publisher_id}/cards/` | publisher_id | да |  |
| POST | `/api/publishers/{publisher_id}/cards/` | publisher_id | да | application/json |
| GET | `/api/publishers/{publisher_id}/contract/` | publisher_id | да |  |
| POST | `/api/publishers/{publisher_id}/contract/` | publisher_id | да | application/json |
| GET | `/api/publishers/{publisher_id}/contract-files/` | publisher_id | да |  |
| POST | `/api/publishers/{publisher_id}/contract-files/` | publisher_id | да | application/json |
| GET | `/api/publishers/{publisher_id}/contractors/` | publisher_id | да |  |
| POST | `/api/publishers/{publisher_id}/contractors/` | publisher_id | да | application/json |
| GET | `/api/publishers/{publisher_id}/statistics/csv/` | publisher_id | да |  |
| GET | `/api/publishers/{publisher_id}/withdraw/` | publisher_id | да |  |
| GET | `/api/publishers/contracts/{contract_id}/acts/` | contract_id | да |  |
| GET | `/api/publishers/contracts/{contract_id}/acts/{act_id}/download/` | contract_id, act_id | да |  |
| POST | `/api/publishers/contract-verify/` |  | да | application/json |
| GET | `/api/v2/publishers/` |  | да |  |
| POST | `/api/v2/publishers/` |  | да | application/json |
| PUT | `/api/v2/publishers/` |  | да | application/json |
| GET | `/api/v2/publishers/{dir}/members/` | dir | да |  |
| GET | `/api/v2/publishers/{publisher_dir}/` | publisher_dir | да |  |
| POST | `/api/v2/publishers/{publisher_dir}/` | publisher_dir | да | application/json |
| PUT | `/api/v2/publishers/{publisher_dir}/` | publisher_dir | да | application/json |
| POST | `/api/v2/publishers/{publisher_id}/add-promo/` | publisher_id | да | application/json |
| GET | `/api/v2/publishers/{publisher_id}/comments/` | publisher_id | да |  |
| DELETE | `/api/v2/publishers/{publisher_id}/members/{user_id}/` | publisher_id, user_id | да |  |
| GET | `/api/v2/publishers/{publisher_id}/members/{user_id}/` | publisher_id, user_id | да |  |
| PATCH | `/api/v2/publishers/{publisher_id}/members/{user_id}/` | publisher_id, user_id | да | application/json |
| POST | `/api/v2/publishers/{publisher_id}/members/{user_id}/` | publisher_id, user_id | да | application/json |
| PUT | `/api/v2/publishers/{publisher_id}/members/{user_id}/` | publisher_id, user_id | да | application/json |
| POST | `/api/v2/publishers/{publisher_id}/members/accept/` | publisher_id | да | application/json |
| PATCH | `/api/v2/publishers/{publisher_id}/members/exit/` | publisher_id | да | application/json |
| PUT | `/api/v2/publishers/{publisher_id}/members/exit/` | publisher_id | да | application/json |
| GET | `/api/v2/publishers/{publisher_id}/strikes/` | publisher_id | да |  |
| GET | `/api/v2/publishers/{publisher_id}/strikes/{id}/` | publisher_id, id | да |  |
| GET | `/api/v2/publishers/{publisher_id}/withdraw/` | publisher_id | да |  |
| GET | `/api/v2/publishers/invitations/` |  | да |  |
| POST | `/api/v2/publishers/invitations/` |  | да | application/json |
| DELETE | `/api/v2/publishers/invitations/{invitation_id}/` | invitation_id | да |  |
| PUT | `/api/v2/publishers/invitations/{invitation_id}/` | invitation_id | да | application/json |
| GET | `/api/v2/publishers/rights/` |  | да |  |

## Викторины - `quizzes` (15)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/quizzes/` |  | да |  |
| POST | `/api/v2/quizzes/` |  | да | application/json |
| DELETE | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да |  |
| GET | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да |  |
| PUT | `/api/v2/quizzes/{quiz_id}/` | quiz_id | да | application/json |
| POST | `/api/v2/quizzes/{quiz_id}/answers/` | quiz_id | да | application/json |
| POST | `/api/v2/quizzes/{quiz_id}/ban/` | quiz_id | да | application/json |
| DELETE | `/api/v2/quizzes/{quiz_id}/options/{question_id}/{option_id}/` | quiz_id, question_id, option_id | да |  |
| PUT | `/api/v2/quizzes/{quiz_id}/options/{question_id}/{option_id}/` | quiz_id, question_id, option_id | да | application/json |
| DELETE | `/api/v2/quizzes/{quiz_id}/questions/{question_id}/` | quiz_id, question_id | да |  |
| PUT | `/api/v2/quizzes/{quiz_id}/questions/{question_id}/` | quiz_id, question_id | да | application/json |
| GET | `/api/v2/quizzes/{quiz_id}/questions/{question_id}/answers/` | quiz_id, question_id | да |  |
| POST | `/api/v2/quizzes/{quiz_id}/recover/` | quiz_id | да | application/json |
| GET | `/api/v2/quizzes/{quiz_id}/users/{option_id}/` | quiz_id, option_id | да |  |
| GET | `/api/v2/quizzes/statistics/` |  | да |  |

## schema - `schema` (1)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/schema/` |  | да |  |

## Поиск - `search` (17)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/search/` |  | да |  |
| GET | `/api/search/catalog/` |  | да |  |
| GET | `/api/search/internal/carditems/` |  | да |  |
| GET | `/api/search/internal/characters/` |  | да |  |
| GET | `/api/search/internal/clubs/` |  | да |  |
| GET | `/api/search/internal/creators/` |  | да |  |
| GET | `/api/search/internal/publishers/` |  | да |  |
| GET | `/api/search/internal/titlecharacters/` |  | да |  |
| GET | `/api/search/internal/titles/` |  | да |  |
| GET | `/api/search/internal/users/` |  | да |  |
| GET | `/api/search/popular/` |  | да |  |
| POST | `/api/search/popular/` |  | да | application/json |
| GET | `/api/v2/search/` |  | да |  |
| GET | `/api/v2/search/catalog/` |  | да |  |
| GET | `/api/v2/search/characters/catalog/` |  | да |  |
| GET | `/api/v2/search/popular/` |  | да |  |
| POST | `/api/v2/search/popular/` |  | да | application/json |

## service - `service` (1)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/service/users/` |  | да |  |

## Магазин - `shop` (10)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/shop/` |  | да |  |
| POST | `/api/v2/shop/` |  | да | application/json |
| GET | `/api/v2/shop/{dir}/` | dir | да |  |
| POST | `/api/v2/shop/buy/{id}/` | id | да | application/json |
| GET | `/api/v2/shop/counts/` |  | да |  |
| GET | `/api/v2/shop/decks/` |  | да |  |
| GET | `/api/v2/shop/decks/{id}/` | id | да |  |
| GET | `/api/v2/shop/decks/awakened/` |  | да |  |
| GET | `/api/v2/shop/decks/awakened/{id}/` | id | да |  |
| GET | `/api/v2/shop/stickerpacks/` |  | да |  |

## Подписка - `subscription` (2)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/v2/subscription/` |  | да |  |
| POST | `/api/v2/subscription/trial/` |  | да | application/json |

## Тайтлы - `titles` (103)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/titles/` |  | да |  |
| POST | `/api/titles/` |  | да | application/json |
| GET | `/api/titles/{title_dir}/` | title_dir | да |  |
| PUT | `/api/titles/{title_dir}/` | title_dir | да | application/json |
| GET | `/api/titles/{title_dir}/similar/` | title_dir | да |  |
| POST | `/api/titles/{title_dir}/similar/` | title_dir | да | application/json |
| PUT | `/api/titles/{title_dir}/similar/` | title_dir | да | application/json |
| GET | `/api/titles/banner/` |  | да |  |
| GET | `/api/titles/chapters/` |  | да |  |
| POST | `/api/titles/chapters/` |  | да | application/json |
| PUT | `/api/titles/chapters/` |  | да | application/json |
| DELETE | `/api/titles/chapters/{chapter_id}/` | chapter_id | да |  |
| GET | `/api/titles/chapters/{chapter_id}/` | chapter_id | да |  |
| PUT | `/api/titles/chapters/{chapter_id}/` | chapter_id | да | application/json |
| GET | `/api/titles/chapters/{chapter_id}/download/` | chapter_id | да |  |
| GET | `/api/titles/characters/` |  | да |  |
| POST | `/api/titles/characters/` |  | да | application/json |
| PUT | `/api/titles/characters/` |  | да | application/json |
| GET | `/api/titles/characters/{id}/` | id | да |  |
| POST | `/api/titles/characters/{id}/` | id | да | application/json |
| PUT | `/api/titles/characters/{id}/` | id | да | application/json |
| GET | `/api/titles/collections/` |  | да |  |
| GET | `/api/titles/collections/{collection_id}/` | collection_id | да |  |
| POST | `/api/titles/creators/` |  | да | application/json |
| GET | `/api/titles/creators/{creator_id}/titles/` | creator_id | да |  |
| POST | `/api/titles/creators/{creator_id}/titles/` | creator_id | да | application/json |
| GET | `/api/titles/creators/{id}/` | id | да |  |
| PATCH | `/api/titles/creators/{id}/` | id | да | application/json |
| PUT | `/api/titles/creators/{id}/` | id | да | application/json |
| GET | `/api/titles/last-chapters/` |  | да |  |
| GET | `/api/titles/top-100/` |  | да |  |
| GET | `/api/titles/volumes/{branch_id}/` | branch_id | да |  |
| GET | `/api/v2/titles/` |  | да |  |
| POST | `/api/v2/titles/` |  | да | application/json |
| GET | `/api/v2/titles/{title__dir}/additional/` | title__dir | да |  |
| PATCH | `/api/v2/titles/{title__dir}/additional/` | title__dir | да | application/json |
| POST | `/api/v2/titles/{title__dir}/additional/` | title__dir | да | application/json |
| PUT | `/api/v2/titles/{title__dir}/additional/` | title__dir | да | application/json |
| GET | `/api/v2/titles/{title__dir}/anime/` | title__dir | да |  |
| GET | `/api/v2/titles/{title__dir}/relations/` | title__dir | да |  |
| PATCH | `/api/v2/titles/{title__dir}/relations/` | title__dir | да | application/json |
| POST | `/api/v2/titles/{title__dir}/relations/` | title__dir | да | application/json |
| PUT | `/api/v2/titles/{title__dir}/relations/` | title__dir | да | application/json |
| GET | `/api/v2/titles/{title__dir}/voiceover/` | title__dir | да |  |
| GET | `/api/v2/titles/{title__id}/characters/` | title__id | да |  |
| GET | `/api/v2/titles/{title_dir}/` | title_dir | да |  |
| PUT | `/api/v2/titles/{title_dir}/` | title_dir | да | application/json |
| GET | `/api/v2/titles/{title_dir}/chapters/available/` | title_dir | да |  |
| GET | `/api/v2/titles/{title_dir}/similar/` | title_dir | да |  |
| PATCH | `/api/v2/titles/{title_dir}/similar/` | title_dir | да | application/json |
| POST | `/api/v2/titles/{title_dir}/similar/` | title_dir | да | application/json |
| PUT | `/api/v2/titles/{title_dir}/similar/` | title_dir | да | application/json |
| GET | `/api/v2/titles/{title_id}/my-progress/` | title_id | да |  |
| POST | `/api/v2/titles/{title_id}/my-progress/` | title_id | да | application/json |
| GET | `/api/v2/titles/banner/` |  | да |  |
| GET | `/api/v2/titles/categories/` |  | да |  |
| GET | `/api/v2/titles/chapters/` |  | да |  |
| PATCH | `/api/v2/titles/chapters/` |  | да | application/json |
| POST | `/api/v2/titles/chapters/` |  | да | application/json |
| PUT | `/api/v2/titles/chapters/` |  | да | application/json |
| DELETE | `/api/v2/titles/chapters/{chapter_id}/` | chapter_id | да |  |
| GET | `/api/v2/titles/chapters/{chapter_id}/` | chapter_id | да |  |
| PATCH | `/api/v2/titles/chapters/{chapter_id}/` | chapter_id | да | application/json |
| PUT | `/api/v2/titles/chapters/{chapter_id}/` | chapter_id | да | application/json |
| POST | `/api/v2/titles/chapters/parse-docs/` |  | да | application/json |
| POST | `/api/v2/titles/characters/` |  | да | application/json |
| GET | `/api/v2/titles/characters/{id}/` | id | да |  |
| PATCH | `/api/v2/titles/characters/{id}/` | id | да | application/json |
| PUT | `/api/v2/titles/characters/{id}/` | id | да | application/json |
| GET | `/api/v2/titles/collections/` |  | да |  |
| POST | `/api/v2/titles/collections/` |  | да | application/json |
| DELETE | `/api/v2/titles/collections/{id}/` | id | да |  |
| GET | `/api/v2/titles/collections/{id}/` | id | да |  |
| PATCH | `/api/v2/titles/collections/{id}/` | id | да | application/json |
| PUT | `/api/v2/titles/collections/{id}/` | id | да | application/json |
| POST | `/api/v2/titles/creators/` |  | да | application/json |
| GET | `/api/v2/titles/creators/{creator_id}/titles/` | creator_id | да |  |
| POST | `/api/v2/titles/creators/{creator_id}/titles/` | creator_id | да | application/json |
| GET | `/api/v2/titles/creators/{id}/` | id | да |  |
| PATCH | `/api/v2/titles/creators/{id}/` | id | да | application/json |
| PUT | `/api/v2/titles/creators/{id}/` | id | да | application/json |
| GET | `/api/v2/titles/genres/` |  | да |  |
| GET | `/api/v2/titles/last-chapters/` |  | да |  |
| GET | `/api/v2/titles/moments/` |  | да |  |
| POST | `/api/v2/titles/moments/` |  | да | application/json |
| DELETE | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да |  |
| GET | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да |  |
| PATCH | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да | application/json |
| PUT | `/api/v2/titles/moments/{moment_dir}/` | moment_dir | да | application/json |
| PUT | `/api/v2/titles/moments/{moment_dir}/ban/` | moment_dir | да | application/json |
| POST | `/api/v2/titles/moments/{moment_dir}/view/` | moment_dir | да | application/json |
| GET | `/api/v2/titles/moments/catalog/` |  | да |  |
| GET | `/api/v2/titles/moments/tags/` |  | да |  |
| PUT | `/api/v2/titles/next-chapter-date/{branch_id}/` | branch_id | да | application/json |
| GET | `/api/v2/titles/publisher/{publisher_id}/` | publisher_id | да |  |
| GET | `/api/v2/titles/sliders/` |  | да |  |
| GET | `/api/v2/titles/sliders/{dir}/` | dir | да |  |
| GET | `/api/v2/titles/sliders/types/` |  | да |  |
| GET | `/api/v2/titles/top/` |  | да |  |
| GET | `/api/v2/titles/top/tabs/` |  | да |  |
| GET | `/api/v2/titles/volumes/{branch_id}/` | branch_id | да |  |
| POST | `/api/v2/titles/volumes/{branch_id}/` | branch_id | да | application/json |
| GET | `/api/v3/titles/` |  | да |  |

## Пользователи - `users` (131)

| Метод | Путь | Параметры пути | Bearer | Тип содержимого |
|---|---|---|:---:|---|
| GET | `/api/users/{user_id}/` | user_id | да |  |
| DELETE | `/api/users/{user_id}/bookmarks/` | user_id | да |  |
| GET | `/api/users/{user_id}/bookmarks/` | user_id | да |  |
| POST | `/api/users/{user_id}/bookmarks/` | user_id | да | application/json |
| PUT | `/api/users/{user_id}/bookmarks/` | user_id | да | application/json |
| GET | `/api/users/{user_id}/comments/` | user_id | да |  |
| DELETE | `/api/users/{user_id}/history/` | user_id | да |  |
| GET | `/api/users/{user_id}/history/` | user_id | да |  |
| GET | `/api/users/{user_id}/payments/` | user_id | да |  |
| POST | `/api/users/{user_id}/payments/` | user_id | да | application/json |
| DELETE | `/api/users/{user_id}/user_bookmarks/` | user_id | да |  |
| GET | `/api/users/{user_id}/user_bookmarks/` | user_id | да |  |
| POST | `/api/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| PUT | `/api/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| POST | `/api/users/activate/` |  | да | application/json |
| GET | `/api/users/auto-login/` |  | да |  |
| POST | `/api/users/auto-login/` |  | да | application/json |
| DELETE | `/api/users/bookmarks/` |  | да |  |
| GET | `/api/users/bookmarks/` |  | да |  |
| POST | `/api/users/bookmarks/` |  | да | application/json |
| PUT | `/api/users/bookmarks/` |  | да | application/json |
| POST | `/api/users/bookmarks/import/` |  | да | application/json |
| DELETE | `/api/users/current/` |  | да |  |
| GET | `/api/users/current/` |  | да |  |
| PUT | `/api/users/current/` |  | да | application/json |
| POST | `/api/users/login/` |  | да | application/json |
| DELETE | `/api/users/notifications/` |  | да |  |
| GET | `/api/users/notifications/` |  | да |  |
| PUT | `/api/users/notifications/` |  | да | application/json |
| POST | `/api/users/password-reset/` |  | да | application/json |
| PUT | `/api/users/password-reset/` |  | да | application/json |
| POST | `/api/users/revoke-token/` |  | да | application/json |
| POST | `/api/users/signup/` |  | да | application/json |
| POST | `/api/users/social/` |  | да | application/json |
| GET | `/api/users/top/` |  | да |  |
| POST | `/api/users/utm-user-history/` |  | да | application/json |
| GET | `/api/v2/users/{user_id}/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/achievements/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/badges/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/bookmarks/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/bookmarks/` | user_id | да |  |
| PATCH | `/api/v2/users/{user_id}/bookmarks/` | user_id | да | application/json |
| POST | `/api/v2/users/{user_id}/bookmarks/` | user_id | да | application/json |
| PUT | `/api/v2/users/{user_id}/bookmarks/` | user_id | да | application/json |
| GET | `/api/v2/users/{user_id}/clubs/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/favorite-cards/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/friends/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/friends/{friend_id}/` | user_id, friend_id | да |  |
| GET | `/api/v2/users/{user_id}/friends/{friend_id}/` | user_id, friend_id | да |  |
| PATCH | `/api/v2/users/{user_id}/friends/{friend_id}/` | user_id, friend_id | да | application/json |
| PUT | `/api/v2/users/{user_id}/friends/{friend_id}/` | user_id, friend_id | да | application/json |
| GET | `/api/v2/users/{user_id}/friends-requests/` | user_id | да |  |
| POST | `/api/v2/users/{user_id}/friends-requests/` | user_id | да | application/json |
| DELETE | `/api/v2/users/{user_id}/friends-requests/{request_id}/` | user_id, request_id | да |  |
| GET | `/api/v2/users/{user_id}/friends-requests/{request_id}/` | user_id, request_id | да |  |
| PATCH | `/api/v2/users/{user_id}/friends-requests/{request_id}/` | user_id, request_id | да | application/json |
| PUT | `/api/v2/users/{user_id}/friends-requests/{request_id}/` | user_id, request_id | да | application/json |
| GET | `/api/v2/users/{user_id}/history/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/history/delete/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/results/` | user_id | да |  |
| DELETE | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да |  |
| GET | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да |  |
| PATCH | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| POST | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| PUT | `/api/v2/users/{user_id}/user_bookmarks/` | user_id | да | application/json |
| PUT | `/api/v2/users/achievements/update/` |  | да | application/json |
| POST | `/api/v2/users/activate/` |  | да | application/json |
| POST | `/api/v2/users/admin/` |  | да | application/json |
| POST | `/api/v2/users/app-auth/code/` |  | да | application/json |
| POST | `/api/v2/users/app-auth/token/` |  | да | application/json |
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
| DELETE | `/api/v2/users/current/push-notifications/` |  | да |  |
| POST | `/api/v2/users/current/push-notifications/` |  | да | application/json |
| POST | `/api/v2/users/current/tour-reward/` |  | да | application/json |
| POST | `/api/v2/users/email-change/` |  | да | application/json |
| GET | `/api/v2/users/followers/` |  | да |  |
| GET | `/api/v2/users/friends-requests/friend/{friend_id}/` | friend_id | да |  |
| GET | `/api/v2/users/have-card/{card_id}/` | card_id | да |  |
| POST | `/api/v2/users/krakend/` |  | да | application/json |
| POST | `/api/v2/users/login/` |  | да | application/json |
| POST | `/api/v2/users/login/2fa/issue/` |  | да | application/json |
| POST | `/api/v2/users/login/2fa/verify/` |  | да | application/json |
| POST | `/api/v2/users/logout/` |  | да | application/json |
| POST | `/api/v2/users/logout-all/` |  | да | application/json |
| GET | `/api/v2/users/my-recommendations/` |  | да |  |
| GET | `/api/v2/users/notifications/` |  | да |  |
| POST | `/api/v2/users/notifications/create/` |  | да | application/json |
| POST | `/api/v2/users/notifications/delete/` |  | да | application/json |
| GET | `/api/v2/users/notifications/list-by-title/` |  | да |  |
| POST | `/api/v2/users/notifications/set-read/` |  | да | application/json |
| GET | `/api/v2/users/notifications/summary-by-title/` |  | да |  |
| GET | `/api/v2/users/notifications/summary-by-type/` |  | да |  |
| GET | `/api/v2/users/notifications/summary-total/` |  | да |  |
| POST | `/api/v2/users/password-change/` |  | да | application/json |
| PUT | `/api/v2/users/publishers-order/` |  | да | application/json |
| GET | `/api/v2/users/requests/` |  | да |  |
| GET | `/api/v2/users/requests/{id}/` | id | да |  |
| PUT | `/api/v2/users/requests/{id}/cancel/` | id | да | application/json |
| GET | `/api/v2/users/results/` |  | да |  |
| GET | `/api/v2/users/search-friends/` |  | да |  |
| GET | `/api/v2/users/sessions/` |  | да |  |
| DELETE | `/api/v2/users/sessions/{session_id}/` | session_id | да |  |
| POST | `/api/v2/users/set-email/` |  | да | application/json |
| POST | `/api/v2/users/signup/` |  | да | application/json |
| POST | `/api/v2/users/social/mangalib/` |  | да | application/json |
| POST | `/api/v2/users/social/mangalib/bind/` |  | да | application/json |
| POST | `/api/v2/users/social/senkuro/` |  | да | application/json |
| POST | `/api/v2/users/social/senkuro/bind/` |  | да | application/json |
| POST | `/api/v2/users/social/shikimori/` |  | да | application/json |
| POST | `/api/v2/users/social/shikimori/bind/` |  | да | application/json |
| POST | `/api/v2/users/social/vk/` |  | да | application/json |
| POST | `/api/v2/users/social/vk/bind/` |  | да | application/json |
| DELETE | `/api/v2/users/subscribtion/` |  | да |  |
| PUT | `/api/v2/users/subscribtion/` |  | да | application/json |
| GET | `/api/v2/users/subscribtions/` |  | да |  |
| GET | `/api/v2/users/tickets/` |  | да |  |
| POST | `/api/v2/users/token/refresh/` |  | да | application/json |
| GET | `/api/v2/users/top/` |  | да |  |
| GET | `/api/v2/users/top/place/` |  | да |  |
| POST | `/api/v3/users/password-change/` |  | да | application/json |
