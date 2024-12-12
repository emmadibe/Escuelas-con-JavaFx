package Scenes;

import ClasesPrincipales.*;
import Controladores.EstudianteControlador;
import Controladores.ExamenControlador;
import Controladores.TablaIntermediaEstudianteXCursoControlador;
import Controladores.TablaIntermediaEstudianteXExamenControlador;
import Excepciones.ArrayListVacioException;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.print.attribute.standard.Media;

public class AgregarExamenScene extends VBox implements Escenas
{
    private Label labelNombre;
    private TextField displayNombre;
    private Label labelNumeroExamen;
    private Spinner spinnerNumeroExamen;
    private Button buttonAgregar;
    private Button buttonVolver;
    private Curso curso;
    private VBox vBox;
    private HBox hBox;
    private Label examenExito;

    public AgregarExamenScene(Curso curso)
    {
        this.setCurso(curso);
    }

    @Override
    public Scene crear()
    {
        /////////////////////////////////////LAYOUTS
        //Uso un layouts vbox
        this.setvBox(new VBox(10));

        //////////////////////////////////////NODOS:
        //Labels, displays y spinners.
        this.setLabelNombre(MetodosGeneralesFx.crearLabel("Nombre"));
        this.setDisplayNombre(new TextField());
        this.setLabelNumeroExamen(MetodosGeneralesFx.crearLabel("Numero de examen"));
        this.setSpinnerNumeroExamen(new Spinner<>(1, 50, 1));//min, max, valor inicial.
        //Botones
        this.sethBox(new HBox(10)); //Caja que contendrá mis botones.
        this.setButtonAgregar(MetodosGeneralesFx.createButton("Agregar examen", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 100));
        this.gethBox().getChildren().addAll(this.getButtonAgregar(), this.getButtonVolver()); //Agrego mis botones a mi caja de botones
        //Agrego los nodos a mi layouts
        this.getvBox().getChildren().addAll(
            this.getLabelNombre(),
            this.getDisplayNombre(),
            this.getLabelNumeroExamen(),
            this.getSpinnerNumeroExamen(),
            this.gethBox()
        );
        //Label de agregar exito
        this.setExamenExito(MetodosGeneralesFx.crearLabel("examenExito", "green", "10", "10"));

        ///////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);

        //////////////////////////////////MANEJADORES DE EVENTOS
        this.getButtonAgregar().setOnAction(e -> {
            EstudianteControlador estudianteControlador = new EstudianteControlador(this.getCurso().getID());
            ExamenControlador examenControlador = new ExamenControlador();
            Examen examen = new Examen(this.getCurso().getID(), this.getDisplayNombre().getText(), (int) this.getSpinnerNumeroExamen().getValue());
            examenControlador.agregarUnRegistro(examen); //Agrego el nuevo registro/examen a la BDD.
            System.out.println("El id del curso es " + this.getCurso().getID());
            //Ahora voy con la tabla intermedia estudiantesXexamen
            int examenID = examenControlador.traerIdUltimoRegistro(); //Debo traerme el id del último examen agregado ala BDD para la Tabla intermedia.
            ArrayListGenerico<Estudiante> arrayListEstudiante = new ArrayListGenerico<>();
            arrayListEstudiante = estudianteControlador.traerTodos(); //Tengo a todos los estudiantes de mi curso d este examen.
            TablaIntermediaEstudianteXExamenControlador TIEXEC = new TablaIntermediaEstudianteXExamenControlador();
            TablaIntermediaEstudianteXExamen TIEXE;
            for(int i = 0; i < arrayListEstudiante.tamanio(); i++){  //En cada ciclo voy recorriendo a un estudiante guardado en el arrayList.
                Estudiante estudiante = arrayListEstudiante.retornarUnElementoPorPosicion(i);  //Guardo el estudiante en esa posición i del arrayList
                int estudianteID = estudiante.getID(); //Obtengo el ID del estudiante del bucle actual.
                TIEXE = new TablaIntermediaEstudianteXExamen(estudianteID, examenID, 0); //Instancio una TablaiNtermedia.
                TIEXEC.agregarUnRegistro(TIEXE); //Guardo la tabla intermedia en la base de datos.
            }
            System.out.println("Todo okey ");

            //Agrego el label de examenExito a mi layout y le agrego la animación:
            this.getExamenExito().setText("Examen agregado con éxito");
            this.getvBox().getChildren().add(this.getExamenExito());

            this.getExamenExito().setVisible(true); // Aseguro que ellabel es visible
            TranslateTransition animacion = new TranslateTransition(Duration.millis(500), this.getExamenExito()); //Creo la animación
            animacion.setFromY(0);
            animacion.setToY(-50);
            animacion.setCycleCount(2);
            animacion.setAutoReverse(true);
            animacion.play();
        });

        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            Scene scene1 = verCursoScene.crear();
            stage.setScene(scene1);
        });
        return scene;
    }

    public Label getLabelNombre() {
        return labelNombre;
    }

    public void setLabelNombre(Label labelNombre) {
        this.labelNombre = labelNombre;
    }

    public TextField getDisplayNombre() {
        return displayNombre;
    }

    public void setDisplayNombre(TextField displayNombre) {
        this.displayNombre = displayNombre;
    }

    public Label getLabelNumeroExamen() {
        return labelNumeroExamen;
    }

    public void setLabelNumeroExamen(Label numeroExamen) {
        this.labelNumeroExamen = numeroExamen;
    }

    public Spinner getSpinnerNumeroExamen() {
        return spinnerNumeroExamen;
    }

    public void setSpinnerNumeroExamen(Spinner spinnerNumeroExamen) {
        this.spinnerNumeroExamen = spinnerNumeroExamen;
    }

    public Button getButtonAgregar() {
        return buttonAgregar;
    }

    public void setButtonAgregar(Button buttonAgregar) {
        this.buttonAgregar = buttonAgregar;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
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

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public Label getExamenExito() {
        return examenExito;
    }

    public void setExamenExito(Label examenExito) {
        this.examenExito = examenExito;
    }
}
