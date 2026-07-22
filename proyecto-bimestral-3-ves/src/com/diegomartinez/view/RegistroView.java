package com.diegomartinez.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RegistroView extends BorderPane{
    private Button btnCerrarVentana;
    private Label lblTituloVentana;
    private HBox barraDeOpciones;

    private VBox cajaVertical;
    private GridPane formulario;

    private Label lblNombreCompleto;
    private TextField txtNombreCompleto;
    private Label lblNombreUsuario;
    private TextField txtNombreUsuario;
    private Label lblCorreo;
    private TextField txtCorreo;
    private Label lblClave;
    private PasswordField pwdClave;
    private Label lblConfirmarClave;
    private PasswordField pwdConfirmarClave;

    private Button btnRegistrar;
    private Hyperlink lnkVolverLogin;
    private Label lblMensaje;

    private final String RUTA_ESTILOS = "/com/diegomartinez/styles/";

    public RegistroView() {
        this.getStylesheets().add(RUTA_ESTILOS + "LoginStyles.css");
        this.setPadding(new Insets(15));
        this.setBorder(new Border(
                new BorderStroke(Paint.valueOf("#202087"),
                        BorderStrokeStyle.SOLID,
                        new CornerRadii(22),
                        new BorderWidths(10))
        ));

        this.setBackground(new Background(
                new BackgroundFill(Color.WHITE,
                        new CornerRadii(25),
                        Insets.EMPTY)
        ));

        barraDeOpciones = new HBox(25);
        btnCerrarVentana = new Button("X");
        lblTituloVentana = new Label("JAVAFX - REGISTRO DE USUARIO");
        barraDeOpciones.getChildren().addAll(btnCerrarVentana, lblTituloVentana);
        this.setTop(barraDeOpciones);

        cajaVertical = new VBox(12);
        cajaVertical.setAlignment(Pos.CENTER);

        formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);

        lblNombreCompleto = new Label("Nombre completo");
        txtNombreCompleto = new TextField();

        lblNombreUsuario = new Label("Nombre de usuario");
        txtNombreUsuario = new TextField();

        lblCorreo = new Label("Correo electrónico");
        txtCorreo = new TextField();

        lblClave = new Label("Contraseña");
        pwdClave = new PasswordField();

        lblConfirmarClave = new Label("Confirmar contraseña");
        pwdConfirmarClave = new PasswordField();

        formulario.add(lblNombreCompleto, 0, 0);
        formulario.add(txtNombreCompleto, 1, 0);

        formulario.add(lblNombreUsuario, 0, 1);
        formulario.add(txtNombreUsuario, 1, 1);

        formulario.add(lblCorreo, 0, 2);
        formulario.add(txtCorreo, 1, 2);

        formulario.add(lblClave, 0, 3);
        formulario.add(pwdClave, 1, 3);

        formulario.add(lblConfirmarClave, 0, 4);
        formulario.add(pwdConfirmarClave, 1, 4);

        btnRegistrar = new Button("Registrar");

        lnkVolverLogin = new Hyperlink("¿Ya tienes cuenta? Inicia sesión aquí");

        lblMensaje = new Label("");
        lblMensaje.setWrapText(true);
        lblMensaje.setMaxWidth(320);
        lblMensaje.setAlignment(Pos.CENTER);

        cajaVertical.getChildren().addAll(formulario, btnRegistrar, lblMensaje, lnkVolverLogin);
        this.setCenter(cajaVertical);
    }

    public Button getBtnCerrarVentana() {
        return btnCerrarVentana;
    }

    public Label getLblTituloVentana() {
        return lblTituloVentana;
    }

    public HBox getBarraDeOpciones() {
        return barraDeOpciones;
    }

    public VBox getCajaVertical() {
        return cajaVertical;
    }

    public GridPane getFormulario() {
        return formulario;
    }

    public TextField getTxtNombreCompleto() {
        return txtNombreCompleto;
    }

    public TextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public TextField getTxtCorreo() {
        return txtCorreo;
    }

    public PasswordField getPwdClave() {
        return pwdClave;
    }

    public PasswordField getPwdConfirmarClave() {
        return pwdConfirmarClave;
    }

    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public Hyperlink getLnkVolverLogin() {
        return lnkVolverLogin;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }
}
