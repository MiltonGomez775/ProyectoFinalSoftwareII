FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# Dar permisos de ejecución a gradlew
RUN chmod +x ./gradlew

# Construir el proyecto ignorando tests
RUN ./gradlew build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
