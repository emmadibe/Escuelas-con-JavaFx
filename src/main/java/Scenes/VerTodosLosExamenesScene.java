package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Examen;
import Controladores.ExamenControlador;
import Excepciones.ArrayListVacioException;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class VerTodosLosExamenesScene extends VBox implements Escenas //La idea es mostrar a todos los exámenes del curso en esta escena.
{
    private VBox vBox;
    private Curso curso;
    private VBox vBoxBotones;
    private Stage stage;
    private Button buttonCancelar;
    private HBox hboxBotonCancelar;
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
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.
        ///////////////////////////////////////////NODOS
        //Me traigo a todos los exámenes:
        ExamenControlador examenControlador = new ExamenControlador(this.getCurso().getID());
        ArrayListGenerico<Examen> arrayListExamen = examenControlador.traerTodos(); //En arrayListExamen tengo a todos los exámenes. Cada índice del arrayList cotniene un examen.
        this.setvBoxBotones(arrayListExamen.transformarTEnBotones(this.getStage()));//En esta caja vbox tengo botones. Cada botón es un examen. La lógica está en arrayListgenerico.

        //Botón cancelar:
        this.setButtonCancelar(MetodosGeneralesFx.createButton("Cancelar", 100));
        this.setHboxBotonCancelar(new HBox(10));
        this.getHboxBotonCancelar().getChildren().add(this.getButtonCancelar());
        //Agrego los nodos a mi layouts:
        this.getvBox().getChildren().addAll(this.getvBoxBotones(), this.getHboxBotonCancelar());
        /////////////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////////////////MANEJADORES DE EVENTOS
        //Botón cancelar:
        this.getButtonCancelar().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            Scene scene1 = verCursoScene.crear();
            stage.setScene(scene1);
        });

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

    public Button getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(Button buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public HBox getHboxBotonCancelar() {
        return hboxBotonCancelar;
    }

    public void setHboxBotonCancelar(HBox hboxBotonCancelar) {
        this.hboxBotonCancelar = hboxBotonCancelar;
    }
}
