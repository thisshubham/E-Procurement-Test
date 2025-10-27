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

#### Admin API Endpoints

## Create Department
  **http://localhost:30080/api/v1/Eprocurement/admin/departments**
  
## Create Teacher
  **http://localhost:30080/api/v1/Eprocurement/admin/teachers**
  
## Create Student
  **http://localhost:30080/api/v1/Eprocurement/admin/creStud**
  
## Create Subject
  **http://localhost:30080/api/v1/Eprocurement/admin/subjects**
  
## Create Question
  **http://localhost:30080/api/v1/Eprocurement/admin/subjects/questions**
  
### Student Endpoints
  ## View Result by Student ID
  **http://localhost:30080/api/v1/Eprocurement/student/results/1**
  
  ## View Student Scoreboard
  **http://localhost:30080/api/v1/Eprocurement/student/student/scoreboard?studentId=STU2025-201**
  
  ## View Subject List by Student
  **http://localhost:30080/api/v1/Eprocurement/student/subjects?studentId=1**
  
  ## Start Quiz
  **http://localhost:30080/api/v1/Eprocurement/student/quiz/22/start?studentId=3**
  
  ## Submit Quiz
  **http://localhost:30080/api/v1/Eprocurement/student/quiz/1/submit**
  
  ## Student details by id
  **http://localhost:30080/api/v1/Eprocurement/student/student/byId?studentId=1**

  ### Teacher Endpoints
  
  ## View subject by deptID
  **http://localhost:30080/api/v1/Eprocurement/teacher/subjects/1**
  
  ## View question by subject 
  **http://localhost:30080/api/v1/Eprocurement/teacher/subjects/2/questions**
  
  ## View student List by department
  **http://localhost:30080/api/v1/Eprocurement/teacher/teacher/1/student**
  
  ## Teacher marks grade 
  **http://localhost:30080/api/v1/Eprocurement/teacher/results/2/grade?marks=2&remarks=2&teacherId=2**
  
  ## Assign Department to teacher
  **http://localhost:30080/api/v1/Eprocurement/teacher/teacher/assign/department?deparmentId=2&teacherId=2**


  
### Postman Collection in resource folder
### Swagger documentation there on below url
- http://localhost:30080/swagger-ui/index.html



## Role-Based Access Control

| Role                         | Accessible API Paths                                                                                      | Description                                                    |
| ---------------------------- | --------------------------------------------------------------------------------------------------------- | -------------------------------------------------------------- |
| **ADMIN**                    | `/api/v1/Eprocurement/admin/**`<br>`/api/v1/Eprocurement/teacher/**`<br>`/api/v1/Eprocurement/student/**` | Full control – can manage departments, teachers, and students. |
| **TEACHER**                  | `/api/v1/Eprocurement/teacher/**`<br>`/api/v1/Eprocurement/student/**`                                    | Can manage subjects, view and grade students.                  |
| **STUDENT**                  | `/api/v1/Eprocurement/student/**`                                                                         | Can view personal results, subjects, and test records.         |
| **PUBLIC (Unauthenticated)** | `/api/v1/Eprocurement/auth/**`                                                                            | Registration  endpoints only.                         |

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



