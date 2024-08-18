# Spring Boot Security with JWT and Roles

This project is a Spring Boot application that uses JWT (JSON Web Tokens) for authentication and roles for authorization. It is built with Spring Security 6 and uses MySQL as the database.

## Description

This example application demonstrates how to implement authentication and authorization in a Spring Boot application using JWT for authentication and roles for authorization.

## Features

- JWT-based authentication
- Role-based authorization
- Security configuration with Spring Security 6
- Data persistence with MySQL
- ModelMapper for object mapping

## Technologies

- **Spring Boot**: Core framework for application development.
- **Spring Security**: Security and authentication.
- **JWT (io.jsonwebtoken)**: For generating and validating JWT tokens.
- **MySQL**: Relational database.
- **ModelMapper**: For mapping DTOs.
- **Lombok**: To reduce boilerplate code.

## Prerequisites

- Java 17
- Maven
- MySQL

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Anyel-ec/SpringBoot-Security-JWT-Roles-User-Admin
    cd SpringBoot-Security-JWT-Roles-User-Admin
    ```

2. **Configure the database:**

   Create a database in MySQL and configure the properties in `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/database_name
    spring.datasource.username=username
    spring.datasource.password=password
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the application:**

    ```bash
    mvn spring-boot:run
    ```

## Usage

1. **Register a new user (POST /api/v1/register)**

   Send a POST request to `/api/v1/register` with a JSON body containing `username`, `password`, and `roles` (optional).

    ```json
    {
      "username": "new_user",
      "password": "password",
      "roles": ["ROLE_USER"]
    }
    ```

2. **Log in (POST /api/v1/login)**

   Send a POST request to `/api/v1/login` with a JSON body containing `username` and `password`.

    ```json
    {
      "username": "user",
      "password": "password"
    }
    ```

   Response:

    ```json
    {
      "accessToken": "jwt-token-here"
    }
    ```

3. **Access protected resources**

   Use the JWT token received from the login step as an `Authorization` header with the `Bearer` prefix.

    ```bash
    curl -H "Authorization: Bearer jwt-token-here" http://localhost:8080/api/v1/protected
    ```

## Security Configuration

The security configuration is located in `src/main/java/top/anyel/jwt/security/SecurityConfig.java`. The application is configured to:

- Disable CSRF.
- Allow unauthenticated requests to `/api/v1/login`.
- Require authentication for all other routes.
- Use JWT for authentication.

## Contributing

Contributions are welcome. Please open an issue or submit a pull request to suggest improvements or new features.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

- Anyel EC
