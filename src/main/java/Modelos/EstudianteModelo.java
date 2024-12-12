package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Estudiante;
import Excepciones.ArrayListVacioException;
import Interfaces.Modelos;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.*;

public class EstudianteModelo extends General implements Modelos<Estudiante>
{
    @Override
    public Estudiante traerRegistroBDD(Estudiante estudiante) {
        return null;
    }

    public ArrayListGenerico<Estudiante> traerTodosLosAlumnosDeUnExamen(int examenID)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Estudiante> arrayListEstudiantes = new ArrayListGenerico<Estudiante>();
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM estudiantes e INNER JOIN tablaintermediaestudiantexexamen ti ON e.id = ti.estudianteID WHERE ti.examenID = " + examenID + ";";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                int estudianteID = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String DNI = resultSet.getString("DNI");
                String email = resultSet.getString("email");
                int edad = resultSet.getInt("edad");
                Estudiante estudiante = new Estudiante(estudianteID, nombre, apellido, DNI, email, edad, examenID);
                arrayListEstudiantes.agregar(estudiante);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        try{
            arrayListEstudiantes.imprimirTodo();
        }catch (ArrayListVacioException e){
            e.printStackTrace();
        }
        return arrayListEstudiantes;
    }

    @Override
    public int traerIdUltimoRegistroBDD() {
        Connection connection = null;
        Statement statement = null;
        int idEstudiante = -1;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT MAX(id) AS ultimoID FROM estudiantes";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                idEstudiante = resultSet.getInt("ultimoID");
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
        return idEstudiante;
    }

    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(jdbcDriver);
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Estudiantes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "nombre VARCHAR(100), " +
                    "apellido VARCHAR(100)," +
                    "email VARCHAR(100)," +
                    "DNI VARCHAR(100) UNIQUE," + //Para poder identificar que el estudiante no sea repetido.
                    "edad INT)";
            statement.executeUpdate(sql);
            System.out.println("Tabla 'Estudiantes' creada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void agregarRegistroBDD(Estudiante estudiante)
    {
        Connection connection = null;
        Statement statement = null;
        try{
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO estudiantes(nombre, edad, dni, apellido, email) VALUES (?, ?, ?, ?, ?)"; //Forma de insert into para evitar inyección sql.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, estudiante.getNombre());
            preparedStatement.setInt(2, estudiante.getEdad());
            preparedStatement.setString(3, estudiante.getDni());
            preparedStatement.setString(4, estudiante.getApellido());
            preparedStatement.setString(5, estudiante.getEmail());
            preparedStatement.executeUpdate();

            System.out.println("Estudiante nuevo creado con éxito");
        }catch (SQLException e){
            System.out.println("No se pudo crear al estudiante");
        }catch (ConstraintViolationException e){ //Excepción por si el usuario ingresa un valor repetido en un campo con la constrcciion UNIQUE. DNI tiene esa constriccion.
            System.out.println("Ya existe un usuario con ese DNI.");
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
    public void borrarRegistroBDD(Estudiante estudiante) {

    }

    @Override
    public void editarRegistroBDD(Estudiante estudiante) {

    }

    @Override
    public void existeTablaBDD() {

    }

    @Override
    public boolean existeRegistroBDD(Estudiante estudiante) {
        return false;
    }

    @Override
    public ArrayListGenerico traerTodosBDD(int id)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        ArrayListGenerico<Estudiante> arrayListEstudiantes = new ArrayListGenerico<Estudiante>();
        System.out.println("CURSO ID: " + id);
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT e.* FROM estudiantes e JOIN tablaintermediaestudiantesxcursos ti ON e.id = ti.estudianteID WHERE ti.cursoID = " + id + ";";
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){ //Mientras haya algo en resultSet...
                int idEstudiante = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String dni = resultSet.getString("DNI");
                int edad = resultSet.getInt("edad");
                Estudiante estudiante = new Estudiante(idEstudiante, nombre, apellido, dni, email, edad);
                arrayListEstudiantes.agregar(estudiante);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(connection != null) connection.close();
                if(statement != null) statement.close();
                if(resultSet != null) resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return arrayListEstudiantes;
    }

    @Override
    public Estudiante traerRegistroAPartirDeIDBDD(int id) {
        return null;
    }

}
