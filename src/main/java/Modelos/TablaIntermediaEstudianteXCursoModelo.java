package Modelos;

import Interfaces.ModelosTablasIntermedias;

import java.sql.*;

public class TablaIntermediaEstudianteXCursoModelo extends General implements ModelosTablasIntermedias<Integer>
{
    @Override
    public void crearregistroTablaIntermedia(Integer estudianteID, Integer cursoID)
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO tablaIntermediaEstudiantesXCursos(cursoID, estudianteID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cursoID);
            preparedStatement.setInt(2, estudianteID);
            preparedStatement.executeUpdate();
            System.out.println("registro de la tabla intermedia creada con éxito");
        }catch (SQLException e){
            System.out.println("No se pudo crear el registro de la tabla intermedia");
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void crearTablaIntermedia()
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS tablaIntermediaEstudiantesXCursos ("+
                    " id INT AUTO_INCREMENT PRIMARY KEY," +
                    "cursoID INT," +
                    "estudianteID INT," +
                    "CONSTRAINT fk_id_curso FOREIGN KEY (cursoID) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "CONSTRAINT fk_id_estudiante FOREIGN KEY (estudianteID) REFERENCES estudiantes(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Tabla intermedia de Cursos X Alumnos creada con éxito");
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
}
