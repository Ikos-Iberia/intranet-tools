# Intranet Tools

A web application designed to centralize and manage a company's internal tools. The project provides a secure REST API that allows administrators to manage users, departments, roles, tools and permissions, while authenticated users can access the resources they are authorized to use.

The application follows a layered architecture using Spring Boot and implements JWT authentication, role-based authorization and Swagger/OpenAPI documentation.

---

# 📚 Table of Contents

- [Description](#-description)
- [Architecture](#-architecture)
- [Technologies](#-technologies)
- [Project Structure](#-project-structure)
- [Backend](#-backend)
  - [Requirements](#requirements)
  - [Installation](#installation)
  - [Configuration](#configuration)
  - [Database](#database)
  - [Authentication](#authentication)
  - [Authorization](#authorization)
  - [API Documentation](#api-documentation)
- [Database Model](#-database-model)
- [Frontend](#-frontend)
- [Deployment](#-deployment)
- [Author](#-author)

---

# 📖 Description

Intranet Tools is a full-stack web application created to manage internal company resources from a single platform.

The backend exposes a secure REST API that allows:

- User management
- Department management
- Role management
- Tool management
- Tool permissions
- Tool visibility
- Tool categories
- User activities
- JWT authentication
- Role-based authorization

The project has been developed following REST principles and a layered architecture.

---

# 🏗️ Architecture

The project is divided into three independent modules:

```
Intranet Tools
│
├── Backend (Spring Boot REST API)
├── Frontend (Angular) - Coming Soon
└── Database (MySQL)
```

---

# 🛠️ Technologies

## Backend

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- JWT (JJWT)
- Swagger / OpenAPI
- Maven
- Lombok

## Database

- MySQL 8

---

# 📂 Project Structure

```
backend
│
├── authorization
├── config
├── controllers
├── dto
│   ├── request
│   └── response
├── entities
├── mappers
├── repositories
├── services
│   └── impl
├── exceptions
└── security
```

---

# ⚙️ Backend

## Requirements

Before running the application make sure you have installed:

- Java 21
- Maven 3.9+
- MySQL 8

---

## Installation

Clone the repository

```bash
git clone https://github.com/Ikos-Iberia/intranet-tools
```

Go to the backend directory

```bash
cd backend
```

Install dependencies

```bash
mvn clean install
```

Run the application

```bash
mvn spring-boot:run
```

The API will start at

```
http://localhost:8080
```

---

## Configuration

The application uses the `application.properties` file.

Example:

```properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/intranet_tools
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

jwt.secret=YOUR_SECRET_KEY
jwt.expiration-minutes=60
```

---

## Database

Create a MySQL database before starting the application.

Example:

```sql
CREATE DATABASE intranet_tools;
```

Hibernate automatically creates and updates the database schema.

---

## Authentication

Authentication is performed using JWT.

### Login

```
POST /users/login
```

Example request

```json
{
    "username": "admin",
    "password": "password"
}
```

Example response

```json
{
    "id": 1,
    "username": "admin",
    "roles": [
        "ROLE_ADMIN"
    ],
    "token": "JWT_TOKEN"
}
```

For protected endpoints include the JWT token in the request header.

```
Authorization: Bearer YOUR_TOKEN
```

---

## Authorization

The application uses Spring Security with method-level authorization.

Available roles:

| Role | Description |
|------|-------------|
| ROLE_ADMIN | Full access to the application |
| ROLE_USER | Standard authenticated user |

Authorization is implemented using:

```java
@PreAuthorize(...)
```

Example:

```java
@PreAuthorize("hasRole('ADMIN')")
```

---

## API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI specification:

```
http://localhost:8080/v3/api-docs
```

Swagger includes JWT authentication through the **Authorize** button.

---

# 🗄️ Database Model

The backend manages the following entities:

- Users
- Roles
- Departments
- User Roles
- Tools
- Tool Categories
- Tool Permissions
- Tool Departments
- Tool Visibility
- User Activities

Relationships are managed using JPA/Hibernate.

---

# 💻 Frontend

The frontend is currently under development.

It will consume the REST API exposed by the backend and provide an intuitive user interface for managing the application's resources.

---

# 🚀 Deployment

Deployment instructions will be added once the application is available in a production environment.

---

# 👨‍💻 Author

Developed by **José Antonio Acebedo Aragón**.