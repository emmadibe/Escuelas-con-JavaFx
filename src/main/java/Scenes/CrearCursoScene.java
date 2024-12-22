package Scenes;

import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import Controladores.CursoControlador;
import ENUMS.Rama;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Currency;

public class CrearCursoScene extends VBox implements Escenas
{
    private Label labelNombreCurso;
    private TextField displayNombreCurso;
    private Label labelCantAlumnos;
    private TextField displayCantAlumnos;
    private Label labelEscuela;
    private TextField displayEscuela;
    private Label labelMateria;
    private TextField displayMateria;
    private TextField rama;
    private Button buttonCrearCurso;
    private Button buttonCancelar;
    private VBox vBox;
    private Docente docente; //Así tengo los datos del docente.

    public CrearCursoScene(Docente docente)
    {
        this.setDocente(docente);
    }

    @Override
    public Scene crear()
    {
        /////////////////////////////////LAYOUTS
        //Uso un layouts VBox
        vBox = new VBox();
        this.getvBox().setSpacing(10); //Seteo la distancia entre los nodos del layouts.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////////////////NODOS
        //Etiquetas (Labels) y camos de texto (TextFields).
        this.setLabelNombreCurso(MetodosGeneralesFx.crearLabel("Nombre del curso","null", "0", "0"));
        this.setDisplayNombreCurso(MetodosGeneralesFx.crearTextField());
        this.setLabelEscuela(MetodosGeneralesFx.crearLabel("Escuela", "null", "0", "0"));
        this.setDisplayEscuela(MetodosGeneralesFx.crearTextField());
        this.setLabelMateria(MetodosGeneralesFx.crearLabel("Materia", "null", "0", "0"));
        this.setDisplayMateria(MetodosGeneralesFx.crearTextField());
        this.setLabelCantAlumnos(MetodosGeneralesFx.crearLabel("Cantidad de alumnos", "null", "0", "0"));
        this.setDisplayCantAlumnos(MetodosGeneralesFx.crearTextField());

        //Botones
        //Creo un HBox para los botones y para poder centrarlos. Son solo dos.
        HBox buttonBox = new HBox(10);
        //Instancio mis botones:
        this.setButtonCrearCurso(MetodosGeneralesFx.createButton("Crear curso", 150));
        this.setButtonCancelar(MetodosGeneralesFx.createButton("Cancelar", 150));
        //Agrego mis botones a la caja:
        buttonBox.getChildren().addAll(this.getButtonCrearCurso(), this.getButtonCancelar());
        //Alineo mis botones al centro:
        buttonBox.setAlignment(Pos.CENTER);

        //Agreo todos mis nodos (Labels, TextFields y Botones) a mi layputs:
        this.getvBox().getChildren().addAll(
                this.getLabelNombreCurso(),
                this.getDisplayNombreCurso(),
                this.getLabelEscuela(),
                this.getDisplayEscuela(),
                this.getLabelMateria(),
                this.getDisplayMateria(),
                this.getLabelCantAlumnos(),
                this.getDisplayCantAlumnos(),
                buttonBox);

        /////////////////////////////ESCENA
        //Creo/instancio mi escena y le agrego el layouts.
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        /////////////////////////////MANEJADORES DE EVENTOS:
        //Botón para crear el curso:
        buttonCrearCurso.setOnAction(e -> {
            //Recupero todos los datos escritos por el usuairo en los displays.
            String nombre = this.getDisplayNombreCurso().getText();
            String escuela = this.getDisplayEscuela().getText();
            int cantAlumnos = Integer.parseInt(this.getDisplayCantAlumnos().getText());
            String materia = this.getDisplayMateria().getText();
            int docenteID = this.getDocente().getID();
            CursoControlador cursoControlador = new CursoControlador();
            int id = cursoControlador.traerIdUltimoRegistro();
            //Instancio un curso y lo agrego a la BDD
            Curso curso = new Curso(id, nombre, cantAlumnos, escuela, materia, docenteID);
            cursoControlador.agregarUnRegistro(curso);
            //Cambiar de escena:
            Stage stage = (Stage) scene.getWindow();
            VerCursoScene verCursoScene = new VerCursoScene(curso);
            Scene scene1 = verCursoScene.crear();
            stage.setScene(scene1);
        });


        return scene;
    }

    public Button getButtonCrearCurso() {
        return buttonCrearCurso;
    }

    public void setButtonCrearCurso(Button buttonCrearCurso) {
        this.buttonCrearCurso = buttonCrearCurso;
    }

    public Button getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(Button buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public Label getLabelNombreCurso() {
        return labelNombreCurso;
    }

    public void setLabelNombreCurso(Label labelNombreCurso) {
        this.labelNombreCurso = labelNombreCurso;
    }

    public TextField getDisplayNombreCurso() {
        return displayNombreCurso;
    }

    public void setDisplayNombreCurso(TextField displayNombreCurso) {
        this.displayNombreCurso = displayNombreCurso;
    }

    public Label getLabelCantAlumnos() {
        return labelCantAlumnos;
    }

    public void setLabelCantAlumnos(Label labelCantAlumnos) {
        this.labelCantAlumnos = labelCantAlumnos;
    }

    public TextField getDisplayCantAlumnos() {
        return displayCantAlumnos;
    }

    public void setDisplayCantAlumnos(TextField displayCantAlumnos) {
        this.displayCantAlumnos = displayCantAlumnos;
    }

    public Label getLabelEscuela() {
        return labelEscuela;
    }

    public void setLabelEscuela(Label labelEscuela) {
        this.labelEscuela = labelEscuela;
    }

    public Label getLabelMateria() {
        return labelMateria;
    }

    public void setLabelMateria(Label labelMateria) {
        this.labelMateria = labelMateria;
    }

    public TextField getDisplayMateria() {
        return displayMateria;
    }

    public void setDisplayMateria(TextField displayMateria) {
        this.displayMateria = displayMateria;
    }

    public TextField getRama() {
        return rama;
    }

    public void setRama(TextField rama) {
        this.rama = rama;
    }

    public TextField getDisplayEscuela() {
        return displayEscuela;
    }

    public void setDisplayEscuela(TextField displayEscuela) {
        this.displayEscuela = displayEscuela;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}
