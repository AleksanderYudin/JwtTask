### **'Inside' Junior dev test task**

_____________
<details><summary>Task</summary>
В БД создать пару sql табличек со связями (foreign keys)

Сделать HTTP POST эндпоинт, который получает данные в json вида:
{
name: "имя отправителя"
password: "пароль"
}
Этот эндпоинт проверяет пароль по БД и создает jwt токен, в который записывает данные: name: "имя отправителя",
и отправляет токен в ответ, тоже json вида:
{
token: "тут сгенерированный токен"
}

Сервер слушает и отвечает в какой-нибудь эндпоинт, в него на вход поступают данные в формате json:
Сообщения клиента-пользователя:
{
name:       "имя отправителя",
message:    "текст сообщение"
}
В заголовках указан Bearer токен, полученный из эндпоинта выше (между Bearer и полученным токеном должно быть нижнее подчеркивание).
Проверить токен, в случае успешной проверки токена, полученное сообщение сохранить в БД.

Если пришло сообщение вида:
{
name:       "имя отправителя",
message:    "history 10"
}
проверить токен, в случае успешной проверки токена отправить отправителю 10 последних сообщений из БД

Добавить описание и инструкцию по запуску и комментарии в коде, если изменяете формат сообщений, то подробное описание ендпоинтов и их полей.

Завернуть все компоненты в Docker, покрыть код тестами.

Проект необходимо выкладывать на github и docker hub. Обязательно наличие readme-файла.

Составить запросы (curl) через терминал для проверки работоспособности программы (приложить файл с запросами).
</details>

Prerequisites:

- Java 8+
- Maven
- MySql
- Docker (for containerising)
- Docker compose (for containerising)
______________

In order to run application you need to execute following command `docker-compose up -d`
from directory where docker-compose.yaml is located.
______________

### Request examples:

- Request for fetching bearer token:

`curl -H "Content-Type: application/json"  
--request POST   --data '{"username":"john smith", "password":"pass"}'   
http://localhost:8075/login`

- Request for message posting:

`curl -H "Content-Type: application/json"
-H "Authorization: Bearer_<YOUR_TOKEN>" --request POST
--data '{"username":"john smith", "message":"hi there!"}'
http://localhost:8075/messages`

- Request for message history:

`curl -H "Content-Type: application/json"
-H "Authorization: Bearer_<YOUR_TOKEN>"
--request POST --data '{"username":"john smith", "message":"history 3"}'
http://localhost:8075/messages`

#### Request examples for admins:

- Request for fetching bearer token:

`curl -H "Content-Type: application/json"  
--request POST   --data '{"username":"admin", "password":"pass"}'   
http://localhost:8075/login`

- Request for user registration:

`curl -H "Content-Type: application/json"
-H "Authorization: Bearer_<YOUR_TOKEN>"
--request POST --data '{"username":"<USER_NAME>", "password":"<PASSWORD>, "role":"ROLE_USER"}'
http://localhost:8075/admin/users`

- Request for getting user list:

`curl -H "Content-Type: application/json"
-H "Authorization: Bearer_<YOUR_TOKEN>"
--request GET
http://localhost:8075/admin/users`
