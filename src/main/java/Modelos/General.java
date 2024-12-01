package Modelos;

import java.sql.*;

    public abstract class General
    {
        protected static String jdbcDriver = "com.mysql.jdbc.Driver";
        protected static String dbURL = "jdbc:mysql://localhost:3306/"; //En un principio, no pongo el nombre de la base de datos después del último slash(/). Pues, sino me va a tirar un error la primera vez que haga el programa porque aun no existe la base de datos. Lo que voy a hacer, primero, es crear la base de datos y, luego de crearla, seteo este atributo para agregarle el nombre.
        protected static String username = "root";
        protected static String password = "";

        public static void crearTabla()
        {

        }

        public static boolean  existeTabla(String nombreTabla) {
            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;
            boolean existe = false;

            try {
                Class.forName(jdbcDriver);
                connection = DriverManager.getConnection(dbURL, username, password);
                statement = connection.createStatement();


                String sql = "SELECT COUNT(*) FROM information_schema.tables " +
                        "WHERE table_schema = 'EscuelasConJavaFx' AND table_name = '" + nombreTabla + "'"; //EL nombre de la tabla debe estar entre comillas simples.

                resultSet = statement.executeQuery(sql);

                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    existe = count > 0; // Si el conteo es mayor que 0, la tabla existe; por ende, existe almacenará el valor true.
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return existe;
        }

        public static String getJdbcDriver() {
            return jdbcDriver;
        }

        public static String getDbURL() {
            return dbURL;
        }

        public static String getUsername() {
            return username;
        }

        public static String getPassword() {
            return password;
        }

        public static void setJdbcDriver(String jdbcDriver) {
            Modelos.General.jdbcDriver = jdbcDriver;
        }

        public static void setDbURL(String dbURL) {
            Modelos.General.dbURL = dbURL;
        }

        public static void setUsername(String username) {
            Modelos.General.username = username;
        }

        public static void setPassword(String password) {
            Modelos.General.password = password;
        }
    }


