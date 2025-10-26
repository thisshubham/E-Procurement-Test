# Quiz Management System for E-Procurement - Spring Boot

## Prerequisites
- Java 8
-  Gradle 7.0+
- MySQL 8.0
- Postman (optional)

## Technology Stack
- Spring Boot 2.7.18
- Spring Data JPA
- Spring Security + Basic Auth
- MySQL
- Lombok
- Liquibase

## Database Setup

### MySQL
-mysql user password must be setup properly


## Configuration

*application.properties:*
properties
server.port=30080

# MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/aprocurementTest?allowPublicKeyRetrieval=true&useSSL=false&autoreconnect=true&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root


spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true



## Build & Run

bash
# Clone
git clone https://github.com/thisshubham/E-Procurement-Test.git
cd E-Procurement-Services

# Build
gradle clean build

# Run
gradle bootRun

# Or using JAR
java -jar build/libs/E-Procurement-Services-1.0-SNAPSHOT.jar


## Sample Credentials

| Role | Username | Password | Email |
|------|----------|----------|-------|
| ADMIN | adminuser | admin@123 | admin@example.com |
| TEACHER | cs_teacher | admin@123 | cs_teacher@eproc.edu |
| STUDENT | s2_cs | admin@123 | student2@eproc.edu |

## Database Design
### refer image in resource folder

### Key Tables
- *users*: Base user table (id, username, password, email, role)
- *teachers*: Inherits User (department_id, employee_id)
- *students*: Inherits User (department_id, teacher_id, student_id)
- *departments*: (id, name, description)
- *subjects*: (id, name, department_id, total_marks)
- *questions*: (id, question_text, subject_id, difficulty, marks)
- *answers*: (id, answer_text, is_correct, question_id, option_order)
- *test_results*: (id, student_id, subject_id, marks_obtained, percentage)
- *student_answers*: (id, test_result_id, question_id, selected_answer_id)

## API Endpoints
### Postman Collection in resource folder
### Swagger documentation there on below url
- http://localhost:30080/swagger-ui/index.html



## Role-Based Access Control

| Role                         | Accessible API Paths                                                                                      | Description                                                    |
| ---------------------------- | --------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------- |
| **ADMIN**                    | `/api/v1/Eprocurement/admin/**`<br>`/api/v1/Eprocurement/teacher/**`<br>`/api/v1/Eprocurement/student/**` | Full control – can manage departments, teachers, and students. |
| **TEACHER**                  | `/api/v1/Eprocurement/teacher/**`<br>`/api/v1/Eprocurement/student/**`                                    | Can manage subjects, view and grade students.                  |
| **STUDENT**                  | `/api/v1/Eprocurement/student/**`                                                                         | Can view personal results, subjects, and test records.         |
| **PUBLIC (Unauthenticated)** | `/api/v1/Eprocurement/auth/**`                                                                            | Registration and login endpoints only.                         |

## Business Rules

1. *Department*: Must have ≥2 subjects
2. **Expected behaviour** *Subject*: Must have ≥15 questions (**Current behaviour** Added only **Five** questions)
3. *Teacher*: Can only access their department
4. *Question*: MCQ with exactly 1 correct answer
5. *Test*: Auto-scored on submission
6. *Scorecard*: Available immediately after test

## Project Structure
```
src/main/java/com/eProcurement/
├── securityconfig/ # Security,Basic-Auth
├── controller/     # REST endpoints
├── entity/         # JPA entities
├── repository/     # Data access
├── service/        # Business logic
├── dto/            # Request/Response objects
└── utility/        # Helper classes

src/main/resources/
├── application.properties
|    └── db.changelog
|         └── db.changelog-1.0.xml
|         └── db.changelog-master.xml
├── eProcurement API.postman_collection.json
└── db_design.png
```



