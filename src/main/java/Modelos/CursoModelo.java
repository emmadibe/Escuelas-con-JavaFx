package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ENUMS.Rama;
import Interfaces.Modelos;
import net.bytebuddy.matcher.StringMatcher;
import org.hibernate.dialect.DB2Dialect;

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
    public void editarRegistroBDD(Curso curso)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE cursos SET nombre = ?, cantAlumnos = ?, escuela = ?, materia = ? WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setInt(2, curso.getCantAlumnos());
            preparedStatement.setString(3, curso.getEscuela());
            preparedStatement.setString(4, curso.getMateria());
            preparedStatement.setInt(5, curso.getID());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(preparedStatement != null) preparedStatement.close();
            }catch ( SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void borrarRegistroBDD(Curso curso)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM cursos WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, curso.getID());
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

    public ArrayListGenerico<Integer> traerTodosLosCiclosLectivosDelDocenteBDD(int docenteID)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Integer> arrayListinteger = new ArrayListGenerico<>();
        String sql = "SELECT DISTINCT cicloLectivo FROM cursos WHERE docenteID = ?;"; //La consulta DISTINCT solo retorna los valores diferentes.
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, docenteID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Integer cicloLectivo = (Integer) resultSet.getInt("cicloLectivo");
                arrayListinteger.agregar(cicloLectivo);
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
        return arrayListinteger;
    }

    @Override
    public void agregarRegistroBDD(Curso curso)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO cursos(nombre, cantAlumnos, escuela, materia, docenteID, cicloLectivo)" +
                    "VALUES(?, ?, ?, ?, ?, ?);";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setInt(2, curso.getCantAlumnos());
            preparedStatement.setString(3, curso.getEscuela());
            preparedStatement.setString(4, curso.getMateria());
            preparedStatement.setInt(5, curso.getDocenteID());
            preparedStatement.setInt(6, curso.getCicloLectivo());
            preparedStatement.executeUpdate();
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

    public ArrayListGenerico<Curso> traerTodosPorCiclolectivoBDD(int cicloLectivo, int docenteID)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Curso> cursoArrayList = new ArrayListGenerico<Curso>();
        String sql = "SELECT * FROM cursos WHERE docenteID = ? AND cicloLectivo = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, docenteID);
            preparedStatement.setInt(2, cicloLectivo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String nombreCurso = resultSet.getString("nombre");
                String escuela = resultSet.getString("escuela");
                String materia = resultSet.getString("materia");
                int cantAlumnos = resultSet.getInt("cantAlumnos");
                int id = resultSet.getInt("id");
                int idDelDocente = resultSet.getInt("docenteID");
                int miCicloLectivo = resultSet.getInt("cicloLectivo");
                Curso curso = new Curso(id, nombreCurso, cantAlumnos, escuela, materia, idDelDocente, miCicloLectivo);
                cursoArrayList.agregar(curso);
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
        return cursoArrayList;
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
                int cicloLectivo = resultSet.getInt("cicloLectivo");
                Curso curso = new Curso(id, nombreCurso, cantAlumnos, escuela, materia, idDelDocente, cicloLectivo);
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
                int cicloLectivo = resultSet.getInt("cicloLectivo");
                curso = new Curso(id, nombre, cantAlumnos, escuela, materia, docenteID, cicloLectivo);
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
                    "cicloLectivo INT," +
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
