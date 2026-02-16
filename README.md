# Question 5 – Task Management REST API

## Project Description

This project implements a simple Task Management (To-Do List) REST API using Spring Boot. 
The API allows creating, updating, retrieving, filtering, and deleting tasks.

All tasks are stored in an in-memory list.

---

## How to Run

1. Open the project in VS Code.
2. Open terminal in the project root folder.
3. Run:

   mvnw.cmd spring-boot:run

4. The application runs at:

   http://localhost:8080

---

## Base URL

http://localhost:8080/api/tasks

---

## Endpoints

- GET /api/tasks → Get all tasks
- GET /api/tasks/{taskId} → Get task by ID
- GET /api/tasks/status?completed=true/false → Filter by completion status
- GET /api/tasks/priority/{priority} → Filter by priority
- POST /api/tasks → Create new task
- PUT /api/tasks/{taskId} → Update task
- PATCH /api/tasks/{taskId}/complete → Mark task as completed
- DELETE /api/tasks/{taskId} → Delete task

---

## Testing

All endpoints were tested using Postman.
Screenshots are included in the screenshots folder.

---

## Technologies Used

- Java
- Spring Boot
- Maven
- Postman
