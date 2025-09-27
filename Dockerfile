# Use official OpenJDK 21 image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Build the application (if target JAR not present)
RUN ./mvnw clean package || true

# Use environment variable PORT from Render
ENV PORT=$PORT

# Expose the port
EXPOSE $PORT

# Run the application
CMD ["java", "-jar", "target/Resume-Maker-HTML-0.0.1-SNAPSHOT.jar"]
