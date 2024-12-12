package Modelos;

import java.sql.*;
import java.util.Vector;

public class MostrarTodoModelo extends General
{
    public static ResultSet getData(Integer cursoID) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT \n" +
                "  e.nombre, \n" +
                "  ex.nombre AS examen, \n" +
                "  se.nota\n" +
                "FROM \n" +
                "  estudiantes e\n" +
                "  INNER JOIN tablaintermediaestudiantexexamen se ON e.id = se.estudianteID\n" +
                "  INNER JOIN examenes ex ON se.examenID = ex.id\n" +
                "  INNER JOIN tablaintermediaestudiantesxcursos tic ON tic.estudianteID = e.id\n" +
                "WHERE \n" +
                "  tic.cursoID = ? \n" +
                "ORDER BY \n" +
                "  e.nombre, ex.nombre;";
        try{
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cursoID);
            resultSet = preparedStatement.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

}
