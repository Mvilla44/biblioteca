package com.miguel;

import com.miguel.Excepciones.*;

public interface OperacionesAdministrativas {

    void agregarLibro(Book libro)
            throws LibroYaExisteException;

    void eliminarLibro(int id)
            throws LibroNoEncontradoException, LibroPrestadoException;

    void agregarUsuario(User usuario)
            throws UsuarioYaExisteException;

    void eliminarUsuario(int id)
            throws UsuarioNoEncontradoException;
}
