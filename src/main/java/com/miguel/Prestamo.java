package com.miguel;

import java.time.LocalDate;

public class Prestamo {

    private final Book libro;
    private final User usuario;
    private final LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(Book libro, User usuario) {
        this.libro = libro;
        this.usuario = usuario;
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
        fechaDevolucion = LocalDate.now();
    }
}
