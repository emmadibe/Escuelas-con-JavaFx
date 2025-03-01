package Ventanas;

import ClasesPrincipales.*;
import Controladores.*;
import Excepciones.ArrayListVacioException;
import Modelos.ClaseModelo;
import Modelos.SeguimientoModelo;
import Modelos.TraerTodoAsistenciaModelo;
import Scenes.LoginScene;
import com.ecodeup.jdbc.Conexion;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Inicio extends Application
{
    public static void main(String[] args)
    {
        Conexion.getConnection(); //Me conecto a la base de datos. Si no existe, la creo.we
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        //Reproduzco la música
        Musica.playMusic();
        ///////////////////////////CREAR UNA ESCENA
        //Instancio la clase-escena LoginScene; pues, es la escena inicial que debe tener mi ventana:
        LoginScene loginScene = new LoginScene();
        //Instancio una escena. Mi primer escena:
        Scene scene = loginScene.crear();
        /////////////////////////Trabajo con la VENTANA
        //Le agrego un ícono a mi ventana:
        stage.getIcons().add(new Image("A:\\Dev\\UTN\\carrera\\Programación 2 Java\\JavaFx\\probarJavaFx\\TpFinalCOnJavaFx\\xardas.jpeg"));
        //Agrego mi escena a mi VENTANA(Stage). Establezco la escena de logueo como mi escena actual en mi ventana:
        stage.setScene(scene);
        stage.setTitle("LoginScene"); //Seteo un título a mi ventana(stage).
        /////////////////////////////Muestro:
        stage.show();

    }
}
