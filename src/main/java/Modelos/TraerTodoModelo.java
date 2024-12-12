package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TraerTodo;

import java.awt.image.RescaleOp;
import java.sql.*;

public class TraerTodoModelo extends General
{
    public static ArrayListGenerico<TraerTodo> traerATodos(int cursoID)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        ArrayListGenerico<TraerTodo> arrayTraetTodo = new ArrayListGenerico<>();
        String sql = "SELECT CONCAT (e.nombre, ' ', e.apellido) AS nombreYapellido, CONCAT (numeroExamen, ' ', ex.nombre) AS numeroYnombreExamen, tie.nota FROM estudiantes e " +
                "INNER JOIN tablaintermediaestudiantesxcursos tic ON e.id = tic.estudianteID " +
                "INNER JOIN tablaintermediaestudiantexexamen tie ON e.id = tie.estudianteID " +
                "INNER JOIN examenes ex ON ex.id = tie.examenID " +
                "WHERE tic.cursoID = ? " +
                "ORDER BY e.nombre, numeroYnombreExamen;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cursoID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombreYapellido = resultSet.getString("nombreYapellido");
                String numeroYnombreExamen = resultSet.getString("numeroYnombreExamen");
                int nota = resultSet.getInt("nota");
                TraerTodo traerTodo = new TraerTodo(nombreYapellido, nota, numeroYnombreExamen);
                arrayTraetTodo.agregar(traerTodo);
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
        return arrayTraetTodo;
    }
}
