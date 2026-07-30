# Build stage
FROM eclipse-temurin:21-jdk-jammy AS build
WORKDIR /build

# Copy Maven wrapper files
COPY mvnw .
COPY .mvn ./.mvn

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN chmod +x mvnw && ./mvnw dependency:go-offline

# Copy source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Create non-privileged user
RUN useradd -m -u 1001 appuser

# Copy built JAR from build stage
COPY --from=build /build/target/technical-portfolio-*.jar app.jar

# Switch to non-privileged user
USER appuser

# Expose port (adjust if your app uses a different port)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
