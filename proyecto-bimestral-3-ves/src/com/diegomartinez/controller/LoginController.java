package com.diegomartinez.controller;

import com.diegomartinez.model.Usuario;
import com.diegomartinez.repository.UsuarioDAO;
import com.diegomartinez.view.LoginView;
import java.sql.SQLException;
import javafx.stage.Stage;

public class LoginController {
    private final LoginView LOGIN_VIEW;
    private final UsuarioDAO usuarioDAO;
    private double ejeX = 0;
    private double ejeY = 0;

    public LoginController(LoginView loginView) {
        this.LOGIN_VIEW = loginView;
        this.usuarioDAO = new UsuarioDAO();
        construirAcciones();
    }

    private Stage escenarioPrincipal = SceneManager.getInstanciaSceneManager().getEscenarioPrincipal();

    public void iniciarSesion() {
        String nombreUsuario = this.LOGIN_VIEW.getTxtNombreUsuario().getText().trim();
        String clave = this.LOGIN_VIEW.getPwdClave().getText().trim();

        this.LOGIN_VIEW.getTxtNombreUsuario().getStyleClass().remove("empty");
        this.LOGIN_VIEW.getPwdClave().getStyleClass().remove("empty");

        if (nombreUsuario.isEmpty()) {
            this.LOGIN_VIEW.getTxtNombreUsuario().getStyleClass().add("empty");
            mostrarMensaje("No dejes vacío el nombre de usuario", true);
        } else if (clave.isEmpty()) {
            this.LOGIN_VIEW.getPwdClave().getStyleClass().add("empty");
            mostrarMensaje("No dejes vacía la contraseña", true);
        } else {
            try {
                Usuario usuario = usuarioDAO.validarCredenciales(nombreUsuario, clave);

                if (usuario == null) {
                    mostrarMensaje("Verifica tus credenciales", true);
                } else {
                    mostrarMensaje("", false);
                    if (escenarioPrincipal != null) {
                        escenarioPrincipal.close();
                    }

                    String nombreAMostrar = usuario.getNombreCompleto();
                    SceneManager.getInstanciaSceneManager().ventanaBienvenida(nombreAMostrar);
                }
            } catch (SQLException errorSQL) {
                mostrarMensaje("Error de conexión con la base de datos", true);
                errorSQL.printStackTrace();
            }
        }
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        this.LOGIN_VIEW.getLblMensaje().getStyleClass().removeAll("mensaje-error", "mensaje-exito");
        this.LOGIN_VIEW.getLblMensaje().setText(mensaje);
        if (!mensaje.isEmpty()) {
            this.LOGIN_VIEW.getLblMensaje().getStyleClass().add(esError ? "mensaje-error" : "mensaje-exito");
        }
    }

    public void construirAcciones() {
        this.LOGIN_VIEW.getBtnIniciarSesion().setOnMouseClicked(
                (evento) -> {
                    iniciarSesion();
                }
        );

        this.LOGIN_VIEW.getBtnCerrarVentana().setOnMouseClicked(
                (evento) -> {
                    System.exit(0);
                }
        );

        this.LOGIN_VIEW.getLnkRegistro().setOnAction(
                (evento) -> {
                    SceneManager.getInstanciaSceneManager().ventanaRegistro();
                }
        );

        this.LOGIN_VIEW.setOnMouseClicked(
                (evento) -> {
                    ejeX = evento.getSceneX();
                    ejeY = evento.getSceneY();
                }
        );

        this.LOGIN_VIEW.setOnMouseDragged(
                (evento) -> {
                    double ejeXDesplazamientoVentana = evento.getScreenX();
                    double ejeYDesplazamientoVentana = evento.getScreenY();

                    escenarioPrincipal.setX(ejeXDesplazamientoVentana - ejeX);
                    escenarioPrincipal.setY(ejeYDesplazamientoVentana - ejeY);
                }
        );
    }
    
}
