# GymBooking - Spring Boot REST API

A full-stack gym booking management system built with Spring Boot, Spring Security JWT, JPA/Hibernate and MySQL.

## Technologies
- Java 21
- Spring Boot 3.5
- Spring Data JPA / Hibernate
- Spring Security + JWT
- MySQL
- Maven
- Swagger / OpenAPI 3
- HTML/CSS/JavaScript (Frontend)

## Features
- JWT Authentication & Authorization
- Role-based access control (ADMIN / USER)
- Member management
- Gym class management
- Booking management with capacity control
- User dashboard with calendar booking
- Swagger API documentation

## Prerequisites
- Java 21
- MySQL 8
- Maven

## Setup

### Database
1. Create a MySQL database:
```sql
CREATE DATABASE gym_booking;
CREATE USER 'gymbooking_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON gym_booking.* TO 'gymbooking_user'@'localhost';
```

### Application
1. Clone the repository:
```bash
git clone https://github.com/panamertikas/gym-booking-spring.git
cd gym-booking-spring
```

2. Copy the example properties file:
```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

3. Edit `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gym_booking?serverTimezone=UTC
spring.datasource.username=gymbooking_user
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. Build and run:
```bash
mvn spring-boot:run
```

## Default Users
After running the application, create an admin user manually in the database:
```sql
INSERT INTO users (username, password, role) 
VALUES ('admin', '$2a$10$...bcrypt_hash...', 'ADMIN');
```

## API Documentation
Swagger UI available at: `http://localhost:8080/swagger-ui/index.html`

## Frontend
- `http://localhost:8080/login.html` - Login/Register
- `http://localhost:8080/index.html` - Members (ADMIN)
- `http://localhost:8080/gymclasses.html` - Gym Classes (ADMIN)
- `http://localhost:8080/bookings.html` - All Bookings (ADMIN)
- `http://localhost:8080/dashboard.html` - User Dashboard
- `http://localhost:8080/my-bookings.html` - My Bookings (USER)
- `http://localhost:8080/profile.html` - My Profile (USER)

## Related Projects
- [GymBooking Console](https://github.com/panamertikas/gym-booking) - Plain Java OOP
- [GymBooking JDBC](https://github.com/panamertikas/gym-booking-jdbc) - JDBC/JPA