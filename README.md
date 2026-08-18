# Curriculum Vitae (CV) Backend

This repository contains the backend service for my Curriculum Vitae (CV). It's a RESTful API built with Spring Boot and MongoDB. The application is designed to store and serve my professional experience, education, skills, and projects dynamically to the frontend application.

## Features

- **RESTful API**: Serves structured CV data, including experience, education, and skills, in JSON format.
- **Database Initialization**: Automatically populates the MongoDB database with default CV information on startup.
- **Security**: Includes API Key filtering to protect specific administrative endpoints.
- **Docker Support**: Provides a Dockerfile for easy containerization and deployment.

## Technology Stack

- **Framework**: [Spring Boot 3](https://spring.io/projects/spring-boot)
- **Language**: [Java 17+](https://adoptium.net/)
- **Database**: [MongoDB](https://www.mongodb.com/)
- **Build Tool**: [Maven](https://maven.apache.org/)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 17 or higher
- MongoDB (running locally or via MongoDB Atlas)
- Docker (optional, for containerized deployment)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/dabs1/CV-backend.git
   ```

2. Configure the database (Optional):
   By default, the application connects to a local MongoDB instance at `mongodb://localhost:27017/cvdb`. You can change this in `src/main/resources/application.properties` or `application.yml`.

### Running the Application (Locally)

To start the local development server, run:

**On Windows:**
```cmd
mvnw.cmd spring-boot:run
```

**On macOS/Linux:**
```bash
./mvnw spring-boot:run
```

### Running with Docker

You can easily build and run the application using Docker:

1. Build the Docker image:
   ```bash
   docker build -t cv-backend .
   ```
2. Run the container:
   ```bash
   docker run -p 8080:8080 cv-backend
   ```

The application API will be accessible at `http://localhost:8080/api/cv`.

## API Documentation

The backend exposes the following primary endpoints:

| HTTP Method | Endpoint | Description |
| ----------- | -------- | ----------- |
| `GET` | `/api/cv` | Retrieves the full CV data in JSON format (Experience, Education, Skills, etc.). |

*(Note: Administrative endpoints are protected via an API Key authentication filter).*

## Project Structure

The codebase follows a standard Spring Boot architecture to ensure separation of concerns:

- `com.cv.controller` - REST Controllers defining the API endpoints.
- `com.cv.service` - Business logic and orchestration layer.
- `com.cv.repository` - Spring Data MongoDB interfaces for database interactions.
- `com.cv.model` - Data models representing the CV structure.
- `com.cv.config` - Configuration classes (Security, Database Initialization).

## Live Application

The live version of the CV frontend interacting with this API is available at: [cvtomasfrancisco.vercel.app](https://cvtomasfrancisco.vercel.app/)

## Frontend Application

This backend provides data for the React frontend. The frontend repository can be found on my GitHub profile.
