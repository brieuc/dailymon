# Use an temurin with Maven image as the base image
FROM maven:3.9-eclipse-temurin-21-alpine AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .

COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copie le JAR depuis l'hôte (pas depuis un stage build)
COPY --from=build /app/target/dailymon-0.6.0.jar .
CMD ["java", "-jar", "-Dspring.profiles.active=production", "dailymon-0.6.0.jar"]