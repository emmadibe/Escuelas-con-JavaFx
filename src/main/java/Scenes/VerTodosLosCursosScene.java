package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import Controladores.CursoControlador;
import Excepciones.ArrayListVacioException;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerTodosLosCursosScene extends VBox implements Escenas
{
    private Docente docente;
    private VBox vBox;
    private Label labelDocente;
    private VBox vBoxBotones;
    private Button buttonVolver;
    private Stage stage;

    public VerTodosLosCursosScene(Docente docente, Stage stage)
    {
        this.setDocente(docente);
        this.setStage(stage);
    }

    @Override
    public Scene crear()
    {
        /////////////////////////////////LAYOUTS:
        //Instancio layout Vbox
        this.setvBox(new VBox(10)); //10px es la distancia entre los nodos del layout

        //////////////////////////////////NODOS
        //label
        this.setLabelDocente(MetodosGeneralesFx.crearLabel("docente", "pink", "10", "10"));
        this.getLabelDocente().setText(
                "docenteID: " + this.getDocente().getID() + "\n" +
                "Nombre: " + this.getDocente().getNombre() + "n\n" +
                "Apellido: " + this.getDocente().getApellido() + "n\n" +
                "DNI: " + this.getDocente().getDni() + "n\n" +
                "Email: " + this.getDocente().getEmail() + "n\n"
        );
        //TRAERME TODOS LOS CURSOS:
        CursoControlador cursoControlador = new CursoControlador(this.getDocente()); //Instancio cursoControlador.
        ArrayListGenerico<Curso> arrayListGenerico = cursoControlador.traerTodos(); //Me traigo, en forma de arrayList, todos los cursos del docente.
        this.setvBoxBotones(arrayListGenerico.transformarTEnBotones(this.getStage())); //En esta caja VBox, tengo botones. Cada botón es un curso. La lógica está en arrayListGenerico.

        //Button volver
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 200));
        this.getButtonVolver().setAlignment(Pos.BOTTOM_CENTER);

        ////Agrego los nodos  mi layouts:
        this.getvBox().getChildren().addAll(this.getLabelDocente(), this.getvBoxBotones(), this.getButtonVolver());

        //////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);

        ////////////////////////////////////MANEJO DE EVENTOS:
        //Button volver:
        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            DocenteScene docenteScene = new DocenteScene(this.getDocente());
            stage.setScene(docenteScene.crear());
        });

        return scene;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Label getLabelDocente() {
        return labelDocente;
    }

    public void setLabelDocente(Label labelDocente) {
        this.labelDocente = labelDocente;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public VBox getvBoxBotones() {
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

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }
}
