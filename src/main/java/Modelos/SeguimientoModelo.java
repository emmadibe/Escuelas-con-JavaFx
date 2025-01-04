package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Seguimiento;
import Interfaces.Modelos;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import java.sql.*;
import java.time.LocalDate;

public class SeguimientoModelo extends  General implements Modelos<Seguimiento>
{
    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        String sql = "CREATE TABLE IF NOT EXISTS seguimientos " +
                    "(" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "titulo VARCHAR(60), " +
                    "cuerpo TEXT, " +
                    "fecha DATE, " +
                    "cursoID INT, " +
                    "CONSTRAINT fk_id_curso_seguimiento FOREIGN KEY (cursoID) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE" +
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
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void agregarRegistroBDD(Seguimiento seguimiento)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        java.sql.Date fechaSQL = java.sql.Date.valueOf(seguimiento.getFecha()); // Conversión a java.sql.Date. MySQL no acepta el formato LocalDate, debo convertirlo a java.sql.Date.
        String sql = "INSERT INTO seguimientos(titulo, cuerpo, fecha, cursoID)" +
                "VALUES(?, ?, ?, ?);";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, seguimiento.getTitulo());
            preparedStatement.setString(2, seguimiento.getCuerpo());
            preparedStatement.setDate(3, fechaSQL);
            preparedStatement.setInt(4, seguimiento.getCursoID());
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
    public void borrarRegistroBDD(Seguimiento seguimiento) {

    }

    @Override
    public void editarRegistroBDD(Seguimiento seguimiento) {

    }

    @Override
    public Seguimiento traerRegistroBDD(Seguimiento seguimiento) {
        return null;
    }

    @Override
    public ArrayListGenerico<Seguimiento> traerTodosBDD(int id)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Seguimiento> arrayListSeguimiento = new ArrayListGenerico<>();
        String sql = "SELECT * FROM seguimientos WHERE cursoID = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){ //Mientras haya registros en resultSet...
                String titulo = resultSet.getString("titulo");
                String cuerpo = resultSet.getString("cuerpo");
                int cursoID = id;
                int idSeguimiento = resultSet.getInt("id");
                LocalDate fecha = resultSet.getDate("fecha").toLocalDate(); //Transformo la fecha del formato sql al formato LocalDate.
                Seguimiento seguimiento = new Seguimiento(idSeguimiento, titulo, cuerpo, fecha, cursoID);
                arrayListSeguimiento.agregar(seguimiento);
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
        return arrayListSeguimiento;
    }

    @Override
    public boolean existeRegistroBDD(Seguimiento seguimiento) {
        return false;
    }

    public boolean existeRegistroParaCursoIDBDD(int cursoID)
    {
        boolean existe = false;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM seguimientos WHERE cursoID = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cursoID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){ //Si la bdd trajo registros y están en el resultSet, entonces retorno true.
                existe = true;
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
            return existe;
        }
    }

    @Override
    public int traerIdUltimoRegistroBDD() {
        return 0;
    }

    @Override
    public Seguimiento traerRegistroAPartirDeIDBDD(int id) {
        return null;
    }
}
