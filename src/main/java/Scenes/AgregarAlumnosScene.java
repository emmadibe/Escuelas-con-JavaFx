package Scenes;

import ClasesPrincipales.*;
import Controladores.CursoControlador;
import Controladores.EstudianteControlador;
import Controladores.TablaIntermediaEstudianteXCursoControlador;
import Controladores.TablaIntermediaEstudianteXExamenControlador;
import Interfaces.Escenas;
import Modelos.ExamenModelo;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AgregarAlumnosScene extends VBox implements Escenas
{
    private Label labelNombre;
    private TextField displayNombre;
    private Label labelApellido;
    private TextField displayApellido;
    private Label labelDNI;
    private TextField displayDNI;
    private Label labelEmail;
    private TextField displayEmail;
    private Label labelEdad;
    private Spinner spinnerEdad;
    private Button buttonAgregar;
    private Button buttonVolver;
    private VBox vBox;
    private HBox hBox;
    private Curso curso;

    public AgregarAlumnosScene(Curso curso)
    {
        this.setCurso(curso);
    }

    @Override
    public Scene crear()
    {
        ////////////////////////////////LAYOUTS
        //Uso un layouts VBox
        this.setvBox(new VBox(10)); //Instancio mi layouts VBox.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        //////////////////////////////////NODOS
        this.setLabelNombre(MetodosGeneralesFx.crearLabel("Nombre"));
        this.setDisplayNombre(new TextField());
        this.setLabelApellido(MetodosGeneralesFx.crearLabel("Apellido"));
        this.setDisplayApellido(new TextField());
        this.setLabelDNI(MetodosGeneralesFx.crearLabel("DNI"));
        this.setDisplayDNI(new TextField());
        this.setLabelEmail(MetodosGeneralesFx.crearLabel("Email"));
        this.setDisplayEmail(new TextField());
        this.setLabelEdad(MetodosGeneralesFx.crearLabel("Edad"));
        this.setSpinnerEdad(new Spinner<>(0, 120, 18));  // (min, max, valor inicial)
        this.getSpinnerEdad().setEditable(true);  // permite entrada directa de números

        //Botones
        this.setButtonAgregar(MetodosGeneralesFx.createButton("Agregar", 100));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 100));
        //Creo un layouts HBox para mis botones y poder centrarlos.
        this.sethBox(new HBox(10)); //10px de distancia entre los nodos.
        this.gethBox().getChildren().addAll(this.getButtonAgregar(), this.getButtonVolver());
        //Agrego mis nodos a mi layouts:
        this.getvBox().getChildren().addAll(
                this.getLabelNombre(),
                this.getDisplayNombre(),
                this.getLabelApellido(),
                this.getDisplayApellido(),
                this.getLabelDNI(),
                this.getDisplayDNI(),
                this.getLabelEmail(),
                this.getDisplayEmail(),
                this.getLabelEdad(),
                this.getSpinnerEdad(),
                this.gethBox()
        );

        ///////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////MANEJADORES DE EVENTOS
        //Botón agregar alumnos.
        this.getButtonAgregar().setOnAction(e -> {
            EstudianteControlador estudianteControlador = new EstudianteControlador();
            //Recupero los datos que el usuario ingresó en los displays:
            String nombre = this.getDisplayNombre().getText();
            String apellido = this.getDisplayApellido().getText();
            String dni = this.getDisplayDNI().getText();
            String email = this.getDisplayEmail().getText();
            int edad = (int) this.getSpinnerEdad().getValue();
            //Instancio un Estudiante con los valores ingresados por el usuario en los displays como parámetros para el constructor.
            Estudiante estudiante = new Estudiante(nombre, apellido, dni, email, edad);
            //Agrego el estudiante instanciado a la base de datos.
            estudianteControlador.agregarUnRegistro(estudiante);
            //Tengo que agregar, además, el registro de la Tabla intermediante de estudiantes x curso:
            int estudianteID = estudianteControlador.traerIdUltimoRegistro();
            TablaIntermediaEstudianteXCurso TIEXC = new TablaIntermediaEstudianteXCurso(this.getCurso().getID(), estudianteID);
            TablaIntermediaEstudianteXCursoControlador TIEXControl = new TablaIntermediaEstudianteXCursoControlador();
            TIEXControl.agregarUnRegistro(TIEXC);
            //Debo corroborar que no haya exámenes en el curso. Si los hay, debo crear un registro en la tabla intermedia estudiantes x examen. Sino, el estudiante no aparecerá en la Table View de la escena VerCurso:
            if(CursoControlador.existenExamenesEnElCurso(this.getCurso().getID())){ //Si hay exámenes en el curso...
                //Debo traermelos id de todos los exámenes del curso:
                ArrayListGenerico<Integer> arrayListEnteros = ExamenModelo.traerTodosLosIExamenesIDDeUnCurso(this.getCurso().getID()); //Tengo todos los id de todos los exámenes pertenecientes a este curso.
                //Ahora, debo crear los registros de la tabla intermedia estudiantes x examen. Por cada examenID que haya, un registro
                for(int i = 0; i < arrayListEnteros.tamanio(); i++){
                    int examenID = arrayListEnteros.retornarUnElementoPorPosicion(i);
                    TablaIntermediaEstudianteXExamen TIEXE = new TablaIntermediaEstudianteXExamen(estudianteID, examenID, 0); //EN cada bucle, una instancia
                    TablaIntermediaEstudianteXExamenControlador TIEXEC = new TablaIntermediaEstudianteXExamenControlador();
                    TIEXEC.agregarUnRegistro(TIEXE); //Agrego la tabla intermedia a la bdd
                }
            }
            //Limpio todos los display:
            MetodosGeneralesFx.limpiarInputs(this.getvBox());
        });
        //Botón volver:
        this.getButtonVolver().setOnAction(e -> {
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(verCursoScene.crear());
        });
        return scene;
    }

    public TextField getDisplayNombre() {
        return displayNombre;
    }

    public void setDisplayNombre(TextField displayNombre) {
        this.displayNombre = displayNombre;
    }

    public TextField getDisplayApellido() {
        return displayApellido;
    }

    public void setDisplayApellido(TextField displayApellido) {
        this.displayApellido = displayApellido;
    }

    public TextField getDisplayDNI() {
        return displayDNI;
    }

    public void setDisplayDNI(TextField displayDNI) {
        this.displayDNI = displayDNI;
    }

    public Button getButtonAgregar() {
        return buttonAgregar;
    }

    public void setButtonAgregar(Button buttonAgregar) {
        this.buttonAgregar = buttonAgregar;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public Spinner getSpinnerEdad() {
        return spinnerEdad;
    }

    public void setSpinnerEdad(Spinner spinnerEdad) {
        this.spinnerEdad = spinnerEdad;
    }

    public TextField getDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(TextField displayEmail) {
        this.displayEmail = displayEmail;
    }

    public Label getLabelNombre() {
        return labelNombre;
    }

    public void setLabelNombre(Label labelNombre) {
        this.labelNombre = labelNombre;
    }

    public Label getLabelApellido() {
        return labelApellido;
    }

    public void setLabelApellido(Label labelApellido) {
        this.labelApellido = labelApellido;
    }

    public Label getLabelDNI() {
        return labelDNI;
    }

    public void setLabelDNI(Label labelDNI) {
        this.labelDNI = labelDNI;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
    }

    public Label getLabelEdad() {
        return labelEdad;
    }

    public void setLabelEdad(Label labelEdad) {
        this.labelEdad = labelEdad;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
