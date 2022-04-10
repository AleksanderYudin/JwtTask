### **'Inside' Junior dev test task**

_____________
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
