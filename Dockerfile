# ===== 1️⃣ Build Stage =====
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn -q -e -B dependency:go-offline

# Copy application source
COPY src ./src

# Build project
RUN mvn -q -e -B clean package -DskipTests


# ===== 2️⃣ Run Stage =====
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
