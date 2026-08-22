package com.miguel.Excepciones;

public class TipoUsuarioInvalidoException extends Exception {

    public TipoUsuarioInvalidoException(int tipo) {
        super("El tipo de usuario " + tipo + " no es valido");
    }
}
