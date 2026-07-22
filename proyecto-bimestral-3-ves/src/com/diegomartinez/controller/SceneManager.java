package com.diegomartinez.controller;

import com.diegomartinez.view.BienvenidaView;
import com.diegomartinez.view.LoginView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class SceneManager {
     private Stage escenarioPrincipal;
    private Stage escenarioSecundario;
    private Scene escenaPrincipal;
    private static SceneManager instanciaSceneManager;

    private SceneManager() {
        try {

        } catch (NullPointerException objetoNulo) {
            JOptionPane.showMessageDialog(null, "Error de objeto nulo: SceneManager");
            objetoNulo.printStackTrace();
        } catch (Exception errorPadre) {
            JOptionPane.showMessageDialog(null, "Error padre: metodo SceneManager");
            errorPadre.printStackTrace();
        }
    }

    public void ventanaBienvenida(String nombreUsuario) {
        try {
            this.escenarioSecundario = new Stage();
            this.escenarioSecundario.initStyle(StageStyle.TRANSPARENT);
            this.escenarioSecundario.initModality(Modality.APPLICATION_MODAL);
            BienvenidaView bienvenida = BienvenidaView.getInstancaBienvenidaView(nombreUsuario);

            this.escenaPrincipal = new Scene(bienvenida, 300, 150);
            this.escenaPrincipal.setFill(Color.TRANSPARENT); 
            
            this.escenaPrincipal.getStylesheets().add(getClass().getResource("/com/diegomartinez/styles/LoginStyles.css").toExternalForm());

            this.escenarioSecundario.setScene(escenaPrincipal);
            this.escenarioSecundario.sizeToScene();
            this.escenarioSecundario.showAndWait();

        } catch (NullPointerException objetoNulo) {
            JOptionPane.showMessageDialog(null, "Error de objeto nulo: Ventana Bienvenida");
            objetoNulo.printStackTrace();
        } catch (Exception errorPadre) {
            JOptionPane.showMessageDialog(null, "Error padre: metodo Ventana Bienvenida");
            errorPadre.printStackTrace();
        }
    }

    public void ventanaLogin() {
        try {
            this.escenarioPrincipal.initStyle(StageStyle.TRANSPARENT);
            LoginView login = LoginView.getInstanciaLoginView();
            cambiarEscena(login, 450, 500);
            this.escenaPrincipal.setFill(Color.TRANSPARENT);
            new LoginController(login);

        } catch (NullPointerException objetoNulo) {
            JOptionPane.showMessageDialog(null, "Error de objeto nulo: Ventana Login");
            objetoNulo.printStackTrace();
        } catch (Exception errorPadre) {
            JOptionPane.showMessageDialog(null, "Error padre: metodo Ventana Login");
            errorPadre.printStackTrace();
        }
    }

    public void cambiarEscena(Pane panel, int ancho, int alto) {
        try {
            escenaPrincipal = new Scene(panel, ancho, alto);
            this.escenarioPrincipal.setScene(escenaPrincipal);
            this.escenarioPrincipal.sizeToScene();
            this.escenarioPrincipal.show();
        } catch (NullPointerException objetoNulo) {
            JOptionPane.showMessageDialog(null, "Error de objeto nulo: cambiar escena");
            objetoNulo.printStackTrace();
        } catch (Exception errorPadre) {
            JOptionPane.showMessageDialog(null, "Error padre: metodo cambiar escena");
            errorPadre.printStackTrace();
        }
    }

    public static SceneManager getInstanciaSceneManager() {
        if (instanciaSceneManager == null) {
            instanciaSceneManager = new SceneManager();
        }
        return instanciaSceneManager;
    }

    public static void setInstanciaSceneManager(SceneManager instanciaSceneManager) {
        SceneManager.instanciaSceneManager = instanciaSceneManager;
    }

    public Stage getEscenarioPrincipal() {
        return escenarioPrincipal;
    }

    public void setEscenarioPrincipal(Stage escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public Stage getEscenarioSecundario() {
        return escenarioSecundario;
    }

    public void setEscenarioSecundario(Stage escenarioSecundario) {
        this.escenarioSecundario = escenarioSecundario;
    }

    public Scene getEscenaPrincipal() {
        return escenaPrincipal;
    }

    public void setEscenaPrincipal(Scene escenaPrincipal) {
        this.escenaPrincipal = escenaPrincipal;
    }
}
