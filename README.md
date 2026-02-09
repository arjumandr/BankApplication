# BankApplication

<!-- PROJECT LOGO -->
<h1 align="center">Bank Management System</h1>

<p align="center">
  A Spring Boot–based banking application for managing accounts, transactions, clerks, and managers.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17%2B-orange" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen" />
  <img src="https://img.shields.io/badge/Build-Maven-blue" />
  <img src="https://img.shields.io/badge/Database-MySQL%20%7C%20H2-lightgrey" />
  <img src="https://img.shields.io/badge/License-MIT-green" />
</p>

---

## 📖 Overview

The **Bank Management System** is a Spring Boot application that follows a **layered architecture**:

1. **Controller Layer** – Handles HTTP requests and responses  
2. **Service Layer** – Implements business logic and validation  
3. **DAO / Repository Layer** – Interacts with the database using JPA/Hibernate  
4. **Database Layer** – Stores accounts, transactions, clerks, and managers  

The system ensures **data integrity**, **clean API design**, and avoids common issues such as:
- Transient entity errors  
- Infinite JSON serialization loops  

---

## ✨ Features

- **Account Management**
  - Create, read, update, and delete accounts
- **Transaction Management**
  - Deposit, withdraw, and transfer funds
- **Clerk & Manager Management**
  - Many-to-one relationship between clerks and managers
  - Prevents saving clerks with non-existent managers
- **Exception Handling**
  - Business exceptions in the service layer
  - Persistence exceptions handled in DAO layer
- **JSON Serialization Safety**
  - Uses `@JsonManagedReference` and `@JsonBackReference`
- **RESTful APIs**
  - Clean, consistent endpoints following best practices

---

## 🛠 Technologies

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA / Hibernate**
- **MySQL / H2 Database**
- **Maven**
- **RESTful APIs (JSON)**

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL (or H2 for in-memory testing)

### Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/bank-management-system.git
   cd bank-management-system
