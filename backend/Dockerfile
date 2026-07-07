# =========================
# 1️⃣ Build stage
# =========================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml primero para cachear dependencias
COPY pom.xml .

# Descargar dependencias offline
RUN mvn dependency:go-offline

# Copiar código fuente
COPY src ./src

# Compilar JAR sin tests
RUN mvn clean package -DskipTests

# =========================
# 2️⃣ Runtime stage
# =========================
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Crear usuario no-root para mayor seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Cambiar a usuario no-root
USER appuser

# Copiar JAR desde el stage de build
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto de Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]