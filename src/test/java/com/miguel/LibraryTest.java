package com.miguel;

import com.miguel.Excepciones.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

    @Test
    void deberiaAgregarLibro() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        biblioteca.agregarLibro(libro);

        Book resultado = biblioteca.buscarLibroPorId(1);

        assertEquals(libro, resultado);
    }

    @Test
    void deberiaLanzarExcepcionSiLibroNoExiste() {

        Library biblioteca = new Library();

        LibroNoEncontradoException excepcion = assertThrows(
                LibroNoEncontradoException.class,
                () -> biblioteca.buscarLibroPorId(999)
        );

        assertEquals(
                "El libro 999 no existe",
                excepcion.getMessage()
        );
    }

    @Test
    void noDeberiaPrestarLibroQueYaEstaPrestado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student estudiante = new Student("Miguel", 1);
        Student estudiante2 = new Student("Carlos", 2);

        biblioteca.agregarLibro(libro);

        biblioteca.prestarLibro(
                "El principito",
                estudiante
        );

        assertThrows(
                LibroPrestadoException.class,
                () -> biblioteca.prestarLibro(
                        "El principito",
                        estudiante2
                )
        );
    }

    @Test
    void deberiaDetectarLibroPrestado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student estudiante = new Student("Miguel", 1);

        biblioteca.agregarLibro(libro);

        assertFalse(biblioteca.estaPrestado(libro));

        biblioteca.prestarLibro("El principito", estudiante);

        assertTrue(biblioteca.estaPrestado(libro));
    }

    @Test
    void deberiaDevolverLibroCorrectamente() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student estudiante = new Student("Miguel", 1);

        biblioteca.agregarLibro(libro);

        // Inicialmente está disponible
        assertFalse(biblioteca.estaPrestado(libro));

        // Se presta el libro
        biblioteca.prestarLibro("El principito", estudiante);

        // Ahora está prestado
        assertTrue(biblioteca.estaPrestado(libro));

        // El estudiante lo devuelve
        biblioteca.devolverLibro("El principito", estudiante);

        // Nuevamente esta disponible
        assertFalse(biblioteca.estaPrestado(libro));
    }

    @Test
    void noDeberiaDevolverLibroQueNoTienePrestado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student estudiante = new Student("Miguel", 1);

        biblioteca.agregarLibro(libro);

        assertThrows(
                IllegalStateException.class,
                () -> biblioteca.devolverLibro(
                        "El principito",
                        estudiante
                )
        );
    }

    @Test
    void noDeberiaPermitirDevolverLibroDeOtroUsuario() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student miguel = new Student("Miguel", 1);
        Student carlos = new Student("Carlos", 2);

        biblioteca.agregarLibro(libro);

        biblioteca.prestarLibro("El principito", miguel);

        assertThrows(
                IllegalStateException.class,
                () -> biblioteca.devolverLibro(
                        "El principito",
                        carlos
                )
        );

        // El libro debe continuar prestado
        assertTrue(biblioteca.estaPrestado(libro));
    }

    @Test
    void deberiaAgregarUsuario() throws Exception {

        Library biblioteca = new Library();

        Student miguel = new Student("Miguel", 1);

        biblioteca.agregarUsuario(miguel);

        User resultado = biblioteca.buscarUsuario(1);

        assertEquals(miguel, resultado);
    }

    @Test
    void noDeberiaAgregarUsuarioConIdDuplicado() throws Exception {

        Library biblioteca = new Library();

        Student miguel = new Student("Miguel", 1);
        Student carlos = new Student("Carlos", 1);

        biblioteca.agregarUsuario(miguel);

        UsuarioYaExisteException excepcion = assertThrows(
                UsuarioYaExisteException.class,
                () -> biblioteca.agregarUsuario(carlos)
        );

        assertEquals(
                "Ya existe un usuario con el ID: 1",
                excepcion.getMessage()
        );
    }

    @Test
    void noDeberiaEliminarUsuarioInexistente() throws Exception {

        Library biblioteca = new Library();

        assertThrows(
                UsuarioNoEncontradoException.class,
                () -> biblioteca.eliminarUsuario(1)
        );
    }

    @Test
    void deberiaRetornarNullSiUsuarioNoExiste() {

        Library biblioteca = new Library();

        User resultado = biblioteca.buscarUsuario(1);

        assertNull(resultado);
    }

    @Test
    void noDeberiaEliminarUsuarioConLibroPrestado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student miguel = new Student("Miguel", 1);

        biblioteca.agregarUsuario(miguel);
        biblioteca.agregarLibro(libro);

        biblioteca.prestarLibro("El principito", miguel);

        assertThrows(
                IllegalStateException.class,
                () -> biblioteca.eliminarUsuario(1)
        );

        assertEquals(
                miguel,
                biblioteca.buscarUsuario(1)
        );
    }

    @Test
    void deberiaLanzarExcepcionSiElTipoDeUsuarioEsInvalido() throws Exception {

        Library biblioteca = new Library();

        assertThrows(
                TipoUsuarioInvalidoException.class,
                () -> UserFactory.crearUsuario(
                        "Miguel",
                        1,
                        4,
                        biblioteca
                )
        );
    }

    @Test
    void deberiaAgregarEstudiante() throws Exception {

        Library biblioteca = new Library();

        User usuario =  UserFactory.crearUsuario(
                "Miguel",
                1,
                1,
                biblioteca
        );

        biblioteca.agregarUsuario(usuario);

        assertInstanceOf(
                Student.class,
                usuario
        );
    }

    @Test
    void deberiaAgregarProfesor() throws Exception {

        Library biblioteca = new Library();

        User usuario =  UserFactory.crearUsuario(
                "Miguel",
                1,
                3,
                biblioteca
        );

        biblioteca.agregarUsuario(usuario);

        assertInstanceOf(
                Profesor.class,
                usuario
        );
    }

    @Test
    void deberiaAgregarAdministrador() throws Exception {

        Library biblioteca = new Library();

        User usuario =  UserFactory.crearUsuario(
                "Miguel",
                1,
                2,
                biblioteca
        );

        biblioteca.agregarUsuario(usuario);

        assertInstanceOf(
                Admin.class,
                usuario
        );
    }

    @Test
    void noDeberiaAgregarLibroConIdDuplicado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Book libro2 = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        biblioteca.agregarLibro(libro);

        LibroYaExisteException exception = assertThrows(
                LibroYaExisteException.class,
                () -> biblioteca.agregarLibro(libro2)
        );

        assertEquals(
                "Ya existe un libro con el ID: 1",
                exception.getMessage()
        );
    }

    @Test
    void deberiaBuscarLibroPorTituloSinImportarMayusculas() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        biblioteca.agregarLibro(libro);

        assertEquals(
                libro,
                biblioteca.buscarLibroPorTitulo("EL PRINCIPITO")
        );
    }

    @Test
    void deberiaLanzarExcepcionSiElLibroNoExistePorTitulo() throws Exception {

        Library biblioteca = new Library();

        LibroNoEncontradoException exception = assertThrows(
                LibroNoEncontradoException.class,
                () -> biblioteca.buscarLibroPorTitulo("Libro inexistente")
        );

        assertEquals(
                "El libro Libro inexistente no existe",
                exception.getMessage()
        );
    }

    @Test
    void deberiaEliminarLibroCorrectamente() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        biblioteca.agregarLibro(libro);

        assertEquals(
                libro,
                biblioteca.buscarLibroPorId(1)
        );

        biblioteca.eliminarLibro(1);

        assertThrows(
                LibroNoEncontradoException.class,
                () -> biblioteca.buscarLibroPorId(1)
        );
    }

    @Test
    void noDeberiaEliminarLibroPrestado() throws Exception {

        Library biblioteca = new Library();

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        biblioteca.agregarLibro(libro);

        Student student = new Student(
                "Miguel",
                1
        );

        biblioteca.agregarUsuario(student);

        biblioteca.prestarLibro("El principito", student);

        assertThrows(
                LibroPrestadoException.class,
                () -> biblioteca.eliminarLibro(1)
        );

        assertEquals(
                libro,
                biblioteca.buscarLibroPorId(1)
        );
    }

    @Test
    void noDeberiaCrearPrestamosSinLibro() throws Exception {

        Student student = new Student(
                "Miguel",
                1
        );

        assertThrows(
                NullPointerException.class,
                () -> new Prestamo(null, student)
        );
    }

    @Test
    void noDeberiaCrearPrestamosSinUsuario() throws Exception {

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        assertThrows(
                NullPointerException.class,
                () -> new Prestamo(libro, null)
        );
    }

    @Test
    void noDeberiaDevolverPrestamoDosVeces() throws Exception {

        Book libro = new Book(
                1,
                "El principito",
                "Antoine de Saint-Exupery",
                1943
        );

        Student student = new Student("Miguel", 1);

        Prestamo prestamo = new Prestamo(libro, student);

        prestamo.devolver();

        assertThrows(
                IllegalStateException.class,
                prestamo::devolver
        );
    }
}

