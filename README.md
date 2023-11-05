# Java Spring Boot Authentication API

This is a Java Spring Boot application that serves as an authentication API, providing secure user authentication and authorization for your applications.

## Features

- User registration with email and password
- User login and token-based authentication
- Role-based authorization
- JWT (JSON Web Token) authentication
- Password hashing and salting

## Actions

- Run tests with `mvn test`
- Build JAR with `mvn package`
- Build Docker image and push to [Docker Hub](https://hub.docker.com/repository/docker/atilara/authentication-jwt-spring-boot) with the following params:
  - `DATABASE_URL`: URL of the PostgreSQL database
  - `DATABASE_USERNAME`: Username of the PostgreSQL database
  - `DATABASE_PASSWORD`: Password of the PostgreSQL database
  - `JWT_SECRET_KEY`:  Cryptographic key used to sign and verify JWTs
- Publish release with the version written in `pom.xml`

## Prerequisites

Make sure you have the following installed:

- Java 17
- Apache Maven
- PostgreSQL

## Dependencies

- Spring Data JPA
- Spring Security
- Lombok
- Java JWT (jjwt)

## How to run

1. Clone the repository to your local machine.

2. Open the project in your IDE.

3. Configure the application settings that are read by `src/main/resources/application.yml`. Create a `.env` file in the root of the application with:

   ```env
    DATABASE_URL=value
    DATABASE_USER=value
    DATABASE_PASSWORD=value
    JWT_SECRET_KEY=value
   ```

   Modify the URL, username, and password to match your database configuration.

4. Build and run the application:

   ```bash
   mvn spring-boot:run
   ```

The API will be accessible at `http://localhost:8080`.
