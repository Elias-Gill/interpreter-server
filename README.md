# Interpreted Code Executor Server

Este servidor de backend ejecuta código usando mi intérprete personal y devuelve los
resultados.
Incluye una página de frontend sencilla y un único punto de acceso API.

## Características principales

- Punto de acceso API:
  `/interpreter`
- Retorna resultados en formato JSON
- Página de frontend simple en "/"
- Implementado con Java Blade en lugar de Spring Boot (experimento)
- Requiere Java 8+ y Maven para ejecución
- Utiliza Maven Assembly Plugin para compilación
- Genera un JAR con dependencias
- Incluye Makefile para facilitar el desarrollo

## Requisitos de instalación

- Java 8 o superior
- Maven instalado
- (Opcional) Make instalado para uso del Makefile

## Cómo ejecutar el proyecto

1. Clona el repositorio
2. Ejecuta `mvn clean package` para compilar y generar el JAR en la carpeta "target/"
3. Corre el JAR `"with-dependencies" usando java

## Notas importantes

- Este proyecto es muy simple y no incluye pruebas unitarias.
- La implementación con Java Blade es experimental y puede ser reemplazada por Spring Boot en
  futuras versiones.

Licenciado bajo la Licencia MIT.
