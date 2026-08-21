package Excepciones;

public class LibroPrestadoException extends Exception {

    public LibroPrestadoException(String titulo) {
        super("El libro " + titulo + " ya esta prestado");
    }

    public LibroPrestadoException(int id) {
        super("El libro con ID " + id + " esta prestado");
    }
}
