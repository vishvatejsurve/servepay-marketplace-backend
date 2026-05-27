# 🛠️ ServePay - Service Marketplace Platform (Spring Boot)

A production-style backend system built using Spring Boot that enables customers to book home services, providers to manage service offerings, and secure payment handling with complete request lifecycle management.

---

## 🔥 Features

🔐 JWT Authentication (Secure APIs)

👥 Role-Based Access (CUSTOMER / PROVIDER)

🛠️ Service Management (Electrician, Plumber, AC Repair, etc.)

📍 Location-Based Service Requests

📋 Service Request Workflow

💳 Razorpay Payment Integration

⭐ Ratings & Reviews System

📊 Average Provider Rating Calculation

⚠️ Global Exception Handling

📑 Swagger API Documentation

✅ Request Validation

---

## 🧠 Tech Stack

- Java 17
- Spring Boot
- Spring Security (JWT)
- MySQL
- Spring Data JPA / Hibernate
- Razorpay Payment Gateway
- Swagger / OpenAPI
- Lombok
- Maven

---

## 📁 Project Structure

```text
controller/      → REST APIs

service/         → Business Logic

repository/      → Database Layer

entity/          → Database Models

dto/             → Request / Response Objects

security/        → JWT & Authentication

config/          → Application Configurations

exception/       → Global Exception Handling

enums/           → Workflow Status & Roles
```

---

## 🔐 Authentication Flow

1. User registers (CUSTOMER / PROVIDER)

2. User logs in

3. JWT Token generated

4. Send token in headers:

```http
Authorization: Bearer TOKEN
```

5. Access secured APIs

---

## 👥 Roles

| Role | Access |
|-------|---------|
| CUSTOMER | Create requests, payments, reviews |
| PROVIDER | Add services, accept requests, complete work |

---

## 🔄 Service Workflow

```text
Customer Login
↓
Browse Services
↓
Create Service Request
↓
Provider Accepts Request
↓
Work Starts
↓
Work Completed
↓
Payment Completed
↓
Rating & Review Added
```

---

## 🚀 API Endpoints

### 🔐 Authentication

```http
POST /auth/register
POST /auth/login
```

---

### 🛠️ Services

```http
POST /services
GET /services
```

---

### 📋 Requests

```http
POST /requests
GET /requests/available
PUT /requests/{id}/accept
PUT /requests/{id}/start
PUT /requests/{id}/complete
```

---

### 💳 Payments

```http
POST /payments/create-order/{requestId}
POST /payments/verify/{requestId}
```

---

### ⭐ Reviews

```http
POST /reviews
```

---

## 🧪 Testing (Postman / Swagger)

1. Register Customer

2. Register Provider

3. Login → Get JWT Token

4. Add token in header

```http
Authorization: Bearer TOKEN
```

5. Test APIs

---

## 💳 Payment Flow

Uses Razorpay Payment Gateway

Order Creation

Payment Verification

Payment Status Tracking

Request Status → PAID

---

## 📊 Request Lifecycle

```text
REQUESTED
↓
ACCEPTED
↓
IN_PROGRESS
↓
COMPLETED
↓
PAID
```

---

## ⭐ Ratings & Reviews

Customers can provide ratings only after completed payment.

Features:

- Average Rating Calculation
- Review Management
- Provider Trust System

---

## ⚙️ Setup Instructions

Clone Repository

```bash
git clone https://github.com/your-username/servepay-marketplace-backend.git
```

Move into project

```bash
cd servepay-marketplace-backend
```

Configure Database

Update:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/servepay_db

spring.datasource.username=root

spring.datasource.password=your_password

jwt.secret=your_secret

razorpay.key.id=your_key

razorpay.key.secret=your_secret
```

Run Application

```bash
mvn spring-boot:run
```

---

## 🏆 Key Learnings

- JWT Authentication & Authorization
- Role-Based Access Control
- Payment Gateway Integration
- Request Lifecycle Management
- Entity Relationship Design
- Global Exception Handling
- Production-Style Backend Architecture
- API Documentation using Swagger

---

## 🚀 Future Improvements

📨 Kafka Event Notifications

⚡ Redis Caching

📱 Real-Time Notifications

🐳 Docker Deployment

☁️ AWS Deployment

📍 Geo-location Optimization

📈 Analytics Dashboard

---

## 👨‍💻 Author

**Vishvatej Surve**

Backend Developer | Java | Spring Boot

---

⭐ If you like this project, give it a star!
