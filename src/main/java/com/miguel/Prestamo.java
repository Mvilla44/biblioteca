package com.miguel;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {

    private final Book libro;
    private final User usuario;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Book libro, User usuario) {

        this.libro = Objects.requireNonNull(
                libro,
                "El libro no puede ser null"
        );

        this.usuario = Objects.requireNonNull(
                usuario,
                "El usuario no puede ser null"
        );

        this.fechaPrestamo = LocalDate.now();
    }

    public Book getLibro() {
        return libro;
    }

    public User getUsuario() {
        return usuario;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public void devolver() {

        if (!estaActivo()) {
            throw new IllegalStateException(
                    "El préstamo ya fue devuelto"
            );
        }

        fechaDevolucion = LocalDate.now();
    }
}
