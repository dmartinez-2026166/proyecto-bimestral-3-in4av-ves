package com.diegomartinez.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BienvenidaView extends VBox {
    private static BienvenidaView instanciaBienvenidaView;
    private Label lblMensaje;
    private Button btnAceptar;
    private String nombreUsuarioInicial = "";

    public BienvenidaView(String nombreUsuario) {
        this.nombreUsuarioInicial = nombreUsuario;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        
        this.getStyleClass().add("bienvenida-ventana");
        
        lblMensaje = new Label("Bienvenido, " + nombreUsuarioInicial);
        lblMensaje.getStyleClass().add("bienvenida-mensaje");
        
        btnAceptar = new Button("Aceptar");
        btnAceptar.setPrefWidth(120);
        btnAceptar.getStyleClass().add("bienvenida-boton");
        
        btnAceptar.setOnAction(evento -> {
            Stage stage = (Stage) this.getScene().getWindow();
            if (stage != null) {
                stage.close();
            }
        });
        
        this.getChildren().addAll(lblMensaje, btnAceptar);
    }

    public void setNombreUsuario(String nombre) {
        this.nombreUsuarioInicial = nombre;
        if (lblMensaje != null) {
            lblMensaje.setText("Bienvenido, " + nombre);
        }
    }

    public static BienvenidaView getInstancaBienvenidaView(String nombreUsuario) {
        if (instanciaBienvenidaView == null) {
            instanciaBienvenidaView = new BienvenidaView(nombreUsuario);
        } else {
            instanciaBienvenidaView.setNombreUsuario(nombreUsuario);
        }
        return instanciaBienvenidaView;
    }

    public static void setInstanciBienvenidaView(BienvenidaView instanciaBienvenidaView) {
        BienvenidaView.instanciaBienvenidaView = instanciaBienvenidaView;
    }
}
