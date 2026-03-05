# TechRoute – Microservices E-Commerce Backend

TechRoute is a **backend-only e-commerce mini project** built using a **Microservices Architecture**.
The system is designed to demonstrate how modern scalable applications are structured using **independent services, secure APIs, and service-to-service communication**.

Each service is responsible for a specific business capability and maintains its own database, following the **Database per Service pattern**.

---

# System Architecture

TechRoute follows a **Microservices Architecture** where each service is independently deployable and communicates through REST APIs.

```
Client
   │
   ▼
API Gateway
   │
   ├── User Service
   │
   ├── Product Service
   │
   └── Order Service
```

Future services such as **Payment, Notification, and Inventory** can be added without affecting existing services.

---

# Microservices

## 1. User Service

**Purpose**

Handles authentication, registration, and role management.

**Tech Stack**

* Spring Boot
* Spring Security
* JWT Authentication
* PostgreSQL / MySQL

**Responsibilities**

* Register new users
* Authenticate users
* Generate JWT tokens
* Manage roles such as:

  * CUSTOMER
  * ADMIN
  * VENDOR
* Manage user profiles

---

## 2. API Gateway

**Purpose**

Acts as the **single entry point** for all client requests.

**Tech Stack**

* Spring Cloud Gateway

**Responsibilities**

* Route incoming requests to appropriate microservices
* Validate JWT tokens
* Apply cross-cutting concerns such as:

  * Logging
  * Rate limiting
  * Security filters

---

## 3. Product Service

**Purpose**

Manages the **product catalog**.

**Tech Stack**

* Spring Boot
* MongoDB
* Spring Data MongoDB

**Responsibilities**

* Create, update, and delete products
* Provide product listing APIs
* Public endpoints for customers:

  * View products
  * Search products
* Secured endpoints for:

  * Admins
  * Vendors
* Store product details using MongoDB’s flexible schema

---

## 4. Order Service (In Progress)

**Purpose**

Handles **customer orders and their lifecycle**.

**Tech Stack**

* Spring Boot
* PostgreSQL / MySQL
* Spring Data JPA
* Feign Client / WebClient

**Responsibilities**

* Place new orders
* Validate product information via Product Service
* Store order details with product snapshots
* Manage order lifecycle:

```
PENDING → CONFIRMED → SHIPPED → DELIVERED → CANCELLED
```

**Role Enforcement**

* Customers can manage their own orders
* Admins/Vendors can manage all orders

---

# Design Patterns Used

This project implements several backend design patterns commonly used in large-scale systems.

**Microservices + Database per Service**

Each microservice maintains its own database to ensure loose coupling.

**API Gateway Pattern**

All requests pass through a centralized gateway.

**DTO Pattern**

Separates internal entities from external API contracts.

**Repository + Service Layer Pattern**

Ensures separation between data access and business logic.

**JWT + Role-Based Access Control**

Secures endpoints and controls access based on roles.

**Audit + Soft Delete Pattern**

Maintains historical records without permanently deleting data.

**Exception Handling Pattern**

Provides consistent error responses across services.

**Service-to-Service Communication**

Microservices communicate via REST using Feign or WebClient.

---

# Project Status

Current progress:

| Service         | Status      |
| --------------- | ----------- |
| User Service    | Completed   |
| API Gateway     | Completed   |
| Product Service | Completed   |
| Order Service   | In Progress |

---

# Next Steps

## Short-Term Goals

**Complete Order Service**

* Implement entities and DTOs
* Build endpoints for customers and admins
* Integrate Product Service validation
* Implement order lifecycle logic
* Write unit and integration tests

**Documentation**

* Add Swagger / OpenAPI documentation
* Define clear API contracts between services

---

## Medium-Term Enhancements

* Payment Service for secure transactions
* Notification Service for order updates
* Inventory Service for stock management
* Event-driven communication using Kafka or RabbitMQ

---

## Long-Term Improvements

* Centralized monitoring and logging (ELK Stack)
* Metrics using Prometheus and Grafana
* Docker containerization
* Kubernetes orchestration
* CI/CD pipelines for automated deployments
* Horizontal scaling of services

---

# Tech Stack Summary

Backend Technologies

* Java
* Spring Boot
* Spring Security
* Spring Cloud Gateway
* JWT Authentication

Databases

* PostgreSQL / MySQL
* MongoDB

Communication

* REST APIs
* Feign Client / WebClient

DevOps (Future)

* Docker
* Kubernetes
* Kafka / RabbitMQ
* Prometheus / Grafana

---

# Author

**Shriharsha K M**

GitHub
[https://github.com/ShriAlt](https://github.com/ShriAlt)

LinkedIn
[https://www.linkedin.com/in/shri-harsha-k-m-5758aa325/](https://www.linkedin.com/in/shri-harsha-k-m-5758aa325/)

---
