# DailyFeed Member Service

This project is the **Member Service** for the DailyFeed platform. It handles user registration, authentication, profile management, and social follow/unfollow functionality.

## Project Overview

- **Service Name**: `dailyfeed-member`
- **Port**: 8084 (default)
- **Technology Stack**:
    - **Language**: Java 17
    - **Framework**: Spring Boot 3.5.5
    - **Persistence**: MySQL (JPA + QueryDSL), MongoDB, Redis
    - **Messaging**: Kafka (Event-driven architecture)
    - **Communication**: Feign Client (Spring Cloud OpenFeign)
    - **Authentication**: JWT (JSON Web Token)
    - **Build Tool**: Gradle (Multi-module)
    - **Containerization**: Jib (Docker)
    - **Orchestration**: Kubernetes (Kind for local development)

## Architecture & Modules

The project is structured as a multi-module Gradle project:

1.  **`dailyfeed-member`**: The main executable Spring Boot application.
2.  **`dailyfeed-code`**: Contains shared domain objects, DTOs, and global utilities used across services.
3.  **`dailyfeed-feign`**: Centralized Feign client configurations.
4.  **`dailyfeed-pagination-support`**: Reusable logic for handling pagination consistently.
5.  **`dailyfeed-redis-support`**: Reusable Redis logic and configurations.

## Building and Running

### Prerequisites
- Java 17 or higher
- Docker (for running infrastructure)

### Local Development (Native JAR + Docker-Compose Infra)
1.  Set up infrastructure (MySQL, MongoDB, Redis, Kafka):
    ```bash
    cd dailyfeed-infrastructure/docker/mysql-mongodb-redis
    docker-compose up -d
    ```
2.  Run the application:
    ```bash
    ./run-local-was.sh
    ```
    This runs the application with the `local-was` profile.

### Building Docker Images
Use Jib to build images directly to Docker or a registry:
```bash
./jibDockerBuild.sh {IMAGE_VERSION}
```

### Refreshing Dependencies
If you encounter issues with missing classes or dependencies:
```bash
./gradlew clean build --refresh-dependencies
```

## Development Conventions

### Domain Organization
The project follows a domain-centric organization within `click.dailyfeed.member.domain`:
- `authentication`: Security and login logic.
- `member`: Core user management.
- `follow`: Social relationship logic.
- `jwt`: Token management.

### Configuration
- **Profiles**: `local-was` (Native), `local` (K8s/Kind), `dev` (Hybrid).
- **Feign Config**: Externalized in `src/main/resources/feign-config-*.yaml`.

### Testing
- Integrated tests often use the `local-was` profile to interact with real infrastructure containers.
- Sample data can be inserted via specific test cases (e.g., `SignupInsertLocalWASTest`).

## Key Files
- `README.md`: Detailed execution and deployment guide.
- `build.gradle.kts`: Root build configuration and Jib settings.
- `dailyfeed-member/src/main/resources/application-*.yaml`: Profile-specific configurations.
- `MemberApplication.java`: Main entry point.
