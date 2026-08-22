package com.miguel;

import com.miguel.Excepciones.LibroNoEncontradoException;
import com.miguel.Excepciones.LibroPrestadoException;

public class Profesor extends User implements GestionPrestamos {

    public Profesor(String nombre, int id) {
        super(nombre, id);
    }

    @Override
    public String getTipoUsuario() {
        return "Docente";
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Tipo de usuario: " + getTipoUsuario());
    }

    public void prestarLibro(Library biblioteca, String titulo) throws LibroNoEncontradoException, LibroPrestadoException {
            biblioteca.prestarLibro(titulo, this);
    }

    public void devolverLibro(Library biblioteca, String titulo) throws  LibroNoEncontradoException {
            biblioteca.devolverLibro(titulo, this);
    }
}
