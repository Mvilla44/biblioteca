import Excepciones.*;

public class Student extends User implements GestionPrestamos {

    public Student(String nombre, int id) {
        super(nombre, id);
    }

    public String getTipoUsuario () {
        return "Estudiante";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de usuario: " + getTipoUsuario());
    }

    @Override
    public void prestarLibro(Library biblioteca, String titulo) throws LibroNoEncontradoException, LibroPrestadoException {
        biblioteca.prestarLibro(titulo, this);
    }

    @Override
    public void devolverLibro(Library biblioteca, String titulo) throws LibroNoEncontradoException {
        biblioteca.devolverLibro(titulo, this);
    }
}
