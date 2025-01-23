# Etapa 1: Compilar el intérprete en Go
ARG GO_VERSION=1
FROM golang:${GO_VERSION}-bookworm as go-builder

# Configurar el directorio de trabajo y clonar el repositorio del intérprete
WORKDIR /tmp/interpreter
RUN git clone https://github.com/elias-gill/interpreter.git .

# Compilar el intérprete en Go
RUN CGO_ENABLED=0 go build -v -o interpreter .

# Etapa 2: Compilar el proyecto Java con Maven
FROM maven:3.8.6-openjdk-11 as java-builder

# Configurar el directorio de trabajo y copiar los archivos del proyecto Java
WORKDIR /usr/src/app
COPY pom.xml .
COPY src ./src

# Compilar el proyecto Java con Maven
RUN mvn clean package -DskipTests

# Etapa 3: Crear la imagen final
FROM openjdk:11-jre-slim

# Copiar el intérprete compilado desde la etapa de Go
COPY --from=go-builder /tmp/interpreter/interpreter /bin/interpreter

# Copiar el JAR compilado desde la etapa de Java
COPY --from=java-builder /usr/src/app/target/blade_app-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/src/app/blade_app.jar

# Establecer el comando de ejecución
CMD ["java", "-jar", "/usr/src/app/blade_app.jar"]
