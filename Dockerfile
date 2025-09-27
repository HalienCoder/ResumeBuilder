# ----------- Build Stage -----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# ----------- Run Stage -----------
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=build /app/target/app.jar ./app.jar
ENV PORT=$PORT
EXPOSE $PORT
CMD ["java", "-jar", "app.jar"]
