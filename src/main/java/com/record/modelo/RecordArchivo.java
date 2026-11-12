package com.record.modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class RecordArchivo {

    private final String rutaArchivo;

    public RecordArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void guardar(Jugador jugador) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(rutaArchivo, StandardCharsets.UTF_8, false))) {
            escritor.write(jugador.getNombre());
            escritor.newLine();
            escritor.write(String.valueOf(jugador.getPuntaje()));
            escritor.newLine();
        }
    }

    /**
     * Lee el jugador guardado en el archivo.
     *
     * @throws FileNotFoundException si todavía no existe record.txt
     * @throws IOException           si hay un error de lectura o el contenido es inválido
     */
    public Jugador cargar() throws IOException {
        try (BufferedReader lector = new BufferedReader(
                new FileReader(rutaArchivo, StandardCharsets.UTF_8))) {

            String nombre = lector.readLine();
            String puntajeTexto = lector.readLine();

            if (nombre == null || puntajeTexto == null) {
                throw new IOException("El archivo " + rutaArchivo + " está vacío o incompleto.");
            }

            try {
                int puntaje = Integer.parseInt(puntajeTexto.trim());
                return new Jugador(nombre, puntaje);
            } catch (NumberFormatException e) {
                throw new IOException("El puntaje guardado en el archivo no es un número válido.", e);
            } catch (IllegalArgumentException e) {
                throw new IOException("Datos inválidos en el archivo: " + e.getMessage(), e);
            }
        }
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }
}
