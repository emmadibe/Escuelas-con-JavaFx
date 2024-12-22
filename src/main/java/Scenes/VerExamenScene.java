package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Estudiante;
import ClasesPrincipales.Examen;
import Controladores.CursoControlador;
import Controladores.EstudianteControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class VerExamenScene extends VBox implements Escenas
{
    private Examen examen;
    private Label labelExamen;
    private VBox vBox;
    private VBox estudiantesVbox;
    private Stage stage;
    private HBox hBoxBotones;
    private Button buttonVolver;

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
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.
        //////////////////////////////////////////NODOS
        //Me tarigo a todos los alumnos que comparten este examen
        ArrayListGenerico<Estudiante> arrayListEstudiante;
        EstudianteControlador estudianteControlador = new EstudianteControlador();
        arrayListEstudiante = estudianteControlador.traerTodosLosAlumnosDeUnExamen(this.getExamen().getId()); //En este arrayList tengo a todos los estudiantes que comparten el examen.
       //Botón volver:
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 100));
        this.sethBoxBotones(new HBox(10)); //Seteo mi caja Hbox. Aquí guardo mi botón. Tiene una distancia entre elementos de 10px.
        this.gethBoxBotones().getChildren().add(this.getButtonVolver());
        this.gethBoxBotones().setAlignment(Pos.CENTER);

        /////ESCENAS
        Scene scene = null;
        if(!arrayListEstudiante.estaVacio()){
            this.setEstudiantesVbox(arrayListEstudiante.transformarTEnBotones(this.getStage()));//En esta caja vbox tengo botones. Cada botón es un estudiante. La lógica está en arrayListgenerico.
            //Agrego los nodos a mi layouts:
            this.getvBox().getChildren().addAll(this.getEstudiantesVbox(), this.gethBoxBotones());
            //Seteo la instancia de escena
            scene = new Scene(this.getvBox(), 800, 600);
            //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
            scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        }

        //////////////////////////////////////////MANEJADORES DE EVENTOS
        //Volver:
        this.getButtonVolver().setOnAction(e -> {
            CursoControlador cursoControlador = new CursoControlador();
            Curso curso = cursoControlador.traerRegistroAPartirDeID(this.getExamen().getCursoID());
            VerTodosLosExamenesScene verTodosLosExamenesScene = new VerTodosLosExamenesScene(this.getStage(), curso);
            Scene scene1 = verTodosLosExamenesScene.crear();
            Stage stage1 = this.getStage();
            stage1.setScene(scene1);
        });
        
        return scene;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }


    public Label getLabelExamen() {
        return labelExamen;
    }

    public void setLabelExamen(Label labelExamen) {
        this.labelExamen = labelExamen;
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

    public VBox getvBox() {
        return vBox;
    }

    public HBox gethBoxBotones() {
        return hBoxBotones;
    }

    public void sethBoxBotones(HBox hBoxBotones) {
        this.hBoxBotones = hBoxBotones;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

}
