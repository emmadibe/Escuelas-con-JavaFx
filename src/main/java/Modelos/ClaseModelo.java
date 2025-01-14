package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Clase;
import Interfaces.Modelos;
import kotlin.DeepRecursiveFunction;

import java.sql.*;

public class ClaseModelo extends General implements Modelos<Clase>
{
    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS clases (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "cursoID INT," +
                    "fecha DATE," +
                    "CONSTRAINT fk_id_curso_clases FOREIGN KEY (cursoID) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE" +
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

    @Override
    public void agregarRegistroBDD(Clase clase)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        java.sql.Date fechaSQL = java.sql.Date.valueOf(clase.getFechaDeLaClase()); // Conversión a java.sql.Date. MySQL no acepta el formato LocalDate, debo convertirlo a java.sql.Date.
        String sql = "INSERT INTO clases(fecha, cursoID) " +
                    "VALUES(?, ?);";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, fechaSQL);
            preparedStatement.setInt(2, clase.getCursoID());
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

    @Override
    public void existeTablaBDD() {

    }

    @Override
    public void borrarRegistroBDD(Clase clase) {

    }

    @Override
    public void editarRegistroBDD(Clase clase) {

    }

    @Override
    public Clase traerRegistroBDD(Clase clase) {
        return null;
    }

    @Override
    public ArrayListGenerico traerTodosBDD(int id) {
        return null;
    }

    @Override
    public boolean existeRegistroBDD(Clase clase) {
        return false;
    }

    @Override
    public int traerIdUltimoRegistroBDD()
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lastID = -1;
        String sql = "SELECT id FROM clases ORDER BY id DESC LIMIT 1";
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
                if(resultSet != null) resultSet.close();
                if(statement != null) statement.close();
                if(preparedStatement != null) preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return lastID;
    }

    @Override
    public Clase traerRegistroAPartirDeIDBDD(int id) {
        return null;
    }
}
