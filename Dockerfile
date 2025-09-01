FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Copie le JAR depuis l'hôte (pas depuis un stage build)
COPY target/dailymon-0.6.0.jar .
CMD ["java", "-jar", "-Dspring.profiles.active=production", "dailymon-0.6.0.jar"]