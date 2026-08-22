# Sistema de Gestión de Biblioteca

Aplicación de consola desarrollada en **Java** para administrar una biblioteca mediante libros, usuarios y préstamos. El proyecto fue construido como práctica de programación orientada a objetos y como proyecto de portafolio, incorporando **Maven, JUnit 5 y Git/GitHub**.

## Características

- Gestión de libros: agregar, buscar, mostrar y eliminar.
- Gestión de usuarios: agregar, buscar, mostrar y eliminar.
- Préstamo y devolución de libros.
- Control de libros disponibles y prestados.
- Restricciones de negocio, como impedir préstamos duplicados o eliminar libros prestados.
- Tres tipos de usuario: `Student`, `Profesor` y `Admin`.
- Creación de usuarios mediante `UserFactory`.
- Excepciones personalizadas para errores del dominio.
- Pruebas automatizadas con JUnit 5.

## Tecnologías

| Tecnología | Uso |
|---|---|
| Java 25 | Lenguaje y desarrollo de la aplicación |
| Maven | Gestión del proyecto, dependencias y ejecución de pruebas |
| JUnit 5 | Pruebas automatizadas |
| Git | Control de versiones |
| GitHub | Repositorio y publicación del proyecto |

## Arquitectura y diseño

El proyecto aplica principios de programación orientada a objetos para separar responsabilidades y representar el dominio de la biblioteca.

### Principales clases

- `Library`: administra libros, usuarios y préstamos y concentra las reglas principales del negocio.
- `Book`: representa un libro y sus datos principales.
- `User`: clase abstracta base para los usuarios.
- `Student`, `Profesor` y `Admin`: especializaciones de `User`.
- `Prestamo`: representa un préstamo y controla su estado activo o devuelto.
- `UserFactory`: centraliza la creación de los diferentes tipos de usuario.
- `GestionPrestamos`: define las operaciones relacionadas con préstamos y devoluciones.
- `OperacionesAdministrativas`: define operaciones administrativas de la biblioteca.

### Conceptos aplicados

- **Encapsulamiento:** atributos privados y acceso mediante métodos.
- **Herencia:** `Student`, `Profesor` y `Admin` heredan de `User`.
- **Abstracción:** `User` define el comportamiento común de los usuarios.
- **Polimorfismo:** cada tipo de usuario puede implementar su comportamiento específico.
- **Interfaces:** separación de responsabilidades mediante `GestionPrestamos` y `OperacionesAdministrativas`.
- **Colecciones:** uso de `Map` y `List` para gestionar entidades y préstamos.
- **Excepciones personalizadas:** errores específicos del dominio tratados de forma explícita.
- **Factory Pattern:** `UserFactory` encapsula la creación de usuarios.
- **Inmutabilidad:** identificadores y referencias que no deben cambiar se mantienen como `final`.

## Estructura del proyecto

```text
biblioteca/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── miguel/
│   │               ├── Admin.java
│   │               ├── Book.java
│   │               ├── GestionPrestamos.java
│   │               ├── Library.java
│   │               ├── Main.java
│   │               ├── OperacionesAdministrativas.java
│   │               ├── Prestamo.java
│   │               ├── Profesor.java
│   │               ├── Student.java
│   │               ├── User.java
│   │               ├── UserFactory.java
│   │               └── Excepciones/
│   │                   ├── LibroNoEncontradoException.java
│   │                   ├── LibroPrestadoException.java
│   │                   ├── LibroYaExisteException.java
│   │                   ├── TipoUsuarioInvalidoException.java
│   │                   ├── UsuarioNoEncontradoException.java
│   │                   └── UsuarioYaExisteException.java
│   └── test/
│       └── java/
│           └── com/
│               └── miguel/
│                   └── LibraryTest.java
├── .gitignore
├── pom.xml
└── README.md
```

## Pruebas automatizadas

El proyecto cuenta actualmente con **22 pruebas automatizadas** para validar escenarios de libros, usuarios, préstamos, devoluciones, excepciones y creación de usuarios.

Las pruebas se encuentran en:

```text
src/test/java/com/miguel/LibraryTest.java
```

Para ejecutarlas:

```bash
mvn clean test
```

El resultado esperado es:

```text
BUILD SUCCESS
```

## Ejemplos de reglas de negocio probadas

Entre los escenarios cubiertos por las pruebas se encuentran:

- No permitir registrar dos libros con el mismo ID.
- Lanzar una excepción cuando se busca un libro inexistente.
- No permitir prestar un libro que ya está prestado.
- Permitir devolver correctamente un libro prestado.
- Impedir que un usuario devuelva un libro que pertenece a otro préstamo.
- No permitir registrar usuarios con IDs duplicados.
- No permitir eliminar usuarios que mantienen préstamos activos.
- Validar tipos de usuario no soportados en `UserFactory`.
- Impedir crear préstamos sin libro o usuario válidos.
- Impedir devolver un préstamo que ya fue cerrado.

## Gestión de datos

`Library` mantiene la información en memoria mediante colecciones de Java:

- `Map<Integer, Book>` para los libros identificados por ID.
- `Map<Integer, User>` para los usuarios identificados por ID.
- `List<Prestamo>` para los préstamos registrados.

Se utiliza el tipo de colección (`Map`/`List`) en las declaraciones para reducir el acoplamiento con una implementación concreta y mantener el diseño más flexible.

## Excepciones personalizadas

El proyecto cuenta con excepciones específicas para representar errores del dominio, entre ellas:

- `LibroNoEncontradoException`
- `LibroPrestadoException`
- `LibroYaExisteException`
- `TipoUsuarioInvalidoException`
- `UsuarioNoEncontradoException`
- `UsuarioYaExisteException`

Esto permite separar los errores propios de la aplicación de errores genéricos de ejecución.

## Requisitos

- **JDK 25**
- **Maven 3.9.x** o compatible
- Git, si se desea clonar el repositorio

Comprobar versiones:

```bash
java -version
mvn -version
```

## Instalación y ejecución

Clonar el proyecto:

```bash
git clone https://github.com/Mvilla44/biblioteca.git
cd biblioteca
```

Ejecutar las pruebas:

```bash
mvn clean test
```

Ejecutar la aplicación desde IntelliJ IDEA mediante:

```text
com.miguel.Main
```

## Objetivo del proyecto

Este proyecto busca demostrar el dominio práctico de conceptos fundamentales de Java y herramientas utilizadas en un flujo de desarrollo real: programación orientada a objetos, colecciones, interfaces, herencia, polimorfismo, excepciones personalizadas, pruebas automatizadas, Maven y control de versiones con Git.

## Autor

**Miguel Villa**

GitHub: [Mvilla44](https://github.com/Mvilla44)
