FROM maven:latest AS builder

COPY backend /app

WORKDIR /app

RUN mvn clean package

FROM openjdk:21-jdk-slim

COPY --from=builder /app/target/Baymax-0.0.1-SNAPSHOT.jar /app/Baymax-0.0.1-SNAPSHOT.jar

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "Baymax-0.0.1-SNAPSHOT.jar"]