# Usa OpenJDK 21
FROM eclipse-temurin:21-jdk

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Construye el proyecto usando Gradle
RUN ./gradlew build -x test

# Expone el puerto que usa Spring Boot
EXPOSE 8080

# Comando para arrancar la aplicación
CMD ["java", "-jar", "build/libs/demo-0.0.1-SNAPSHOT.jar"]
