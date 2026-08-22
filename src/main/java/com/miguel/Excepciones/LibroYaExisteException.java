package com.miguel.Excepciones;

public class LibroYaExisteException extends Exception {

    public LibroYaExisteException(int id) {
        super("Ya existe un libro con el ID: " + id);
    }
}
