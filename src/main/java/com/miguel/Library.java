package com.miguel;

import java.util.ArrayList;
import java.util.HashMap;

import com.miguel.Excepciones.*;

public class Library implements OperacionesAdministrativas {

    private final HashMap<Integer, Book> libros;

    private final HashMap<Integer, User> usuarios;

    private final ArrayList<Prestamo> prestamos;

    public Library() {

        libros = new HashMap<>();

        usuarios = new HashMap<>();

        prestamos = new ArrayList<>();
    }

    public boolean estaPrestado(Book libro) {

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getLibro() == libro && prestamo.estaActivo()) {
                return true;
            }
        }

        return false;
    }

    public Book buscarLibroPorTitulo(String titulo) throws LibroNoEncontradoException {

        for (Book libro : libros.values()) {

            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }

        throw new LibroNoEncontradoException(titulo);
    }

    public Book buscarLibroPorId(int id) throws LibroNoEncontradoException {

        Book libro = libros.get(id);

        if (libro == null) {
            throw new LibroNoEncontradoException(id);
        }

        return libro;
    }

    public void agregarLibro(Book libro) throws LibroYaExisteException {

        if (libros.containsKey(libro.getId())) {

            throw new LibroYaExisteException(libro.getId());
        }

        libros.put(libro.getId(), libro);
    }

    public void eliminarLibro(int id) throws LibroNoEncontradoException, LibroPrestadoException {

        Book libro = buscarLibroPorId(id);

        if (estaPrestado(libro)) {
            throw new LibroPrestadoException(id);
        }

        libros.remove(id);
    }


    public void mostrarLibros() {

        for (Book libro : libros.values()) {
            libro.mostrarInformacion();
        }
    }

    public void prestarLibro(String titulo, User usuario) throws LibroNoEncontradoException, LibroPrestadoException {

        Book libro = buscarLibroPorTitulo(titulo);

        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede estar vacío");
        }

        if (estaPrestado(libro)) {
            throw new LibroPrestadoException(libro.getId());
        }

        Prestamo nuevoPrestamo = new Prestamo(libro, usuario);

        prestamos.add(nuevoPrestamo);
    }

    public void devolverLibro(String titulo, User usuario) throws LibroNoEncontradoException {

        Book libro = buscarLibroPorTitulo(titulo);

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getLibro() == libro && prestamo.getUsuario().equals(usuario) && prestamo.estaActivo()) {

                prestamo.devolver();
                return;
            }
        }

        throw new IllegalStateException("El usuario no tiene prestado este libro");
    }

    public void agregarUsuario(User usuario) throws UsuarioYaExisteException {

    if (usuario == null) {
        throw new IllegalArgumentException("El usuario no puede estar vacío");
    }

    if (usuarios.containsKey(usuario.getId())) {
        throw new UsuarioYaExisteException(usuario.getId());
    }

        usuarios.put(usuario.getId(), usuario);
    }

    public void eliminarUsuario(int id) throws UsuarioNoEncontradoException {

        User usuario = buscarUsuario(id);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(id);
        }

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getUsuario().equals(usuario) && prestamo.estaActivo()) {

                throw new IllegalStateException("El usuario tiene libros prestados y no puede ser eliminado");
            }
        }

        usuarios.remove(id);
    }

    public User buscarUsuario(int id) {

        return usuarios.get(id);
    }

    public void mostrarUsuarios() {

        for (User usuario : usuarios.values()) {
            usuario.mostrarInformacion();
        }
    }

    public void mostrarLibrosPrestados(int idUsuario) throws UsuarioNoEncontradoException {

        User usuario = buscarUsuario(idUsuario);

        if (usuario == null) {
            throw new UsuarioNoEncontradoException(idUsuario);
        }

        boolean encontroLibros = false;

        for (Prestamo prestamo : prestamos) {

            if (prestamo.getUsuario().equals(usuario) && prestamo.estaActivo()) {

                if (!encontroLibros) {
                    System.out.println("El usuario tiene los siguientes libros prestados:\n");
                }

                prestamo.getLibro().mostrarInformacion();
                encontroLibros = true;
            }
        }

        if (!encontroLibros) {
            System.out.println("El usuario no tiene libros prestados");
        }
    }

    public GestionPrestamos buscarGestorPrestamos(int id) {

        User usuario = buscarUsuario(id);

        if (usuario instanceof GestionPrestamos gestor) {
            return gestor;
        }

        return null;
    }
}
