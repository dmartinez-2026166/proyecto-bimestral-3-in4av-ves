package com.diegomartinez.conexiondb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDatabase {

    private static ConexionDatabase instanciaConexionDatabase;
    private String usuario = "IN4AV";
    private String clave = "dmartinez2026166";
    private String url = "jdbc:mysql://localhost:3306/proyecto_b3_2026166_in4av";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private Connection instanciaConnection;

    private ConexionDatabase() {
        try {
            Class.forName(DRIVER);
            instanciaConnection = DriverManager.getConnection(url, usuario, clave);
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
