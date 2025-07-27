# 🎬 MoviePulse - Movie API

> **This Project Belongs to Spring Boot Tutorials**

Welcome to the official project repository for **MoviePulse – a Movie Management REST API** built using Spring Boot.

This is **Development Phase 1** of the application, focusing on:
- Core REST API functionality
- Poster image upload and retrieval
- Data persistence using Spring Data JPA (Hibernate)
- Input validation
- Custom exception handling

---

## 📌 Project Overview

**MoviePulse** is a backend REST API designed to manage movie records with metadata and poster file handling.  
This phase enables core movie operations such as storing, retrieving, updating, and deleting movies, along with uploading poster images.

---

## 🛠️ Tech Stack

| Technology              | Description                                      |
|------------------------|--------------------------------------------------|
| Java 17                | Programming Language                             |
| Spring Boot            | Application Framework                            |
| Spring Web             | REST Controller Layer                            |
| Spring Data JPA        | ORM via Hibernate                                |
| MySQL                  | Relational Database                              |
| Bean Validation (JSR-380) | Input Field Validation                      |
| Multipart File Support | Poster Upload & Download                         |
| ModelMapper            | DTO ↔ Entity Mapping                             |
| Postman                | API Testing                                      |
| Lombok                 | Reduces boilerplate with annotations             |
| Maven                  | Dependency Management                            |

---

## 🎯 Features – Phase 1

- 📝 Add, update, delete, and retrieve movie records
- 📁 Upload and serve movie poster images (PNG/JPG)
- 🛡️ Custom exception handling for robust APIs
- ✅ Input validation using `@NotBlank`, `@Valid`, etc.
- 📦 File upload validation and storage logic
- 🔄 Movie DTO mapping using ModelMapper
- 🎯 Pagination and sorting ready for next phases

---

## 📁 Folder Structure

```

movieApi/
│
├── controllers/        # REST API Controllers
├── dto/                # Movie DTOs (Data Transfer Objects)
├── entities/           # JPA Entity classes
├── repositories/       # Spring Data Repositories
├── services/           # Business Logic (Interfaces & Impl)
├── exceptions/         # Custom Exceptions
├── advices/            # Global Exception Handler (ControllerAdvice)
├── config/             # Configuration Beans (ModelMapper, etc.)
└── resources/          # application.yml, static folders, etc.

```

---

## 🚀 API Endpoints Summary

| Method | Endpoint                    | Description                       |
|--------|-----------------------------|-----------------------------------|
| POST   | `/api/v1/movie/add-movie`   | Add movie with poster             |
| GET    | `/api/v1/movie/{id}`        | Get movie by ID                   |
| GET    | `/api/v1/movie/all`         | Retrieve all movies               |
| PUT    | `/api/v1/movie/update/{id}` | Update movie and replace poster   |
| DELETE | `/api/v1/movie/delete/{id}` | Delete movie and poster file      |
| POST   | `/file/upload`              | Upload a standalone poster image  |
| GET    | `/file/{filename}`          | Download or view the poster image |

---

## 🧪 Validations Applied

- Mandatory fields: `title`, `director`, `studio`, `poster`, etc.
- File upload: no empty file or duplicate poster name allowed
- Business rules handled through:
  - `EmptyFileException`
  - `FileAlreadyExistException`
  - `GivenFileNotFoundException`
  - `MovieNotFoundException`
- REST-friendly error responses using `@ControllerAdvice`

---

## 🌐 Configuration Details

- File upload directory: `posters/`
- Base URL: `http://localhost:8080`
- Poster path format: `/file/{posterFileName}`
- MySQL configured via `application.yml`

---

## 🧑‍💻 Author

**Shadab Rehan**  
Java | Spring Boot | REST APIs | Backend Development

🔗 LinkedIn Profile: https://www.linkedin.com/in/shadabrehan

---

## 📢 Development Roadmap

### ✅ Phase 1 (Current)
- Movie CRUD API
- File upload/download
- Validation + Exception Handling

### 🔜 Phase 2 (Planned)
- Movie Pagination & Sorting
- Search and Filtering
- Movie Genre Support
- User Authentication (Spring Security)

---

## ⭐ Feedback & Contributions

If you find this project helpful or have suggestions:
- Give it a ⭐ on GitHub
- Connect with me on LinkedIn: https://www.linkedin.com/in/shadabrehan
- Fork, clone, and contribute to improve!

---
