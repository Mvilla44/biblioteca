package com.miguel.Excepciones;

public class UsuarioYaExisteException extends Exception {

    public UsuarioYaExisteException(int id) {
        super("Ya existe un usuario con el ID: " + id);
    }
}
