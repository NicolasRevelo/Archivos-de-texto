package com.record.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


public class RecordVista {

    private final VBox raiz = new VBox(15);
    private final TextField txtNombre = new TextField();
    private final TextField txtPuntaje = new TextField();
    private final Button btnGuardar = new Button("Guardar");
    private final Button btnCargar = new Button("Cargar");
    private final Label lblMensaje = new Label();

    public RecordVista() {
        txtNombre.setPromptText("Nombre del jugador");
        txtPuntaje.setPromptText("Entero >= 0");

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.add(new Label("Nombre:"), 0, 0);
        formulario.add(txtNombre, 1, 0);
        formulario.add(new Label("Puntaje:"), 0, 1);
        formulario.add(txtPuntaje, 1, 1);

        btnGuardar.setPrefWidth(100);
        btnCargar.setPrefWidth(100);
        HBox botones = new HBox(10, btnGuardar, btnCargar);
        botones.setAlignment(Pos.CENTER);

        lblMensaje.setWrapText(true);
        lblMensaje.setMaxWidth(340);

        raiz.setPadding(new Insets(20));
        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(formulario, botones, lblMensaje);
    }

    /** Muestra un mensaje de éxito (verde). */
    public void mostrarExito(String mensaje) {
        lblMensaje.setTextFill(Color.GREEN);
        lblMensaje.setText(mensaje);
    }

    /** Muestra un mensaje de error (rojo). */
    public void mostrarError(String mensaje) {
        lblMensaje.setTextFill(Color.RED);
        lblMensaje.setText(mensaje);
    }

    public VBox getRaiz() {
        return raiz;
    }

    public TextField getTxtNombre() {
        return txtNombre;
    }

    public TextField getTxtPuntaje() {
        return txtPuntaje;
    }

    public Button getBtnGuardar() {
        return btnGuardar;
    }

    public Button getBtnCargar() {
        return btnCargar;
    }
}
