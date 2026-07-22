package com.diegomartinez.controller;

import com.diegomartinez.exceptions.ExcepcionesPersonalizadas;
import com.diegomartinez.model.Usuario;
import com.diegomartinez.repository.UsuarioDAO;
import com.diegomartinez.view.RegistroView;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;

public class RegistroController {
    private static final Pattern PATRON_CORREO = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-zA-Z]{2,}$");

    private final RegistroView REGISTRO_VIEW;
    private final UsuarioDAO usuarioDAO;
    private final ExcepcionesPersonalizadas validador;
    private double ejeX = 0;
    private double ejeY = 0;

    public RegistroController(RegistroView registroView) {
        this.REGISTRO_VIEW = registroView;
        this.usuarioDAO = new UsuarioDAO();
        this.validador = new ExcepcionesPersonalizadas();
        construirAcciones();
    }

    private void limpiarEstilosError() {
        REGISTRO_VIEW.getTxtNombreCompleto().getStyleClass().removeAll("empty", "error");
        REGISTRO_VIEW.getTxtNombreUsuario().getStyleClass().removeAll("empty", "error");
        REGISTRO_VIEW.getTxtCorreo().getStyleClass().removeAll("empty", "error");
        REGISTRO_VIEW.getPwdClave().getStyleClass().removeAll("empty", "error");
        REGISTRO_VIEW.getPwdConfirmarClave().getStyleClass().removeAll("empty", "error");
    }

    private void marcarCampo(TextInputControl campo, String estilo) {
        if (!campo.getStyleClass().contains(estilo)) {
            campo.getStyleClass().add(estilo);
        }
    }

    private void mostrarMensaje(String mensaje, boolean esError) {
        REGISTRO_VIEW.getLblMensaje().getStyleClass().removeAll("mensaje-error", "mensaje-exito");
        REGISTRO_VIEW.getLblMensaje().setText(mensaje);
        if (!mensaje.isEmpty()) {
            REGISTRO_VIEW.getLblMensaje().getStyleClass().add(esError ? "mensaje-error" : "mensaje-exito");
        }
    }

    public void registrarUsuario() {
        limpiarEstilosError();

        String nombreCompleto = REGISTRO_VIEW.getTxtNombreCompleto().getText().trim();
        String nombreUsuario = REGISTRO_VIEW.getTxtNombreUsuario().getText().trim();
        String correo = REGISTRO_VIEW.getTxtCorreo().getText().trim();
        String clave = REGISTRO_VIEW.getPwdClave().getText().trim();
        String confirmarClave = REGISTRO_VIEW.getPwdConfirmarClave().getText().trim();

        if (nombreCompleto.isEmpty()) {
            marcarCampo(REGISTRO_VIEW.getTxtNombreCompleto(), "empty");
            mostrarMensaje("No dejes vacío el nombre completo", true);
            return;
        }
        if (nombreUsuario.isEmpty()) {
            marcarCampo(REGISTRO_VIEW.getTxtNombreUsuario(), "empty");
            mostrarMensaje("No dejes vacío el nombre de usuario", true);
            return;
        }
        if (correo.isEmpty()) {
            marcarCampo(REGISTRO_VIEW.getTxtCorreo(), "empty");
            mostrarMensaje("No dejes vacío el correo electrónico", true);
            return;
        }
        if (clave.isEmpty() || confirmarClave.isEmpty()) {
            marcarCampo(REGISTRO_VIEW.getPwdClave(), "empty");
            marcarCampo(REGISTRO_VIEW.getPwdConfirmarClave(), "empty");
            mostrarMensaje("No dejes vacía la contraseña", true);
            return;
        }

        if (!PATRON_CORREO.matcher(correo).matches()) {
            marcarCampo(REGISTRO_VIEW.getTxtCorreo(), "error");
            mostrarMensaje("Ingresa un correo electrónico válido", true);
            return;
        }
        
        if (!clave.equals(confirmarClave)) {
            marcarCampo(REGISTRO_VIEW.getPwdClave(), "error");
            marcarCampo(REGISTRO_VIEW.getPwdConfirmarClave(), "error");
            mostrarMensaje("Las contraseñas no coinciden", true);
            return;
        }

        try {
            validador.validarLongitudTexto(nombreCompleto, 100);
            validador.validarLongitudTexto(nombreUsuario, 50);
            validador.validarLongitudTexto(correo, 100);
        } catch (ExcepcionesPersonalizadas errorLongitud) {
            mostrarMensaje(errorLongitud.getMessage(), true);
            return;
        }

        try {
            if (usuarioDAO.existeNombreUsuario(nombreUsuario)) {
                marcarCampo(REGISTRO_VIEW.getTxtNombreUsuario(), "error");
                mostrarMensaje("Ese nombre de usuario ya está registrado", true);
                return;
            }
            if (usuarioDAO.existeCorreo(correo)) {
                marcarCampo(REGISTRO_VIEW.getTxtCorreo(), "error");
                mostrarMensaje("Ese correo ya está registrado", true);
                return;
            }

            Usuario nuevoUsuario = new Usuario(nombreCompleto, nombreUsuario, correo, clave);
            usuarioDAO.registrarUsuario(nuevoUsuario);

            mostrarMensaje("¡Registro exitoso! Ya puedes iniciar sesión", false);
            limpiarFormulario();

        } catch (SQLIntegrityConstraintViolationException errorDuplicado) {
            mostrarMensaje("El usuario o correo ya existen en la base de datos", true);
        } catch (SQLException errorSQL) {
            mostrarMensaje("Error de conexión con la base de datos", true);
            errorSQL.printStackTrace();
        }
    }

    private void limpiarFormulario() {
        REGISTRO_VIEW.getTxtNombreCompleto().clear();
        REGISTRO_VIEW.getTxtNombreUsuario().clear();
        REGISTRO_VIEW.getTxtCorreo().clear();
        REGISTRO_VIEW.getPwdClave().clear();
        REGISTRO_VIEW.getPwdConfirmarClave().clear();
    }

    public void construirAcciones() {
        REGISTRO_VIEW.getBtnRegistrar().setOnMouseClicked(
                (evento) -> registrarUsuario()
        );

        REGISTRO_VIEW.getLnkVolverLogin().setOnAction(
                (evento) -> SceneManager.getInstanciaSceneManager().ventanaLogin()
        );

        REGISTRO_VIEW.getBtnCerrarVentana().setOnMouseClicked(
                (evento) -> System.exit(0)
        );

        REGISTRO_VIEW.setOnMouseClicked(
                (evento) -> {
                    ejeX = evento.getSceneX();
                    ejeY = evento.getSceneY();
                }
        );

        REGISTRO_VIEW.setOnMouseDragged(
                (evento) -> {
                    Stage escenarioPrincipal = SceneManager.getInstanciaSceneManager().getEscenarioPrincipal();
                    double ejeXDesplazamientoVentana = evento.getScreenX();
                    double ejeYDesplazamientoVentana = evento.getScreenY();

                    escenarioPrincipal.setX(ejeXDesplazamientoVentana - ejeX);
                    escenarioPrincipal.setY(ejeYDesplazamientoVentana - ejeY);
                }
        );
    }
    
}
