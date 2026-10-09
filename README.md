# MicroServices Demo

This project is a simple Spring Boot microservices example that demonstrates how independent services can work together in a distributed application. It includes two services:

- `course-service` – manages course records
- `student-service` – manages student records and fetches course information from the course service

## Project Overview

The application models a basic academic system where:

- a course has an `id`, `name`, and `trainer`
- a student has an `id`, `name`, `email`, and `courseId`
- the student service calls the course service to enrich student details with course information

This is a typical service-to-service communication pattern using RESTful APIs.

## Architecture

```text
+-------------------+        REST        +-------------------+
| Course Service    | <----------------> | Student Service    |
| Port: 8082        |                   | Port: 8081        |
| MySQL: course_db  |                   | MySQL: student_db |
+-------------------+                   +-------------------+
```

### Responsibilities

#### Course Service
- Exposes CRUD endpoints for courses
- Stores course records in a MySQL database
- Runs on port `8082`

#### Student Service
- Exposes CRUD endpoints for students
- Stores student records in a MySQL database
- Fetches course details from the course service using `RestTemplate`
- Runs on port `8081`

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Spring WebFlux (present in student service)
- MySQL Connector/J
- Maven

## Repository Structure

```text
MicroServicesDemo/
├── course-service/
│   └── course-service/
│       ├── src/
│       ├── pom.xml
│       └── mvnw
├── student-service/
│   └── student-service/
│       ├── src/
│       ├── pom.xml
│       └── mvnw
└── README.md
```

## Service Details

### Course Service

Base URL: `http://localhost:8082`

Endpoints:

- `POST /courses` – create a course
- `GET /courses` – list all courses
- `GET /courses/{id}` – get course by id

Example request body:

```json
{
  "id": 1,
  "name": "Java Programming",
  "trainer": "John Smith"
}
```

### Student Service

Base URL: `http://localhost:8081`

Endpoints:

- `POST /students` – create a student
- `GET /students` – list all students
- `GET /students/{id}` – get student by id
- `GET /students/{id}/details` – get a student with their related course information

Example request body:

```json
{
  "id": 1,
  "name": "Alice",
  "email": "alice@example.com",
  "courseId": 1
}
```

The `GET /students/{id}/details` endpoint internally calls:

```text
http://localhost:8082/courses/{courseId}
```

and returns a response combining student details and course details.

## Configuration

Both services use MySQL and are configured in `src/main/resources/application.properties`.

### Example configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
spring.datasource.username=root
spring.datasource.password=<your-password>
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

The current project is set to use:

- `course_db` for the course service
- `student_db99` for the student service

Make sure the corresponding MySQL databases already exist before starting the applications.

## Running the Project

### 1. Start MySQL
Ensure MySQL is running locally and the required databases exist.

```sql
CREATE DATABASE course_db;
CREATE DATABASE student_db99;
```

### 2. Start the Course Service
From `course-service/course-service`:

```bash
./mvnw spring-boot:run
```

### 3. Start the Student Service
From `student-service/student-service`:

```bash
./mvnw spring-boot:run
```

The services will start on:

- `http://localhost:8082` – course service
- `http://localhost:8081` – student service

## Sample API Calls

### Create a course

```bash
curl -X POST http://localhost:8082/courses \
  -H "Content-Type: application/json" \
  -d '{"id":1,"name":"Java Programming","trainer":"John Smith"}'
```

### Create a student

```bash
curl -X POST http://localhost:8081/students \
  -H "Content-Type: application/json" \
  -d '{"id":1,"name":"Alice","email":"alice@example.com","courseId":1}'
```

### Get student with course info

```bash
curl http://localhost:8081/students/1/details
```

## Notes

- The project demonstrates a simple microservice pattern with independent databases.
- The student service depends on the course service for course metadata.
- It is a good foundation for understanding inter-service communication, CRUD layers, and Spring Boot configuration.

## Future Enhancements

Possible improvements for this project include:

- adding API gateway routing
- introducing service discovery with Eureka or Consul
- using OpenFeign or WebClient instead of RestTemplate
- adding validation and exception handling
- adding Docker and Docker Compose support
- adding authentication and authorization
