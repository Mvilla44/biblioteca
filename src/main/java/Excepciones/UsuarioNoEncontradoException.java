package Excepciones;

public class UsuarioNoEncontradoException extends Exception {

    public UsuarioNoEncontradoException(int id) {
        super("No existe un usuario con el ID: " + id);
    }
}
