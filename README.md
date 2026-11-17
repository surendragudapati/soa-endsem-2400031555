# Spring Boot JWT Authentication Service

## Project Overview
A RESTful authentication service developed using Spring Boot. It supports user registration, secure login, password encryption, and JWT-based authentication.

## Technologies Used
- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Token)
- Swagger UI
- Maven

## Features
- User registration
- Email-based login
- BCrypt password encryption
- JWT token generation
- JWT token validation
- REST API documentation using Swagger

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Authenticate user and generate JWT |
| GET | `/api/auth/validate` | Validate JWT authentication |

## Database Configuration
Database: `auth_db`

Update the MySQL username and password in `application.properties` according to your local configuration.

## Run the Project
1. Clone the repository.
2. Open the `AuthService` folder in Spring Tool Suite or VS Code.
3. Configure MySQL.
4. Run `AuthServiceApplication.java`.
5. Open Swagger UI:

`http://localhost:8080/swagger-ui/index.html`

## Author
Surendra
