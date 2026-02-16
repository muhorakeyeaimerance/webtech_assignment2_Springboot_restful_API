User Profile API – Bonus Question
Description

This project is a User Profile Management REST API built using Spring Boot.
It allows creating, updating, deleting, searching, activating, and deactivating user profiles.

All responses are returned using a custom response object for better structure.

UserProfile Fields

userId (Long)

username (String)

email (String)

fullName (String)

age (int)

country (String)

bio (String)

active (boolean)

API Endpoints

GET /api/users → Get all users

GET /api/users/{userId} → Get user by ID

POST /api/users → Create new user

PUT /api/users/{userId} → Update user

DELETE /api/users/{userId} → Delete user

Search Endpoints

GET /api/users/search?username=value

GET /api/users/country/{country}

GET /api/users/age?min=18&max=30

Activate / Deactivate

PATCH /api/users/{userId}/activate

PATCH /api/users/{userId}/deactivate

How to Run

Open the project.

Run:

mvn spring-boot:run


Test endpoints using Postman at:

http://localhost:8080/api/users