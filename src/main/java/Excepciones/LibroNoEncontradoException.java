package Excepciones;

public class LibroNoEncontradoException extends Exception {

    public LibroNoEncontradoException(String titulo) {
        super("El libro " + titulo + " no existe");
    }

    public LibroNoEncontradoException (int id) {
        super("El libro " + id + " no existe");
    }
}
