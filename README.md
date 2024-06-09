# Baymax-RAS

## What is the project?
This is a IA based project that give a diagnosis to doctors and help them at the first contact to pacient.

# Backend

## What's the stack?
Java 21 and Spring Boot

### How to run with Docker - (Linux)?
- run `docker build -t baymax-backend:latest .`
- run `docker run -p 8080:8080 baymax-backend:latest`

### How to run with docker-compose - (Linux)?
- run `docker-compose up --build -d`

### Check container status
- check if container is running `docker ps`
- check container logs `docker logs <container_name> -f`