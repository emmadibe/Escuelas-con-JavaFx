package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ENUMS.Rama;
import Interfaces.Modelos;
import net.bytebuddy.matcher.StringMatcher;

import java.sql.*;

public class CursoModelo extends General implements Modelos<Curso>
{
    @Override
    public boolean existeRegistroBDD(Curso curso) {
        return false;
    }

    @Override
    public void existeTablaBDD() {

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

            String sql = "SELECT id FROM cursos ORDER BY id DESC LIMIT 1";
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

    @Override
    public void editarRegistroBDD(Curso curso) {

    }

    @Override
    public void borrarRegistroBDD(Curso curso) {

    }

    @Override
    public void agregarRegistroBDD(Curso curso)
    {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO cursos(nombre, cantAlumnos, escuela, materia, docenteID) " +
                    "VALUES ('" +
                    curso.getNombre().replace("'", "''") + "', " +
                    curso.getCantAlumnos()+ ", '" +
                    curso.getEscuela().replace("'", "''") + "', '" +
                    curso.getMateria().replace("'", "''") + "', " +
                    curso.getDocenteID() + ")";
            statement.executeUpdate(sql);
            System.out.println("Curso nuevo creado con éxito!!");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(statement != null)statement.close();
                if(connection != null)connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public ArrayListGenerico traerTodosBDD(int docenteID)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Curso> cursoArrayList = new ArrayListGenerico<Curso>();
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM cursos WHERE docenteID = " + docenteID;
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String nombreCurso = resultSet.getString("nombre");
                String escuela = resultSet.getString("escuela");
                String materia = resultSet.getString("materia");
                int cantAlumnos = resultSet.getInt("cantAlumnos");
                int id = resultSet.getInt("id");
                int idDelDocente = resultSet.getInt("docenteID");
                Curso curso = new Curso(id, nombreCurso, cantAlumnos, escuela, materia, idDelDocente);
                cursoArrayList.agregar(curso);
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
        return cursoArrayList;
    }

    @Override
    public Curso traerRegistroAPartirDeIDBDD(int id)
    {
        Curso curso = null;
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM cursos WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){ //Si la consulta me trajo un registro...
                String nombre = resultSet.getString("nombre");
                int cantAlumnos = resultSet.getInt("cantAlumnos");
                String escuela = resultSet.getString("escuela");
                String materia = resultSet.getString("materia");
                int docenteID = resultSet.getInt("docenteID");
                curso = new Curso(id, nombre, cantAlumnos, escuela, materia, docenteID);
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
        return curso;
    }

    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS cursos ("+
                    " id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nombre VARCHAR(100)," +
                    "cantAlumnos INT," +
                    "escuela VARCHAR(100)," +
                    "materia VARCHAR(100)," +
                    "rama VARCHAR(100)," +
                    "docenteID INT," +
                    "CONSTRAINT fk_id_docente FOREIGN KEY (docenteID) REFERENCES docentes(id) ON DELETE CASCADE ON UPDATE CASCADE" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Tabla cursos creada con éxito");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try{
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public Curso traerRegistroBDD(Curso curso) {
        return null;
    }
}
