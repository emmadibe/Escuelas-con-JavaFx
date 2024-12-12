package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Estudiante;
import ClasesPrincipales.Examen;
import Controladores.EstudianteControlador;
import Interfaces.Escenas;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.jfr.consumer.EventStream;

import java.awt.*;

public class VerExamenScene extends VBox implements Escenas
{
    private Examen examen;
    private Button buttonVolver;
    private Label labelExamen;
    private VBox vBox;
    private VBox estudiantesVbox;
    private Stage stage;

    public VerExamenScene(Examen examen, Stage stage)
    {
        this.setStage(stage);
        this.setExamen(examen);
    }
    public VerExamenScene(Examen examen)
    {
        this.setExamen(examen);
    }

    @Override
    public Scene crear()
    {
        /////////////////////////////////////////LAYOUTS
        this.setvBox(new VBox(10));

        //////////////////////////////////////////NODOS
        //Me tarigo a todos los alumnos que comparten este examen
        ArrayListGenerico<Estudiante> arrayListEstudiante;
        EstudianteControlador estudianteControlador = new EstudianteControlador();
        arrayListEstudiante = estudianteControlador.traerTodosLosAlumnosDeUnExamen(this.getExamen().getId()); //En este arrayList tengo a todos los estudiantes que comparten el examen.
        //////////////////////////////////////////ESCENAS
        Scene scene = null;
        if(!arrayListEstudiante.estaVacio()){
            this.setEstudiantesVbox(arrayListEstudiante.transformarTEnBotones(this.getStage()));//En esta caja vbox tengo botones. Cada botón es un estudiante. La lógica está en arrayListgenerico.
            //Agrego los nodos a mi layouts:
            this.getvBox().getChildren().addAll(this.getEstudiantesVbox());
            //Seteo la instancia de escena
            scene = new Scene(this.getvBox(), 800, 600);
        }
        //////////////////////////////////////////MANEJADORES DE EVENTOS
        return scene;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public Label getLabelExamen() {
        return labelExamen;
    }

    public void setLabelExamen(Label labelExamen) {
        this.labelExamen = labelExamen;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public VBox getEstudiantesVbox() {
        return estudiantesVbox;
    }

    public void setEstudiantesVbox(VBox estudiantesVbox) {
        this.estudiantesVbox = estudiantesVbox;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
