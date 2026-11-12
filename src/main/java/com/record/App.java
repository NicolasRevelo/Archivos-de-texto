package com.record;

import com.record.controlador.RecordControlador;
import com.record.modelo.RecordArchivo;
import com.record.vista.RecordVista;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto de entrada de la aplicación.
 * Aquí solo se "arma" el MVC: se crean el modelo, la vista y el controlador
 * y se conectan entre sí.
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Modelo: clase encargada de la persistencia en record.txt
        RecordArchivo archivo = new RecordArchivo("record.txt");

        // Vista: solo define la interfaz
        RecordVista vista = new RecordVista();

        // Controlador: recibe la vista y el modelo, y gestiona los eventos
        new RecordControlador(vista, archivo);

        stage.setTitle("Récord del jugador");
        stage.setScene(new Scene(vista.getRaiz(), 380, 230));
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
