# GymBooking - Spring Boot REST API

A full-stack gym booking management system built with Spring Boot, Spring Security JWT, JPA/Hibernate and MySQL.

## Technologies
- Java 21
- Spring Boot 3.5
- Spring Data JPA / Hibernate
- Spring Security + JWT
- MySQL 8
- Maven
- Docker & Docker Compose
- Swagger / OpenAPI 3

## Features
- JWT Authentication & Authorization
- Role-based access control (ADMIN / USER)
- Member management
- Gym class management
- Booking management with capacity control
- Swagger API documentation
- Data initialization on startup

## Running with Docker

### Prerequisites
- Docker Desktop

### Steps

1. Clone the repository:
```bash
git clone https://github.com/panamertikas/gym-booking-spring.git
cd gym-booking-spring
```

2. Stop local MySQL if running (Windows):
```bash
net stop MySQL80
```

3. Start the containers:
```bash
docker-compose up -d
```

4. The API will be available at `http://localhost:8080`

## Default Users
The application automatically creates the following users on startup:

- Admin: `admin` / `admin123`
- User: `user@gmail.com` / `123456`

## API Documentation
Swagger UI available at: `http://localhost:8080/swagger-ui/index.html`

## Related Projects
- [GymBooking React Frontend](https://github.com/panamertikas/gym-booking-react)
- [GymBooking Console](https://github.com/panamertikas/gym-booking) - Plain Java OOP
- [GymBooking JDBC](https://github.com/panamertikas/gym-booking-jdbc) - JDBC/JPA