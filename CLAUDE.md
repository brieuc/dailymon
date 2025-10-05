# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Development Commands

### Build and Run
- **Build the project**: `./mvnw clean compile`
- **Run tests**: `./mvnw test`
- **Package application**: `./mvnw clean package`
- **Run application locally**: `./mvnw spring-boot:run`
- **Run specific test class**: `./mvnw test -Dtest=CommonUtilTest`

### Docker Development
- **Build Docker image**: `docker build -t dailymon:latest .`
- **Start full stack with Docker Compose**: `docker-compose -f docker-compose.app.yml up`
- **Database only**: `docker-compose -f docker-compose.app.yml up db adminer`

### Database Management
- **Liquibase update**: `./mvnw liquibase:update`
- **Liquibase rollback**: `./mvnw liquibase:rollback -Dliquibase.rollbackCount=1`
- Database migrations are in `src/main/resources/db/changelog/`

## Architecture Overview

### Technology Stack
- **Framework**: Spring Boot 3.2.1 with Java 21
- **Database**: MariaDB with JPA/Hibernate
- **Security**: Spring Security with JWT authentication
- **Migration**: Liquibase for database versioning
- **Frontend**: Vue.js (separate dailyvue image)

### Package Structure
- `com.brieuc.dailymon.controller/` - REST controllers (AuthController, EntryController, ModelController)
- `com.brieuc.dailymon.service/` - Business logic services with interfaces and implementations
- `com.brieuc.dailymon.entity/` - JPA entities organized by domain:
  - `entity.user/` - User-related entities
  - `entity.entry/` - Entry types (EntryFood, EntrySport, EntryFree, etc.)
  - `entity.model/` - Model entities
- `com.brieuc.dailymon.dto/` - Data transfer objects
- `com.brieuc.dailymon.repository/` - JPA repositories
- `com.brieuc.dailymon.mapper/` - Entity-DTO mappers
- `com.brieuc.dailymon.config/` - Configuration classes (SecurityConfig, JwtService)

### Application Configuration
- **Local environment**: Uses `application-local.yml` (auto-configured)
- **Production environment**: Uses `application-production.yml` with MariaDB container
- **SSL**: Production runs on port 8443 with SSL certificates from Let's Encrypt
- **Database**: Production connects to `mariadb_container:3306/dailymondb`

### Domain Model
The application appears to be a daily monitoring system with:
- **Users**: Authentication and user management
- **Models**: Template definitions for different tracking types
- **Entries**: Daily entries of different types (Food, Sport, Free-form)
- **Benefits**: Associated benefits or rewards system

### Testing
- Uses JUnit 5 for unit testing
- Test files are in `src/test/java/com/brieuc/dailymon/`
- CommonUtilTest shows date utility testing patterns

### Docker Architecture
- **Multi-service setup**: Database, backend, frontend, adminer, certbot
- **Networking**: All services communicate via `network1` bridge network
- **SSL/TLS**: Automated certificate management with Certbot
- **Database**: MariaDB 10.6 with persistent volumes

### Development Notes
- Uses Lombok for reducing boilerplate code
- JWT tokens for stateless authentication
- Liquibase changelogs follow date-based organization (YYYY-MM format)
- Spring Boot DevTools not included (production-focused setup)