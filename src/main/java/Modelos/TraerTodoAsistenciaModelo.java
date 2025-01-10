package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TablaIntermediaAsistenciaEstudiantesXClases;
import ClasesPrincipales.TraerTodoAsistencia;
import javafx.scene.paint.Color;

import java.sql.*;
import java.time.LocalDate;

public class TraerTodoAsistenciaModelo extends General
{
    public static ArrayListGenerico<TraerTodoAsistencia> traerTodo (int cursoID)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<TraerTodoAsistencia> arrayListTraerTodoAsistencia = new ArrayListGenerico<>();
        String sql = "SELECT " +
                    "CONCAT(e.nombre, ' ', e.apellido AS nombreYapellido," +
                    "c.fecha," +
                    "ti.asistio" +
                    "FROM estudiantes e " +
                    "INNER JOIN" +
                    "clases c ON c.estudianteID = e.id" +
                    "INNER JOIN " +
                    "tablaIntermediaAsistenciaEstudiantesXClases ti ON ti.estudianteID = e.id" +
                    "WHERE ti.cursoID = ?";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cursoID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombreYApellido = resultSet.getString("nombreYapellido");
                LocalDate fecha = resultSet.getDate("fecha").toLocalDate(); //Transformo la fecha del formato sql al formato LocalDate.
                boolean asistio = ((resultSet.getInt("asistio") == 1) ? true : false);
                TraerTodoAsistencia traerTodoAsistencia = new TraerTodoAsistencia(cursoID, nombreYApellido, fecha, asistio);
                arrayListTraerTodoAsistencia.agregar(traerTodoAsistencia); //Y, así, voy agregando cada registro a mi arrayList.
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
                if(preparedStatement != null) preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return arrayListTraerTodoAsistencia;
    }


}
