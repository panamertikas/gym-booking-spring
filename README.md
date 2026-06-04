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

## Running with Docker (Recommended)

### Prerequisites
- Docker Desktop

### Steps

1. Clone the repository:
```bash
git clone https://github.com/panamertikas/gym-booking-spring.git
cd gym-booking-spring
```

2. Start the containers:
```bash
docker-compose up --build
```

3. The API will be available at `http://localhost:8080`

## Running Locally

### Prerequisites
- Java 21
- MySQL 8
- Maven

### Steps

1. Start MySQL and make sure it accepts `root`/`root` credentials

2. Clone the repository:
```bash
git clone https://github.com/panamertikas/gym-booking-spring.git
cd gym-booking-spring
```

3. Build and run:
```bash
mvn spring-boot:run
```

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