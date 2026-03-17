This project is a Spring Boot backend application that provides:
•	User Registration & Authentication (JWT-based)
•	Secure API access using Spring Security
•	Text Processing via external service (Service B)
•	 Logging of processed requests per user
It follows a clean layered architecture:
Controller → Service → Repository → Database
________________________________________
Features
 Authentication
•	User registration with email & password
•	Password hashing using BCrypt
•	JWT token generation on login
•	Stateless authentication using JWT filter
 Security
•	Spring Security integration
•	Custom UserDetailsService
•	JWT validation on each request
•	Protected endpoints
 Processing Service
•	Accepts user input text
•	Calls external API (/api/transform)
•	Stores input/output logs in database
 Logging
•	Each processed request is stored
•	Linked to authenticated user
________________________________________

Tech Stack
•	Backend: Java, Spring Boot
•	Security: Spring Security, JWT
•	Database: JPA / Hibernate
•	Build Tool: Maven
•	Other: Lombok, REST APIs
________________________________________
Project Structure
com.auth_api.auth_api

controller         # REST Controllers
 services           # Business Logic
 repositories       # JPA Repositories
 entity             # Database Entities
 dtos               # Request/Response DTOs
 jwt_utils          # JWT handling
 user_customisations# Security user details
 exceptions         # Global exception handling
 system             # Security & Bean configuration
________________________________________
 API Endpoints
 Auth APIs
 Register User
POST /api/auth/register
Request Body:
{
  "email": "abhi@mail.com",
  "password": "123456"
}
Response:
•	201 Created
________________________________________
 Login
POST /api/auth/login
Request Body:
{
  "email": "abhi@mail.com",
  "password": "123456"
}
Response:
"JWT_TOKEN"
________________________________________
 Processing API
 Process Text
POST /api/process
Authorization: Bearer <JWT_TOKEN>
Request Body:
{  "text": "hello world"}
Response:
{  "output_text": "processed text"}
________________________________________
Database Schema
 Users Table
•	id (UUID)
•	email (unique)
•	password_hash
•	created_at
 Processing Log Table
•	id (UUID)
•	user_id (FK)
•	input_text
•	output_text
•	created_at
________________________________________
Flow Diagram (Simplified)
 Login Flow
1.	User sends credentials
2.	Spring Security authenticates user
3.	JWT token is generated
4.	Token returned to client
Processing Flow
1.	User sends request with JWT
2.	JWT filter validates token
3.	Service calls external API
4.	Response saved in DB
5.	Result returned to user
________________________________________
Exception Handling
Global exception handling using @ControllerAdvice:
•	User not found → 404
•	Password mismatch → 404
•	External API failure → 502
________________________________________
Configuration
Set the following properties in application.properties:
application.jwt.secret=********
application.jwt.expiration=86400000

app.internal-token=your_internal_token
app.data-api.url=http://data-api:8081
________________________________________
Service B
data-api
what it provide 
api auth_api:8080/ call service B with JWT token , with headers 
user enter input text adn receive output text without notifying User about service B 
layer
Controller -> Service ->Service A

Service Structure
Controller
dto
exceptions
service



How to Run
# Clone repo
git clone <https://github.com/JustDoItAbhi/winwin_travels>
# Navigate
cd project-folder
# Run
mvn spring-boot:run________________________________________
Key Concepts Used
•	JWT Authentication
•	Spring Security Filter Chain
•	REST API Design
•	DTO Pattern
•	Layered Architecture
•	Exception Handling
________________________________________
Author
Nourth Arvinder Pal Singh

