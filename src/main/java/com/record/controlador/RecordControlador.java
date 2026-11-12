package com.record.controlador;

import com.record.modelo.Jugador;
import com.record.modelo.RecordArchivo;
import com.record.vista.RecordVista;

import java.io.FileNotFoundException;
import java.io.IOException;


public class RecordControlador {

    private final RecordVista vista;
    private final RecordArchivo archivo;

    public RecordControlador(RecordVista vista, RecordArchivo archivo) {
        this.vista = vista;
        this.archivo = archivo;

        // Registro de eventos
        vista.getBtnGuardar().setOnAction(e -> guardar());
        vista.getBtnCargar().setOnAction(e -> cargar());
    }

    private void guardar() {
        String nombre = vista.getTxtNombre().getText().trim();
        String puntajeTexto = vista.getTxtPuntaje().getText().trim();

        // ---- Validación de entradas ----
        if (nombre.isEmpty()) {
            vista.mostrarError("Error: el nombre no puede estar vacío.");
            return;
        }
        if (puntajeTexto.isEmpty()) {
            vista.mostrarError("Error: debe ingresar un puntaje.");
            return;
        }

        int puntaje;
        try {
            puntaje = Integer.parseInt(puntajeTexto);
        } catch (NumberFormatException ex) {
            vista.mostrarError("Error: el puntaje debe ser un número entero (ej. 150).");
            return;
        }

        if (puntaje < 0) {
            vista.mostrarError("Error: el puntaje debe ser mayor o igual a cero.");
            return;
        }

        // ---- Guardado ----
        try {
            Jugador jugador = new Jugador(nombre, puntaje);
            archivo.guardar(jugador);
            vista.mostrarExito("Récord guardado: " + jugador);
        } catch (IOException ex) {
            vista.mostrarError("No se pudo guardar el archivo: " + ex.getMessage());
        }
    }

    private void cargar() {
        try {
            Jugador jugador = archivo.cargar();
            vista.getTxtNombre().setText(jugador.getNombre());
            vista.getTxtPuntaje().setText(String.valueOf(jugador.getPuntaje()));
            vista.mostrarExito("Récord cargado: " + jugador);
        } catch (FileNotFoundException ex) {
            vista.mostrarError("Aún no hay un récord guardado (" + archivo.getRutaArchivo() + " no existe).");
        } catch (IOException ex) {
            vista.mostrarError("No se pudo cargar el récord: " + ex.getMessage());
        }
    }
}
