package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Docente;
import ENUMS.Rama;
import Interfaces.Modelos;

import java.sql.*;

public class DocenteModelo extends General implements Modelos<Docente>
{
    @Override
    public void crearTablaBDD()
    {
        Connection connection = null;
        Statement statement = null;

        try{
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS docentes (" +
                    "    id INT AUTO_INCREMENT PRIMARY KEY," +
                    "    nombre VARCHAR(100)," +
                    "    apellido VARCHAR(100)," +
                    "    edad INT," +
                    "    DNI VARCHAR(30) UNIQUE," +
                    "    email VARCHAR(50) UNIQUE," +
                    "    rama VARCHAR(50)," +
                    "    password VARCHAR(59)" +
                    ")";
            statement.executeUpdate(sql);
            System.out.println("Tabla docentes creada con exito");
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if(statement != null) statement.close();
                if(connection != null) connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int traerIdUltimoRegistroBDD() {
        return 0;
    }

    @Override
    public Docente traerRegistroBDD(Docente docente) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM docentes WHERE email = '" + docente.getEmail() + "' and password = '" + docente.getPassword() + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String rama = resultSet.getString("rama").toLowerCase(); //Hago el lowerCase para que aparezca en minuscula y asi identificar la rama.
                int edad = resultSet.getInt("edad");
                String dni = resultSet.getString("DNI");

                Rama rama1 = Rama.asignarRama(rama);

                docente = new Docente(id, nombre,apellido, dni, docente.getEmail(), edad, rama1, docente.getPassword());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) statement.close();
                if(connection != null) connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return docente;
    }

    @Override
    public Docente traerRegistroAPartirDeIDBDD(int id)
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Docente docente = null;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM docentes WHERE id = " + id;
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String DNI = resultSet.getString("DNI");
                int edad = resultSet.getInt("edad");
                String email = resultSet.getString("email");
                String ramaNoConvertida = resultSet.getString("rama");
                Rama rama = Rama.asignarRama(ramaNoConvertida);
                String password = resultSet.getString("password");
                docente = new Docente(id, nombre, apellido, DNI, email, edad, rama, password);
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
        return docente;
    }

    @Override
    public ArrayListGenerico traerTodosBDD(int id) {
        return null;
    }

    @Override
    public void agregarRegistroBDD(Docente docente)
    {
        Connection connection = null;
        Statement statement = null;
        docente.imprimirUnaInstancia();
        try{
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "INSERT INTO docentes(nombre, apellido, edad, email, DNI, rama, password) VALUES ('" +
                    docente.getNombre().replace("'", "''") + "', '" +
                    docente.getApellido().replace("'", "''") + "', " +
                    docente.getEdad() + ", '" +
                    docente.getEmail().replace("'", "''") + "', '" +
                    docente.getDni().replace("'", "''") + "', '" +
                    docente.getRama().toString().replace("'", "''") + "', '" +
                    docente.getPassword().replace("'","''") + "')";

            statement.executeUpdate(sql);
            System.out.println("Docente creado con éxito!");
        }catch (SQLException e) {
            if(e.getSQLState().equals("23000")){ //La excepción "23000" indica un valor unique duplicado.
                System.out.println("El email ingresado ya se encuewntra registrado.");
            }else {
                e.printStackTrace();
            }
        } finally {
            try {
                if(statement != null) statement.close();
                if (connection != null) connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void borrarRegistroBDD(Docente docente) {

    }

    @Override
    public void editarRegistroBDD(Docente docente)
    {
        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE docentes " +
                    "SET nombre = ?, apellido = ?, password = ? " +
                    "WHERE id = ?;";
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, docente.getNombre());
            preparedStatement.setString(2, docente.getApellido());
            preparedStatement.setString(3, docente.getPassword());
            preparedStatement.setInt(4, docente.getID());
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
    public void existeTablaBDD()
    {

    }



    @Override
    public boolean existeRegistroBDD(Docente docente)
    {
        String email = docente.getEmail();
        String pass = docente.getPassword();
        Connection connection = null;
        Statement statement = null;
        boolean existe = true;
        try {
            connection = DriverManager.getConnection(dbURL, username, password);
            statement = connection.createStatement();
            String sql = "SELECT * FROM docentes WHERE email = '" + email + "' and password = '" + pass + "'";
            ResultSet resultSet =  statement.executeQuery(sql);
            if(!resultSet.next()){
                existe = false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return existe;
    }
}
