package com.diegomartinez.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDatabase {

    private static ConexionDatabase instanciaConexionDatabase;
    private String usuario = "IN4AV";
    private String clave = "dmartinez2026166";
    private String url = "jdbc:mysql://localhost:3306/gestor_usuarios_in4av";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection instanciaConnection;

    //Ocultar el constructor vacío
    private ConexionDatabase() {
        try {
            Class.forName(DRIVER);
            //Crear la conexion a la DB
            instanciaConnection = DriverManager.getConnection(url, usuario, clave);
//            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException errorClassNotFound) {
            System.out.println("Error de clase no encontrada");
        } catch (SQLException errorSQL) {
            System.out.println("Error de SQL");
        } catch (Exception errorPadre) {
            System.out.println("Error top");
        }
    }

    public Connection getInstanciaConection() {
        return instanciaConnection;
    }

    public static ConexionDatabase getInstanciaConectionDatabase() {
        if (instanciaConexionDatabase == null) {
            instanciaConexionDatabase = new ConexionDatabase();
        }
        return instanciaConexionDatabase;
    }
    
}
