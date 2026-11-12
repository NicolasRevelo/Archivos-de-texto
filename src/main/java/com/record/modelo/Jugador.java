package com.record.modelo;


public class Jugador {

    private final String nombre;
    private final int puntaje;

    public Jugador(String nombre, int puntaje) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (puntaje < 0) {
            throw new IllegalArgumentException("El puntaje debe ser mayor o igual a cero.");
        }
        this.nombre = nombre.trim();
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public String toString() {
        return nombre + " - " + puntaje;
    }
}
