import java.util.Scanner;
import Excepciones.*;

public class Main {

     public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Library biblioteca = new Library();

        Book libro0 = new Book(1,"Alicia en el país de las maravillas", "Lewis Carroll", 1871);
        Book libro1 = new Book(2, "Don quijote de la mancha", "Miguel de Cervantes", 1605);
        Book libro2 = new Book(3,"100 años de soledad", "Gabriel Garcia Marquez", 1967);
        Book libro3 = new Book(4,"Orgullo y prejuicio", "Jane Austen", 1813);

        try {
            biblioteca.agregarLibro(libro0);
            biblioteca.agregarLibro(libro1);
            biblioteca.agregarLibro(libro2);
            biblioteca.agregarLibro(libro3);
        } catch (LibroYaExisteException e) {
            System.out.println(e.getMessage());
        }

        Student estudiante = new Student("Miguel", 1);
        Profesor profesor = new Profesor("Carlos", 2);
        Admin admin = new Admin("Laura", 3, biblioteca);

        try {
            biblioteca.agregarUsuario(estudiante);
            biblioteca.agregarUsuario(profesor);
            biblioteca.agregarUsuario(admin);
        } catch (UsuarioYaExisteException Ue) {
            System.out.println(Ue.getMessage());
        }

        OperacionesAdministrativas gestor = admin;

        try {
            gestor.agregarLibro(
                    new Book(6, "El principito", "Antoine de Saint-Exupery", 1943)
            );
        } catch (LibroYaExisteException e) {
            System.out.println(e.getMessage());
        }

        biblioteca.mostrarLibros();

        biblioteca.mostrarUsuarios();

        int opcion;
        boolean continuar = true;

        while (continuar) {

                System.out.println("==========BIBLIOTECA=========");
                System.out.println("1. Agregar libro");
                System.out.println("2. Mostrar libro");
                System.out.println("3. Buscar libro");
                System.out.println("4. Prestar libro");
                System.out.println("5. Devolver libro");
                System.out.println("6. Eliminar libro");
                System.out.println("7. Mostrar usuario");
                System.out.println("8. Agregar usuario");
                System.out.println("9. Mostrar los libros prestados");
                System.out.println("10. Eliminar usuarios");
                System.out.println("11. Salir");

                System.out.println("Selecciona una opción: ");

                opcion = scanner.nextInt();

            switch (opcion) {

                case 1:

                    scanner.nextLine();

                    System.out.println("Introduce el ID");
                    int idIngresado = scanner.nextInt();

                    scanner.nextLine();

                    System.out.println("Titulo: ");
                    String titulo = scanner.nextLine();

                    System.out.println("Autor: ");
                    String autor = scanner.nextLine();

                    System.out.println("Año: ");
                    int año = scanner.nextInt();

                    Book libro = new Book(idIngresado, titulo, autor, año);

                    try {
                        admin.agregarLibro(libro);
                        System.out.println("Libro agregado correctamente\n\n");
                    } catch (LibroYaExisteException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 2:

                    System.out.println("Los libros que tenemos: ");
                    biblioteca.mostrarLibros();
                    System.out.println("\n\n");

                    break;

                case 3:

                    scanner.nextLine();

                    System.out.println("Ingrese el titulo del libro: ");
                    String tituloIngresado = scanner.nextLine();

                    try {
                        Book libroEncontrado = biblioteca.buscarLibroPorTitulo(tituloIngresado);
                        System.out.println("Libro encontrado correctamente: ");
                        libroEncontrado.mostrarInformacion();
                    } catch (LibroNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 4:

                    scanner.nextLine();

                    System.out.println("Ingrese el titulo del libro: ");
                    String tituloIngresado1 = scanner.nextLine();

                    System.out.println("Ingrese el ID del usuario");
                    int idIngresado5 = scanner.nextInt();

                    GestionPrestamos prestador = biblioteca.buscarGestorPrestamos(idIngresado5);

                    if (prestador == null) {
                        System.out.println("El usuario no existe o no puede solicitar libros");
                    } else {
                        try {
                            prestador.prestarLibro(biblioteca, tituloIngresado1);
                            System.out.println("Libro prestado correctamente\n\n");
                        } catch (LibroNoEncontradoException | LibroPrestadoException ep) {
                            System.out.println(ep.getMessage());
                        }
                    }

                    break;

                case 5:

                    scanner.nextLine();

                    System.out.println("Ingrese el titulo del libro: ");
                    String tituloIngresado2 = scanner.nextLine();

                    System.out.println("Ingrese el ID del usuario");
                    int idIngresado3 = scanner.nextInt();

                    GestionPrestamos usuario2 = biblioteca.buscarGestorPrestamos(idIngresado3);

                    if (usuario2 == null) {
                        System.out.println("El usuario no existe o no puede devolver libros");
                    } else {
                        try {
                            usuario2.devolverLibro(biblioteca, tituloIngresado2);
                            System.out.println("Libro devuelto correctamente\n\n");
                        } catch (LibroNoEncontradoException | IllegalStateException ni) {
                            System.out.println(ni.getMessage());
                        }
                    }

                    break;

                case 6:

                    scanner.nextLine();

                    System.out.println("Ingrese el ID del libro que desea eliminar: ");
                    int idIngresado1 = scanner.nextInt();

                    try {
                        biblioteca.eliminarLibro(idIngresado1);
                        System.out.println("Libro eliminado correctamente\n\n");
                    } catch (LibroNoEncontradoException | LibroPrestadoException ep) {
                        System.out.println(ep.getMessage());
                    }

                    break;

                case 7:

                    System.out.println("Los usuarios que pertenecen a la biblioteca son: ");

                    biblioteca.mostrarUsuarios();

                    System.out.println("\n\n");

                    break;

                case 8:

                    scanner.nextLine();

                    System.out.println("Ingrese su nombre: ");
                    String nombreIngresado = scanner.nextLine();

                    System.out.println("Ingrese su ID: ");
                    int idIngresado0 = scanner.nextInt();

                    System.out.println("Que tipo de usuario deseas agregar\n");
                    System.out.println("1. Estudiante");
                    System.out.println("2. Administrador");
                    System.out.println("3. Docente");

                    int tipoUsuario = scanner.nextInt();

                    try {

                        User usuarioNuevo = UserFactory.crearUsuario(nombreIngresado, idIngresado0, tipoUsuario, biblioteca);

                        biblioteca.agregarUsuario(usuarioNuevo);

                        System.out.println("Usuario agregado correctamente\n\n");

                    } catch (UsuarioYaExisteException | TipoUsuarioInvalidoException ei) {

                        System.out.println(ei.getMessage());
                    }

                    break;

                case 9:

                    System.out.println("Ingrese el ID del usuario correspondiente: ");
                    int idIngresado2 = scanner.nextInt();

                    try {
                        biblioteca.mostrarLibrosPrestados(idIngresado2);
                        System.out.println("\n\n");
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 10:

                    System.out.println("Ingrese el ID del usuario: ");
                    int idIngresado4 = scanner.nextInt();

                    try {
                        biblioteca.eliminarUsuario(idIngresado4);
                        System.out.println("Usuario eliminado correctamente\n\n");
                    } catch (UsuarioNoEncontradoException | IllegalStateException ni) {
                        System.out.println(ni.getMessage());
                    }

                    break;

                case 11:

                    System.out.println("Saliendo del sistema...");
                    continuar = false;

                    break;

                default:

                    System.out.println("Opción invalida");
            }
        }

        scanner.close();
    }
}
