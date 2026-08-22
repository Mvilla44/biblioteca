package com.miguel;

import com.miguel.Excepciones.TipoUsuarioInvalidoException;

public class UserFactory {

    public static User crearUsuario(String nombre, int id, int tipo, Library biblioteca) throws TipoUsuarioInvalidoException {

        return switch (tipo) {

            case 1 -> new Student(nombre, id);

            case 2 -> new Admin(nombre, id, biblioteca);

            case 3 -> new Profesor(nombre, id);

            default -> throw new TipoUsuarioInvalidoException(tipo);
        };
    }
}
