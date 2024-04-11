## Задание: Создание простой системы управления задачами

<details>
<summary>Полный текст задания</summary>
 Задание: Создание простой системы управления задачами

Описание:
Ваша задача - создать простую систему управления задачами с использованием Spring Boot и Spring Data JPA. 
Система должна позволять создавать, просматривать, обновлять и удалять задачи.

Требования:

Модель данных: Создайте сущность Task с полями: id, title, description, dueDate, completed.

Хранилище данных: Используйте Spring Data JPA для работы с базой данных.
Настройте соединение с базой данных (например, использование H2 для упрощения).

REST API: Создайте контроллер TaskController, который будет обрабатывать HTTP-запросы для операций с задачами (создание, чтение, обновление, удаление).

Реализуйте следующие методы:

GET /tasks - Получить список всех задач.

GET /tasks/{id} - Получить информацию о задаче по её id.

POST /tasks - Создать новую задачу.

PUT /tasks/{id} - Обновить информацию о задаче.

DELETE /tasks/{id} - Удалить задачу.

Тестирование:

Напишите unit-тесты для проверки функциональности вашего приложения.
Покройте тестами контроллеры и сервисы.
</details>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white "Java 11")
![Maven](https://img.shields.io/badge/Maven-green.svg?style=for-the-badge&logo=mockito&logoColor=white "Maven")
![Spring](https://img.shields.io/badge/Spring-blueviolet.svg?style=for-the-badge&logo=spring&logoColor=white "Spring")
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/git-%23121011.svg?style=for-the-badge&logo=github&logoColor=white "Git")
+ ЯП: *Java 17*
+ Автоматизация сборки: *Maven*
+ Фреймворк: *Spring*
+ База данных: *PostgreSQL*
+ Контроль версий: *Git*

Для запуска приложения необходимо в aplication.properties указать настройки БД
