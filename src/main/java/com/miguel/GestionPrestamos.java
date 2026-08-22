package com.miguel;

import com.miguel.Excepciones.LibroNoEncontradoException;
import com.miguel.Excepciones.LibroPrestadoException;

public interface GestionPrestamos {

    void prestarLibro(Library biblioteca, String titulo)
            throws LibroNoEncontradoException, LibroPrestadoException;

    void devolverLibro(Library biblioteca, String titulo)
            throws LibroNoEncontradoException;
}
