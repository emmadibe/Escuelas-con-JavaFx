package Modelos;

import java.sql.*;

public class TablaIntermediaEstudianteXExamenModelo extends General
{
    public void crearregistroTablaIntermedia(Integer examenID, Integer estudianteID, Integer nota)
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO tablaIntermediaEstudianteXExamen(examenID, estudianteID, nota) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, examenID);
            preparedStatement.setInt(2, estudianteID);
            preparedStatement.setInt(3, nota);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tablaIntermediaEstudianteXExamen ("+
                    " id INT AUTO_INCREMENT PRIMARY KEY," +
                    "examenID INT," +
                    "nota INT," +
                    "estudianteID INT," +
                    "CONSTRAINT fk_id_estudiantes FOREIGN KEY (estudianteID) REFERENCES estudiantes(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT fk_id_examenes FOREIGN KEY (examenID) REFERENCES examenes(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ")";
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void actualizarNota(int estudianteID, int examenID, int nota)
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "UPDATE tablaintermediaestudiantexexamen SET nota = " + nota + " WHERE estudianteID = " + estudianteID + " AND examenID = " + examenID + ";";
            statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean existeTablaBDD()
    {
        return General.existeTabla("tablaintermediaestudiantexexamen");
    }
}
