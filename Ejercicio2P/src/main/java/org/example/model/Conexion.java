package org.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Conexion instancia ;
    private Connection conexion;

    private final static String URL = "jdbc:mysql://localhost:3306/Libreria";
    private final static String USER = "root";
    private final static String PASSWORD = "";


    private Conexion (){}

    public synchronized static Conexion getInstancia(){
        if(instancia==null){
            instancia=new Conexion();
        }
        return instancia;
    }


    public synchronized Connection getConexion() throws SQLException {
        if(conexion==null || conexion.isClosed()){
            conexion= DriverManager.getConnection(URL,USER,PASSWORD);
        }

        return conexion;
    }

    public void cerrar() throws SQLException {
        if(conexion!=null && !conexion.isClosed()){
            conexion.close();
        }
    }
}
