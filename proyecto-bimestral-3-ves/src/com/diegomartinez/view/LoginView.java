package com.diegomartinez.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class LoginView extends BorderPane{
        private static LoginView instanciaLoginView;
    private Button btnCerrarVentana;
    private Label lblTituloVentana;
    private HBox barraDeOpciones;

    private VBox cajaVertical;
    private Label lblNombreUsuario;
    private TextField txtNombreUsuario;
    private Label lblClave;
    private PasswordField pwdClave;

    private GridPane formulario;
    private ImageView imgLogoLogin;
    private Button btnIniciarSesion;
    private Hyperlink lnkRegistro;
    private Label lblMensaje;
    
    private String RUTA_ESTILOS = "/com/diegomartinez/styles/";
    
    private LoginView() {
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
        lblTituloVentana = new Label("JAVAFX - SIMULACION LOGIN");
        
        barraDeOpciones.getChildren().addAll(btnCerrarVentana, lblTituloVentana);
        this.setTop(barraDeOpciones);
        
        cajaVertical = new VBox();
        formulario = new GridPane();
        
        lblNombreUsuario = new Label("ingrese su nombre usuario");
        txtNombreUsuario = new TextField();
        lblClave = new Label("Ingrese su contraseña");
        pwdClave = new PasswordField();
        
        formulario.add(lblNombreUsuario,0 ,0);
        formulario.add(txtNombreUsuario,1, 0);
        
        formulario.add(lblClave,0 , 1);
        formulario.add(pwdClave, 1, 1);
        
        btnIniciarSesion = new Button("Iniciar Sesion");

        lblMensaje = new Label("");
        lblMensaje.setWrapText(true);
        lblMensaje.setMaxWidth(320);
        lblMensaje.setAlignment(Pos.CENTER);

        lnkRegistro = new Hyperlink("¿No tienes cuenta? Regístrate aquí");

        cajaVertical.setAlignment(Pos.CENTER);
        
        cajaVertical.getChildren().addAll(formulario, btnIniciarSesion, lblMensaje, lnkRegistro);
        this.setCenter(cajaVertical);
    }

    public VBox getCajaVertical() {
        return cajaVertical;
    }

    public void setCajaVertical(VBox cajaVertical) {
        this.cajaVertical = cajaVertical;
    }

    public Label getLblNombreUsuario() {
        return lblNombreUsuario;
    }

    public void setLblNombreUsuario(Label lblNombreUsuario) {
        this.lblNombreUsuario = lblNombreUsuario;
    }

    public TextField getTxtNombreUsuario() {
        return txtNombreUsuario;
    }

    public void setTxtNombreUsuario(TextField txtNombreUsuario) {
        this.txtNombreUsuario = txtNombreUsuario;
    }

    public Label getLblClave() {
        return lblClave;
    }

    public void setLblClave(Label lblClave) {
        this.lblClave = lblClave;
    }

    public PasswordField getPwdClave() {
        return pwdClave;
    }

    public void setPwdClave(PasswordField pwdClave) {
        this.pwdClave = pwdClave;
    }

    public GridPane getFormulario() {
        return formulario;
    }

    public void setFormulario(GridPane formulario) {
        this.formulario = formulario;
    }

    public ImageView getImgLogoLogin() {
        return imgLogoLogin;
    }

    public void setImgLogoLogin(ImageView imgLogoLogin) {
        this.imgLogoLogin = imgLogoLogin;
    }

    public Button getBtnIniciarSesion() {
        return btnIniciarSesion;
    }

    public void setBtnIniciarSesion(Button btnIniciarSesion) {
        this.btnIniciarSesion = btnIniciarSesion;
    }

    
    public Button getBtnCerrarVentana() {
        return btnCerrarVentana;
    }

    public void setBtnCerrarVentana(Button btnCerrarVentana) {
        this.btnCerrarVentana = btnCerrarVentana;
    }

    public Label getLblTituloVentana() {
        return lblTituloVentana;
    }

    public void setLblTituloVentana(Label lblTituloVentana) {
        this.lblTituloVentana = lblTituloVentana;
    }

    public HBox getBarraDeOpciones() {
        return barraDeOpciones;
    }

    public void setBarraDeOpciones(HBox barraDeOpciones) {
        this.barraDeOpciones = barraDeOpciones;
    }

    public Hyperlink getLnkRegistro() {
        return lnkRegistro;
    }

    public void setLnkRegistro(Hyperlink lnkRegistro) {
        this.lnkRegistro = lnkRegistro;
    }

    public Label getLblMensaje() {
        return lblMensaje;
    }

    public void setLblMensaje(Label lblMensaje) {
        this.lblMensaje = lblMensaje;
    }

    public static LoginView getInstanciaLoginView() {
        if (instanciaLoginView == null) {
            instanciaLoginView = new LoginView();
        }
        return instanciaLoginView;
    }

    public static void setInstanciaLoginView(LoginView instanciaLoginView) {
        LoginView.instanciaLoginView = instanciaLoginView;
    }
}
