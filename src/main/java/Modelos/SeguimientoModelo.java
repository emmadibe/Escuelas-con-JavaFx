package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Seguimiento;
import Interfaces.Modelos;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
                    "cursoID INT, " +
                    "CONSTRAINT fk_id_curso FOREIGN KEY (cursoID) REFERENCES cursos(id) ON DELETE CASACADE ON UPDATE CASCADE" +
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
    public void agregarRegistroBDD(Seguimiento seguimiento) {

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
    public ArrayListGenerico traerTodosBDD(int id) {
        return null;
    }

    @Override
    public boolean existeRegistroBDD(Seguimiento seguimiento) {
        return false;
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
