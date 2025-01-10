package Modelos;

import ClasesPrincipales.TablaIntermediaAsistenciaEstudiantesXClases;
import kotlin.jvm.internal.PackageReference;

import java.sql.*;

public class TablaIntermediaAsistenciaEstudiantesXClasesModelo extends General
{
    public static void crearTabla()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS tablaIntermediaAsistenciaEstudiantesXClases (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "claseID INT," +
                "estudianteID INT," +
                "asistio TINYINT," +
                "CONSTRAINT fk_id_estudiantes_Asistencia FOREIGN KEY (estudianteID) REFERENCES estudiantes(id) ON DELETE CASCADE ON UPDATE CASCADE," +
                "CONSTRAINT fk_id_clases_asistencia FOREIGN KEY (claseID) REFERENCES clases(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                ");";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void agregarRegistroBDD(TablaIntermediaAsistenciaEstudiantesXClases TIEXC)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO tablaIntermediaAsistenciaEstudiantesXClases" +        "(estudianteID, claseID, asistio)" +
                    "VALUES (?, ?, ?);";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, TIEXC.getEstudianteID());
            preparedStatement.setInt(2, TIEXC.getClaseID());
            preparedStatement.setByte(3, (byte) TIEXC.getAsistio()); //El tipo de dato tinyInt se corresponde con el byte en JAVA.
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(preparedStatement != null) preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public int traerIDUltimoRegistro()
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT id FROM examenes ORDER BY id DESC LIMIT 1";
        int lastID = -1;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                lastID = resultSet.getInt("id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(preparedStatement != null) preparedStatement.close();
                if(resultSet != null) resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lastID;
    }
}
