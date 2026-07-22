package com.diegomartinez.controller;

import com.diegomartinez.model.Usuario;
import com.diegomartinez.view.LoginView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController {
    private final LoginView LOGIN_VIEW;
    private double ejeX = 0;
    private double ejeY = 0;

    public LoginController(LoginView loginView) {
        this.LOGIN_VIEW = loginView;
        construirAcciones();
    }

    private Stage escenarioPrincipal = SceneManager.getInstanciaSceneManager().getEscenarioPrincipal();

    public void iniciarSesion() {
        String nombreUsuario = this.LOGIN_VIEW.getTxtNombreUsuario().getText().trim();
        String clave = this.LOGIN_VIEW.getPwdClave().getText().trim();

        if (nombreUsuario.isEmpty()) {
            this.LOGIN_VIEW.getTxtNombreUsuario().getStyleClass().add("empty");
            JOptionPane.showMessageDialog(null, "NO DEJES VACIO EL NOMBRE DE USUARIO");
        } else if (clave.isEmpty()){
            this.LOGIN_VIEW.getTxtNombreUsuario().getStyleClass().remove("empty");
            this.LOGIN_VIEW.getPwdClave().getStyleClass().add("empty");
            JOptionPane.showMessageDialog(null, "NO DEJES VACIA LA CONTRASEÑA");
        } else {
            this.LOGIN_VIEW.getPwdClave().getStyleClass().remove("empty");
//            Usuario usuario = authSistema.login(nombreUsuario, clave);
//            
//            if (usuario == null) {
//                JOptionPane.showMessageDialog(null, "VERIFICA TUS CREDENCIALES");
//            } else {
//                if (escenarioPrincipal != null) {
//                    escenarioPrincipal.close();
//                }
//                
//                String nombreAMostrar = usuario.getNombreCompleto();
//                SceneManager.getInstanciaSceneManager().ventanaBienvenida(nombreAMostrar);
//            }
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
