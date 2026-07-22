package com.diegomartinez.repository;

import com.diegomartinez.conexiondb.ConexionDatabase;
import com.diegomartinez.model.Usuario;
import com.diegomartinez.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    private Connection obtenerConexion() {
        return ConexionDatabase.getInstanciaConectionDatabase().getInstanciaConection();
    }

    public void registrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario (nombre_completo, usuario, correo, clave) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql)) {
            statement.setString(1, usuario.getNombreCompleto());
            statement.setString(2, usuario.getNombreUsuario());
            statement.setString(3, usuario.getCorreo());
            statement.setString(4, PasswordUtil.hashear(usuario.getPassword()));
            statement.executeUpdate();
        }
    }

    
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE usuario = ?";

        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultado = statement.executeQuery()) {
                return resultado.next();
            }
        }
    }

    public boolean existeCorreo(String correo) throws SQLException {
        String sql = "SELECT id_usuario FROM usuario WHERE correo = ?";

        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql)) {
            statement.setString(1, correo);
            try (ResultSet resultado = statement.executeQuery()) {
                return resultado.next();
            }
        }
    }
    
    public Usuario validarCredenciales(String nombreUsuario, String claveTextoPlano) throws SQLException {
        String sql = "SELECT id_usuario, nombre_completo, usuario, correo, clave FROM usuario WHERE usuario = ?";

        try (PreparedStatement statement = obtenerConexion().prepareStatement(sql)) {
            statement.setString(1, nombreUsuario);
            try (ResultSet resultado = statement.executeQuery()) {
                if (!resultado.next()) {
                    return null;
                }

                String claveHasheada = resultado.getString("clave");
                if (!PasswordUtil.coincide(claveTextoPlano, claveHasheada)) {
                    return null;
                }

                return new Usuario(
                        resultado.getInt("id_usuario"),
                        resultado.getString("nombre_completo"),
                        resultado.getString("usuario"),
                        resultado.getString("correo"),
                        claveHasheada
                );
            }
        }
    }
}
