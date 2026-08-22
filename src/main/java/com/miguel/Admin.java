package com.miguel;

import com.miguel.Excepciones.*;

public class Admin extends User implements OperacionesAdministrativas {

    private final Library biblioteca;

    public Admin(String nombre, int id, Library biblioteca) {

        super(nombre, id);
        this.biblioteca = biblioteca;
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    @Override
    public void agregarLibro(Book libro) throws LibroYaExisteException {
            biblioteca.agregarLibro(libro);
    }

    @Override
    public void eliminarLibro(int id) throws LibroNoEncontradoException, LibroPrestadoException {
        biblioteca.eliminarLibro(id);
    }

    @Override
    public void agregarUsuario(User usuario) throws UsuarioYaExisteException {
        biblioteca.agregarUsuario(usuario);
    }

    @Override
    public void eliminarUsuario(int id) throws UsuarioNoEncontradoException {
            biblioteca.eliminarUsuario(id);
    }

    @Override
    public void mostrarInformacion() {

        super.mostrarInformacion();
        System.out.println("Tipo de usuario: " + getTipoUsuario());
    }
}
