package Scenes;

import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import ClasesPrincipales.TraerTodo;
import ClasesPrincipales.TraerTodoPasandoNotasADiccionario;
import Controladores.DocenteControlador;
import Controladores.TraerTodoControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import Ventanas.AgregarAlumnoStage;
import Ventanas.AgregarNotaStage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VerCursoScene extends VBox implements Escenas
{
    private Docente docente;
    private Curso curso;
    private VBox vBox;
    private Label labelCurso;
    private Button buttonEditarCurso;
    private Button buttonEliminarCurso;
    private Button buttonAgregarAlumnos;
    private Button buttonAgregarExamen;
    private Button buttonVolver;
    private Button buttonVerExamenes;
    private TableView<TraerTodoPasandoNotasADiccionario> tableView; //tableView es un nodo más.

    public VerCursoScene(Curso curso)
    {
        this.setCurso(curso);
        //Seteamos ya el valor de docente, será necesario:
        DocenteControlador docenteControlador = new DocenteControlador();
        this.setDocente(docenteControlador.traerRegistroAPartirDeID(curso.getDocenteID()));
    }

    @Override
    public Scene crear()
    {
        ///////////////////////////////////////////LAYOUTS
        //Uso un layouts VBox
        this.setvBox(new VBox(10)); //Instancio mi layouts Vbox. 10px de distancia entre los elementos.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////////////////////////NODOS
        //LabelCurso
        this.setCursoLabel(MetodosGeneralesFx.crearLabel("cursoLabel", "pink", "10", "10"));
        this.getCursoLabel().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                "Nombre: " + this.getCurso().getNombre() + "\n" +
                "Escuela: " + this.getCurso().getEscuela() + "\n" +
                "Materia: " + this.getCurso().getMateria() + "\n" +
                "Cantidad de alumnos: " +this.getCurso().getCantAlumnos());//Le agrego todo este texto a la etiqueta.
        //Botones
        //instancio mis botones
        this.setButtonAgregarAlumnos(MetodosGeneralesFx.createButton("Agregar Alumnos", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        this.setButtonEditarCurso(MetodosGeneralesFx.createButton("Editar curso", 150));
        this.setButtonAgregarExamen(MetodosGeneralesFx.createButton("Agregar examen", 150));
        this.setButtonEliminarCurso(MetodosGeneralesFx.createButton("Eliminar curso", 150));
        this.setButtonVerExamenes(MetodosGeneralesFx.createButton("Ver exámenes", 150));
        //Creo un layouts HBox para mis botones y poder centrarlos.
        HBox buttonBox = new HBox(10); //10px es la distancia entre los elementos de mis box.
        buttonBox.getChildren().addAll(this.getButtonAgregarAlumnos(), this.getButtonAgregarExamen(), this.getButtonEditarCurso(), this.getButtonEliminarCurso(), this.getButtonVerExamenes(), this.getButtonVolver());

        ///Ver todo
        TraerTodoControlador traerTodoControlador = new TraerTodoControlador(this.getCurso().getID());
        this.tableView = traerTodoControlador.agregarDatosALaTabla(); //tableView es un nodo.

        //Agrego mis nodos a mi layouts
        vBox.getChildren().addAll(this.getCursoLabel(), this.tableView, buttonBox);

        ////////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        /////////////////////////////////////////MANEJADORES DE EVENTOS:
        //Agregar alumnos:
        this.getButtonAgregarAlumnos().setOnAction(e -> {
            Stage stage = new Stage();
            AgregarAlumnoStage agregarAlumnoStage = new AgregarAlumnoStage(this.getCurso());
            try {
                agregarAlumnoStage.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        //Volver:
        this.getButtonVolver().setOnAction(e -> {
            VerTodosLosCursosScene verTodosLosCursosScene = new VerTodosLosCursosScene(this.getDocente(),(Stage) scene.getWindow());
            Stage stage = (Stage) scene.getWindow();
            Scene scene1 = verTodosLosCursosScene.crear();
            stage.setScene(scene1);
        });
        //Agregar examen:
        this.getButtonAgregarExamen().setOnAction(e -> {
            AgregarExamenScene agregarExamenScene = new AgregarExamenScene(this.getCurso());
            Stage stage = (Stage) scene.getWindow();
            Scene scene1 = agregarExamenScene.crear();
            stage.setScene(scene1);
        });
        //Ver todos los exámenes del curso:
        this.getButtonVerExamenes().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerTodosLosExamenesScene verTodosLosExamenesScene = new VerTodosLosExamenesScene(stage, this.getCurso());
            Scene scene1 = verTodosLosExamenesScene.crear();
            stage.setScene(scene1);
        });
        return scene;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Label getCursoLabel() {
        return labelCurso;
    }

    public void setCursoLabel(Label cursoLabel) {
        this.labelCurso = cursoLabel;
    }

    public Label getLabelCurso() {
        return labelCurso;
    }

    public void setLabelCurso(Label labelCurso) {
        this.labelCurso = labelCurso;
    }

    public Button getButtonEditarCurso() {
        return buttonEditarCurso;
    }

    public void setButtonEditarCurso(Button buttonEditarCurso) {
        this.buttonEditarCurso = buttonEditarCurso;
    }

    public Button getButtonAgregarExamen() {
        return buttonAgregarExamen;
    }

    public void setButtonAgregarExamen(Button buttonAgregarExamen) {
        this.buttonAgregarExamen = buttonAgregarExamen;
    }

    public Button getButtonAgregarAlumnos() {
        return buttonAgregarAlumnos;
    }

    public void setButtonAgregarAlumnos(Button buttonAgregarAlumnos) {
        this.buttonAgregarAlumnos = buttonAgregarAlumnos;
    }

    public Button getButtonEliminarCurso() {
        return buttonEliminarCurso;
    }

    public void setButtonEliminarCurso(Button buttonEliminarCurso) {
        this.buttonEliminarCurso = buttonEliminarCurso;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Button getButtonVerExamenes() {
        return buttonVerExamenes;
    }

    public void setButtonVerExamenes(Button buttonVerExamenes) {
        this.buttonVerExamenes = buttonVerExamenes;
    }
}
