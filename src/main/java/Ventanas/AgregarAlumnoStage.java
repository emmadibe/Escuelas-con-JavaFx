package Ventanas;

import ClasesPrincipales.Curso;
import Scenes.AgregarAlumnosScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AgregarAlumnoStage extends Application //Todas las clases principales (o sea, todas las nuevas ventanas) en un proyecto JavaFx deben heredar de Application
{
    private Curso curso;

    public AgregarAlumnoStage(Curso curso)
    {
        this.setCurso(curso);
    }
    @Override
    public void start(Stage stage) throws Exception
    {
        //////////////////CREO UNA ESCENA/SCENE
        //Instancio la clase-escena AgregarAlumnoScene:
        AgregarAlumnosScene agregarAlumnosScene = new AgregarAlumnosScene(this.getCurso());
        //Instancio una escena:
        Scene scene = agregarAlumnosScene.crear();
        ///////////////////VENTANA/STAGE
        stage.setTitle("Agregar alumno");
        stage.setScene(scene);
        stage.show();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
