package com.miguel;

public abstract class User {

    private String nombre;
    private final int id;

    public User(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public abstract String getTipoUsuario();

    public void mostrarInformacion() {
        System.out.println("Nombre: " + getNombre());
        System.out.println("Id: " + getId());
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User usuario = (User) o;

        return id == usuario.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
