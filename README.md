# Java Spring Boot Authentication API

This is a Java Spring Boot application that serves as an authentication API, providing secure user authentication and authorization for your applications.

## Features

- User registration with email and password
- User login and token-based authentication
- Role-based authorization
- JWT (JSON Web Token) authentication
- Password hashing and salting

## Prerequisites

Make sure you have the following installed:

- Java 17
- Apache Maven
- PostgreSQL (or any other relational database)

## Dependencies

- Spring Data JPA
- Spring Security
- Lombok
- Java JWT (jjwt)

## How to run

You can build and run the application using Maven:
```
mvn spring-boot:run
```
The API will be available at `http://localhost:8080`. You can change the port and other settings in the `application.properties` file.
