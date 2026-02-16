# Question 1 - Library Book Management API

This project is a simple RESTful API built using Spring Boot for managing library books.

It was developed as part of the Spring Boot RESTful API assignment.

## Technologies Used

- Java
- Spring Boot
- Maven
- Spring Web
- Postman (for testing)

---

## Project Structure

src/main/java/com/example/question1libraryapi

- controller/library → Contains BookController
- model/library → Contains Book class

---

## How to Run the Application

1. Open the project in VS Code.
2. Make sure Java and Maven are installed.
3. Navigate to the project folder where pom.xml exists.
4. Run the following command:

mvn spring-boot:run

The application will start on:

http://localhost:8080

---

## API Endpoints

### 1. Get All Books

Method: GET  
URL:
http://localhost:8080/api/books

Response (200 OK):

[
  {
    "id": 1,
    "title": "Introduction to Algorithms",
    "author": "Thomas H. Cormen",
    "isbn": "9780262033848",
    "publicationYear": 2009
  },
  {
    "id": 2,
    "title": "Design Patterns",
    "author": "Erich Gamma",
    "isbn": "9780201633610",
    "publicationYear": 1994
  },
  {
    "id": 3,
    "title": "Computer Networks",
    "author": "Andrew S. Tanenbaum",
    "isbn": "9780132126953",
    "publicationYear": 2010
  }
]

---

### 2. Get Book By ID

Method: GET  
URL:
http://localhost:8080/api/books/{id}

Example:
http://localhost:8080/api/books/1

Responses:
- 200 OK (if found)
- 404 NOT FOUND (if not found)

---

### 3. Search Book By Title

Method: GET  
URL:
http://localhost:8080/api/books/search?title=algorithm

Response:
- 200 OK

---

### 4. Add New Book

Method: POST  
URL:
http://localhost:8080/api/books

Body (JSON):

{
  "id": 4,
  "title": "Java Fundamentals",
  "author": "John Smith",
  "isbn": "123456789",
  "publicationYear": 2022
}

Response:
- 201 CREATED

---

### 5. Delete Book

Method: DELETE  
URL:
http://localhost:8080/api/books/{id}

Example:
http://localhost:8080/api/books/4

Responses:
- 204 NO CONTENT (if deleted)
- 404 NOT FOUND (if not found)

---

## Testing

All endpoints were tested using Postman.

The API returns appropriate HTTP status codes:
- 200 OK
- 201 CREATED
- 204 NO CONTENT
- 404 NOT FOUND

---

## Author

Student Name: MUHORAKEYE Aimerance
Student ID: 26970
