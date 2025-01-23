# Interpreted Code Executor Server

Este servidor de backend ejecuta código usando mi
[intérprete](github.com/elias-gill/interpreter) personal y devuelve los resultados.
Incluye una página de frontend sencilla y un único punto de acceso API.

## Características de la API

- Único endpoint de la API:
  `/interpreter`
- Estructura del JSON de entrada:
```json 
{ 
    "mode": "eval|lexer|parser", 
    "code": "código a ejecutar" 
} 
  ```
- Estructura del JSON de salida:
- 
```json
{ 
    "output": "resultado de la ejecución", 
    "errors": "cualquier error ocurrido durante la ejecución" 
} 
  ```

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

[Licencia MIT](LICENSE).
