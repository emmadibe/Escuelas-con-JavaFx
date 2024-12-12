package Ventanas;

import Scenes.ActualizarNotaScene;
import Scenes.AgregarExamenScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AgregarNotaStage extends Application //Todas las clases principales (las nuevas ventanas) deben extender de application
{
    private int examenID; //Me lo tengo que traer para la tabla intermedia de estudiantes x examen,
    private int estudianteID;

    public AgregarNotaStage(int examenID, int estudianteID)
    {
        this.setExamenID(examenID);
        this.setEstudianteID(estudianteID);
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        /////////////////////////////////CREO UNA ESCENA
        //Instancio la clase-escena ActualizarNotaScene:
        ActualizarNotaScene actualizarNotaScene = new ActualizarNotaScene(this.getExamenID(), this.getEstudianteID());
        //Instancio una escena
        Scene scene = actualizarNotaScene.crear();
        ////////////////////////////////////VENTANA
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Actualizar notas");
        stage.show();
    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

}
