# Question 2 – Student Registration REST API

## Project Description
This project implements a simple Student Registration RESTful API using Spring Boot. 
The API allows managing students and performing filtering operations.

---

## How to Run the Application

1. Open the project in VS Code.
2. Open terminal inside the project folder.
3. Run:

   mvnw.cmd spring-boot:run

4. The application runs on:
   http://localhost:8080

---

## Endpoints

### 1. Get All Students
GET /api/students

Returns list of all students.

---

### 2. Get Student By ID
GET /api/students/{studentId}

Example:
GET /api/students/1

---

### 3. Get Students By Major
GET /api/students/major/{major}

Example:
GET /api/students/major/Computer Science

---

### 4. Filter Students By GPA
GET /api/students/filter?gpa={minGpa}

Example:
GET /api/students/filter?gpa=3.5

---

### 5. Create New Student
POST /api/students

Sample JSON:

{
  "studentId": 6,
  "firstName": "Samuel",
  "lastName": "Kamanzi",
  "email": "samuel@gmail.com",
  "major": "Computer Science",
  "gpa": 3.4
}

Returns 201 Created.

---

### 6. Update Student
PUT /api/students/{studentId}

Updates student details.

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
