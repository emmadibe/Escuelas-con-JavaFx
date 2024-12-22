package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TraerTodo;
import Excepciones.ArrayListVacioException;

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
        String sql = "SELECT \n" +
                "    CONCAT(e.nombre, ' ', e.apellido) AS nombreYapellido, \n" +
                "    CONCAT(ex.numeroExamen, ' ', ex.nombre) AS numeroYnombreExamen, \n" +
                "    te.nota, \n" +
                "    ex.numeroExamen \n" +
                "FROM \n" +
                "    estudiantes e \n" +
                "INNER JOIN \n" +
                "    tablaintermediaestudiantesxcursos tic ON e.id = tic.estudianteID \n" +
                "INNER JOIN \n" +
                "    tablaintermediaestudiantexexamen te ON e.id = te.estudianteID \n" +
                "INNER JOIN \n" +
                "    examenes ex ON te.examenID = ex.id \n" +
                "WHERE \n" +
                "    tic.cursoID = ? \n" +
                "AND \n" +
                "    te.nota = (SELECT MAX(nota) FROM tablaintermediaestudiantexexamen WHERE estudianteID = e.id AND examenID = te.examenID)\n" +
                "ORDER BY \n" +
                "    e.nombre, ex.numeroExamen;";
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
                int numeroExamen = resultSet.getInt("numeroExamen");
                TraerTodo traerTodo = new TraerTodo(nombreYapellido, nota, numeroYnombreExamen, numeroExamen);
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
        try {
            arrayTraetTodo.imprimirTodo();
        } catch (ArrayListVacioException e) {
            throw new RuntimeException(e);
        }
        return arrayTraetTodo;
    }
}
