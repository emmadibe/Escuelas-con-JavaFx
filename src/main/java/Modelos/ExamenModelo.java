package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Examen;
import Interfaces.Modelos;

import java.sql.*;

public class ExamenModelo extends General implements Modelos<Examen>
{
    @Override
    public Examen traerRegistroAPartirDeIDBDD(int id) {
        return null;
    }

    @Override
    public ArrayListGenerico traerTodosBDD(int idCurso)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Examen> arrayExamenes = new ArrayListGenerico<>();
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM examenes WHERE cursoID = " + idCurso;
            resultSet = statement.executeQuery(sql);//ResultSet: Es un objeto en Java que representa el conjunto de resultados devueltos por una consulta SQL. No almacena los resultados en forma de un array, sino que permite iterar sobre las filas de resultados una por una.
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int cursoID = resultSet.getInt("cursoID");
                String nombre = resultSet.getString("nombre");
                int numeroExamen = resultSet.getInt("numeroExamen");
                Examen examen = new Examen(id, cursoID, nombre, numeroExamen);
                arrayExamenes.agregar(examen);
                System.out.println("Examen agregado correctamente");
            }
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
        return arrayExamenes;
    }

    @Override
    public boolean existeRegistroBDD(Examen examen) {
        return false;
    }

    @Override
    public void existeTablaBDD() {

    }

    @Override
    public void editarRegistroBDD(Examen examen) {

    }

    @Override
    public void borrarRegistroBDD(Examen examen) {

    }

    @Override
    public void agregarRegistroBDD(Examen examen)
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO examenes(cursoID, numeroExamen, nombre) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, examen.getCursoID());
            pstmt.setInt(2, examen.getNumeroExamen());
            pstmt.setString(3, examen.getNombre());
            pstmt.executeUpdate();
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

    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS examenes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "cursoID INT, " +
                    "nombre VARCHAR(100), " +
                    "numeroExamen INT, " +
                    "CONSTRAINT fk_id_cursoParaExamen FOREIGN KEY (cursoID) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE, " +
                    "UNIQUE (cursoID, nombre)" + //Para que esa combinación no se repita.
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Tabla exámenes creada con éxito.");
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

    @Override
    public Examen traerRegistroBDD(Examen examen) {
        return null;
    }

    @Override
    public int traerIdUltimoRegistroBDD()
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int lastId = -1;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();

            String sql = "SELECT id FROM examenes ORDER BY id DESC LIMIT 1";
            resultSet = statement.executeQuery(sql);


            if (resultSet.next()) {
                lastId = resultSet.getInt("id");

            } else {
                System.out.println("No se encontraron registros en la tabla.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lastId;
    }
}
