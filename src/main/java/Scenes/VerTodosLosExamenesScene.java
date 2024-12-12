package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Examen;
import Controladores.ExamenControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class VerTodosLosExamenesScene extends VBox implements Escenas //La idea es mostrar a todos los exámenes del curso en esta escena.
{
    private VBox vBox;
    private Curso curso;
    private VBox vBoxBotones;
    private Stage stage;
    public VerTodosLosExamenesScene(Stage stage, Curso curso)
    {
        this.setCurso(curso);
        this.setStage(stage);
    }
    @Override
    public Scene crear()
    {
        ///////////////////////////////////////////LAYOUTS
        //Uso un layouts VBox
        this.setvBox(new VBox(10));  //Instancio mi layouts Vbox. 10px de distancia entre los elementos.

        ///////////////////////////////////////////NODOS
        //Me traigo a todos los exámenes:
        ExamenControlador examenControlador = new ExamenControlador(this.getCurso().getID());
        ArrayListGenerico<Examen> arrayListExamen = examenControlador.traerTodos(); //En arrayListExamen tengo a todos los exámenes. Cada índice del arrayList cotniene un examen.
        this.setvBoxBotones(arrayListExamen.transformarTEnBotones(this.getStage()));//En esta caja vbox tengo botones. Cada botón es un examen. La lógica está en arrayListgenerico.

        //Agrego los nodos a mi layouts:
        this.getvBox().getChildren().addAll(this.getvBoxBotones());
        /////////////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        ////////////////////////////////////////////MANEJADORES DE EVENTOS
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Node getvBoxBotones() {
        return vBoxBotones;
    }

    public void setvBoxBotones(VBox vBoxBotones) {
        this.vBoxBotones = vBoxBotones;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
