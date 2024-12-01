package com.ecodeup.jdbc;

import Modelos.General;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static Modelos.General.*;

public class Conexion extends General
{
        public static Connection getConnection()
        {
            Connection connection = null;
            Statement statement = null;
            try {
                Class.forName(getJdbcDriver());
                connection = DriverManager.getConnection(dbURL, username, password);
                String sql = "CREATE DATABASE IF NOT EXISTS EscuelasConJavaFx";
                statement = connection.createStatement();
                statement.executeUpdate(sql);
                System.out.println("Base de datos creada exitosamente.");
                General.setDbURL("jdbc:mysql://localhost:3306/EscuelasConJavaFx"); //Luego de crear la base de datos, seteo el valor de la variable dbURL para que contenga el nombre de la base de datos. Esto es necesario para realizar cualquier consulta, ya que necesitan saber a qué base de datos estoy haciendo referencia.
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if(connection != null) connection.close();
                    if(statement != null) statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            return connection;
        }

}
