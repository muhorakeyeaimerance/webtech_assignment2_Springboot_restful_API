# Question 4 – E-Commerce Product REST API

## Project Description

This project implements a simple E-Commerce Product Management REST API using Spring Boot. 
The API allows managing products, filtering, searching, pagination, and stock updates.

All data is stored in an in-memory list.

---

## How to Run

1. Open the project in VS Code.
2. Open terminal in the root folder.
3. Run:

   mvnw.cmd spring-boot:run

4. Application runs at:

   http://localhost:8080

---

## Base URL

http://localhost:8080/api/products

---

## Endpoints

- GET /api/products (with pagination)
- GET /api/products/{productId}
- GET /api/products/category/{category}
- GET /api/products/brand/{brand}
- GET /api/products/search?keyword=
- GET /api/products/price-range?min=&max=
- GET /api/products/in-stock
- POST /api/products
- PUT /api/products/{productId}
- PATCH /api/products/{productId}/stock
- DELETE /api/products/{productId}

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
