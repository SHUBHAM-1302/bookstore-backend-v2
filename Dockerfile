Dockerfile
# Use Java 21 as base image
FROM eclipse-temurin:21-jdk

# Set working directory inside container
WORKDIR /app

# Copy jar file from target folder to container
COPY target/bookStore-1.0-SNAPSHOT.jar app.jar

# Expose application port (change if needed)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
