import Excepciones.*;

public interface GestionPrestamos {

    void prestarLibro(Library biblioteca, String titulo)
            throws LibroNoEncontradoException, LibroPrestadoException;

    void devolverLibro(Library biblioteca, String titulo)
            throws LibroNoEncontradoException;
}
