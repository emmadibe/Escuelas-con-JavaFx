package Scenes;

import ClasesPrincipales.Curso;
import Controladores.CursoControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.awt.*;

public class EditarCursoScene extends VBox implements Escenas
{
    private VBox vBox;
    private Label labelDatosOriginalesDelCurso;
    private Label labelNombreCurso;
    private TextField displayNombreCurso;
    private Label labelCantAlumnos;
    private Spinner<Integer> spinnerCantAlumnos;
    private Label labelEscuela;
    private TextField displayEscuela;
    private Label labelMateria;
    private TextField displayMateria;
    private Curso curso;
    private Button buttonEditar;
    private Button buttonVolver;
    private HBox hBoxBotones;

    public EditarCursoScene(Curso curso)
    {
        this.setCurso(curso);
    }
    @Override
    public Scene crear()
    {
        ////////////////////////////Mi LAYOUTS
        this.setvBox(new VBox(10)); //10px es la distancia entre cada nodo de mi layouts.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.
        ////////////////////////////NODOS
        //Labels
        this.setLabelDatosOriginalesDelCurso(MetodosGeneralesFx.crearLabel("Mi curso actual:", "pink", "10", "10"));
        this.getLabelDatosOriginalesDelCurso().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                        "Nombre: " + this.getCurso().getNombre() + "\n" +
                        "Escuela: " + this.getCurso().getEscuela() + "\n" +
                        "Materia: " + this.getCurso().getMateria() + "\n" +
                        "Ciclo Lectivo: " + this.getCurso().getCicloLectivo() + "\n" +
                        "Cantidad de alumnos: " +this.getCurso().getCantAlumnos());//Le agrego todo este texto a la etiqueta.

        this.setLabelNombreCurso(MetodosGeneralesFx.crearLabel("Nombre del curso"));
        this.setLabelEscuela(MetodosGeneralesFx.crearLabel("Escuela"));
        this.setLabelCantAlumnos(MetodosGeneralesFx.crearLabel("Cantidad de alumnos"));
        this.setLabelMateria(MetodosGeneralesFx.crearLabel("Materia"));
        //TextFields
        this.setDisplayNombreCurso(MetodosGeneralesFx.crearTextField(this.getCurso().getNombre())); //Al setearle una etiqueta inicial, si el usuario no quiere modificar todos los campos del curso le es más sencillo.
        this.setDisplayEscuela(MetodosGeneralesFx.crearTextField(this.getCurso().getEscuela()));
        this.setDisplayMateria(MetodosGeneralesFx.crearTextField(this.getCurso().getMateria()));
        //Spinner
        this.setSpinnerCantAlumnos(new Spinner<>(0, 50, this.getCurso().getCantAlumnos())); //Como parámetro a la instancia le paso el min, el max y el valor inicial.
        this.getSpinnerCantAlumnos().setEditable(true);  // permite entrada directa de números
        //Botones
        this.setButtonEditar(MetodosGeneralesFx.createButton("Editar curso", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        //Cajita de botones:
        this.sethBoxBotones(new HBox(10)); //10px es la distancia entre mis nodos dentro de la caja.
        this.gethBoxBotones().getChildren().addAll(this.getButtonEditar(), this.getButtonVolver());
        //Agrego mis nodos a mi layouts:
        this.getvBox().getChildren().addAll(this.getLabelDatosOriginalesDelCurso(), this.getLabelNombreCurso(), this.getDisplayNombreCurso(), this.getLabelEscuela(), this.getDisplayEscuela(), this.getLabelMateria(), this.getDisplayMateria(), this.getLabelCantAlumnos(), this.getSpinnerCantAlumnos(), this.gethBoxBotones());

        ////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi querida escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());

        ////////////////////////////MANEJADORES DE EVENTOS
        //Botón editar
        this.getButtonEditar().setOnAction(e -> {
            String nombre = this.getDisplayNombreCurso().getText();
            String escuela = this.getDisplayEscuela().getText();
            int cantAlumnos = this.getSpinnerCantAlumnos().getValue();
            String materia = this.getDisplayMateria().getText();
            Curso curso1 = new Curso(curso.getID(), nombre, cantAlumnos, escuela, materia, this.getCurso().getDocenteID(), this.getCurso().getCicloLectivo()); //Creo una nueva instancia de la clase Curso con las propiedades con los valores que deseo editar del registro. DocenteId y CicloLectivo son necesarios para volver a la escena verCurso
            CursoControlador cursoControlador = new CursoControlador();
            cursoControlador.editarRegistro(curso1); //Edito el registro.
            //Ahora debo volver a la escena VerCurso:
            VerCursoScene verCursoScene = new VerCursoScene(curso1);
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(verCursoScene.crear());
        });
        //Botón volver:
        this.getButtonVolver().setOnAction(e -> {
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(verCursoScene.crear());
        });
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
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

    public Spinner<Integer> getSpinnerCantAlumnos() {
        return spinnerCantAlumnos;
    }

    public void setSpinnerCantAlumnos(Spinner<Integer> spinnerCantAlumnos) {
        this.spinnerCantAlumnos = spinnerCantAlumnos;
    }

    public Label getLabelEscuela() {
        return labelEscuela;
    }

    public void setLabelEscuela(Label labelEscuela) {
        this.labelEscuela = labelEscuela;
    }

    public TextField getDisplayMateria() {
        return displayMateria;
    }

    public void setDisplayMateria(TextField displayMateria) {
        this.displayMateria = displayMateria;
    }

    public Label getLabelMateria() {
        return labelMateria;
    }

    public void setLabelMateria(Label labelMateria) {
        this.labelMateria = labelMateria;
    }

    public TextField getDisplayEscuela() {
        return displayEscuela;
    }

    public void setDisplayEscuela(TextField displayEscuela) {
        this.displayEscuela = displayEscuela;
    }


    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public Button getButtonEditar() {
        return buttonEditar;
    }

    public void setButtonEditar(Button buttonEditar) {
        this.buttonEditar = buttonEditar;
    }

    public HBox gethBoxBotones() {
        return hBoxBotones;
    }

    public void sethBoxBotones(HBox hBoxBotones) {
        this.hBoxBotones = hBoxBotones;
    }

    public Label getLabelDatosOriginalesDelCurso() {
        return labelDatosOriginalesDelCurso;
    }

    public void setLabelDatosOriginalesDelCurso(Label labelDatosOriginalesDelCurso) {
        this.labelDatosOriginalesDelCurso = labelDatosOriginalesDelCurso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
