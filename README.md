# Sistema de Gestión de Biblioteca

Sistema de gestión de biblioteca desarrollado en Java como proyecto de práctica y portafolio. Permite administrar libros y usuarios, gestionar préstamos y devoluciones, aplicar reglas de negocio mediante excepciones y ejecutar pruebas automatizadas con JUnit.

## Funcionalidades

- Registro, búsqueda y eliminación de libros.
- Registro, búsqueda y eliminación de usuarios.
- Gestión de préstamos y devoluciones.
- Validación de libros ya registrados.
- Validación de libros no encontrados.
- Validación de libros actualmente prestados.
- Manejo de excepciones personalizadas.
- Diferenciación de tipos de usuario: estudiante, profesor y administrador.
- Pruebas automatizadas con JUnit 5.

## Tecnologías utilizadas

- **Java 25**
- **Maven**
- **JUnit 5**
- **Git**
- **GitHub**

## Estructura del proyecto

```text
biblioteca/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Admin.java
│   │       ├── Book.java
│   │       ├── GestionPrestamos.java
│   │       ├── Library.java
│   │       ├── Main.java
│   │       ├── OperacionesAdministrativas.java
│   │       ├── Prestamo.java
│   │       ├── Profesor.java
│   │       ├── Student.java
│   │       ├── User.java
│   │       ├── UserFactory.java
│   │       └── Excepciones/
│   │           ├── LibroNoEncontradoException.java
│   │           ├── LibroPrestadoException.java
│   │           ├── LibroYaExisteException.java
│   │           ├── TipoUsuarioInvalidoException.java
│   │           ├── UsuarioNoEncontradoException.java
│   │           └── UsuarioYaExisteException.java
│   └── test/
│       └── java/
│           └── LibraryTest.java
├── .gitignore
└── pom.xml
```

## Conceptos de programación aplicados

El proyecto utiliza varios conceptos importantes de programación orientada a objetos y desarrollo con Java:

- **Herencia:** `Student`, `Profesor` y `Admin` heredan de `User`.
- **Abstracción:** `User` define el comportamiento común de los usuarios.
- **Interfaces:** `GestionPrestamos` y `OperacionesAdministrativas` separan responsabilidades.
- **Encapsulamiento:** los atributos de las clases se mantienen privados y se accede a ellos mediante métodos.
- **Polimorfismo:** los diferentes tipos de usuario implementan comportamientos específicos.
- **Colecciones:** uso de `HashMap` y `ArrayList` para gestionar libros, usuarios y préstamos.
- **Excepciones personalizadas:** se controlan errores específicos del dominio de la aplicación.
- **Inmutabilidad:** varios atributos de las entidades principales se definen como `final`.
- **Pruebas automatizadas:** se utiliza JUnit 5 para verificar el comportamiento de la biblioteca.

## Gestión de datos

La clase `Library` centraliza la gestión del sistema utilizando:

- `HashMap<Integer, Book>` para almacenar libros por ID.
- `HashMap<Integer, User>` para almacenar usuarios por ID.
- `ArrayList<Prestamo>` para registrar los préstamos.

Esto permite realizar búsquedas y validaciones de forma organizada y mantener separadas las responsabilidades del sistema.

## Manejo de excepciones

El proyecto utiliza excepciones específicas para controlar situaciones como:

- Intentar registrar un libro que ya existe.
- Buscar un libro que no existe.
- Intentar eliminar un libro que actualmente está prestado.
- Registrar un usuario con un ID existente.
- Buscar un usuario que no existe.
- Utilizar un tipo de usuario no válido.

Esto permite que la lógica de negocio sea más clara y que los errores se gestionen de forma explícita.

## Pruebas

Las pruebas automatizadas están ubicadas en:

```text
src/test/java/LibraryTest.java
```

Para ejecutar las pruebas con Maven:

```bash
mvn clean test
```

El proyecto está configurado para Java 25 y las pruebas se ejecutan mediante JUnit 5.

## Ejecución del proyecto

### Requisitos

Antes de ejecutar el proyecto debes tener instalado:

- JDK 25 o compatible.
- Maven 3.9.x o compatible.

Puedes comprobar las versiones con:

```bash
java -version
mvn -version
```

### Clonar el repositorio

```bash
git clone https://github.com/Mvilla44/biblioteca.git
cd biblioteca
```

### Compilar y ejecutar las pruebas

```bash
mvn clean test
```

### Ejecutar la aplicación

La aplicación se puede ejecutar desde IntelliJ IDEA ejecutando la clase:

```text
Main.java
```

## Objetivo del proyecto

Este proyecto fue desarrollado como una aplicación práctica para reforzar conocimientos de Java y demostrar el uso de programación orientada a objetos, colecciones, interfaces, excepciones, pruebas automatizadas, Maven y control de versiones con Git.

## Autor

**Miguel Villa**

GitHub: [Mvilla44](https://github.com/Mvilla44)
