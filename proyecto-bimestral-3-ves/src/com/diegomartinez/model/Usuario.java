package com.diegomartinez.model;

public class Usuario {
    private String nombreUsuario;
    private String password;
    private String nombreCompleto;
    
    public Usuario() {
    
    }
    
    public Usuario(String nombreUsuario, String password, String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        this.password = password;
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
}
