package Scenes;

import ClasesPrincipales.*;
import Controladores.ClaseControlador;
import Controladores.TablaIntermediaAsistenciaEstudiantesXClaseControlador;
import Controladores.TraerTodoAsistenciaControlador;
import Interfaces.Escenas;
import Modelos.TraerTodoAsistenciaModelo;
import NodosControladores.MetodosGeneralesFx;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VerAsistenciasScene extends VBox implements Escenas
{
    private Curso curso;
    private Label labelCurso;
    private Label labelTablaTitulo;
    private Label labelClaseAgregadaConExito;
    private Button buttonVolver;
    private Button buttonAgregarClase;
    private VBox vBox;
    private HBox hboxCajaBotones;
    private Stage stage;
    private TableView<TraerTodoAsistenciaPasandoFechaADiccionario> tableViewTraerTodoAsistencia;

    public VerAsistenciasScene(Curso curso, Stage stage)
    {
        this.setCurso(curso);
        this.setStage(stage);
    }

    @Override
    public Scene crear()
    {
        ////////////////////LAYOUTS
        //Uso un layouts VBox
        this.setvBox(new VBox(10)); //Instancio mi layouts Vbox. 10px de distancia entre los elementos.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////NODOS
        //Label clase creada con exito
        this.setLabelClaseAgregadaConExito(MetodosGeneralesFx.crearLabel("claseExito"));
        String textoLabel = "Clase creada con éxito!";

        //LabelCurso
        this.setLabelCurso(MetodosGeneralesFx.crearLabel("cursoLabel", "pink", "10", "10"));
        this.getLabelCurso().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                        "Nombre: " + this.getCurso().getNombre() + "\n" +
                        "Escuela: " + this.getCurso().getEscuela() + "\n" +
                        "Materia: " + this.getCurso().getMateria() + "\n" +
                        "Ciclo Lectivo: " + this.getCurso().getCicloLectivo() + "\n" +
                        "Cantidad de alumnos: " +this.getCurso().getCantAlumnos());//Le agrego todo este texto a la etiqueta.
        //Botones
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        this.setButtonAgregarClase(MetodosGeneralesFx.createButton("Agregar clase", 200));
        this.setHboxCajaBotones(new HBox(10));
        this.getHboxCajaBotones().getChildren().addAll(this.getButtonAgregarClase(), this.getButtonVolver());

        //Crear la tabla de asistencia:
        //La idea es que haya una columna, llamada nombre alunos, en donde cada fila sea un nombre de un alumno. El resto de las columnas deben ser creadas dinámicamente. Cada columna debe tener, como nombre, una fecha, la cual corresponde a una clase. Cada fila de cada columna/fecha tendrá botoncitos. Si lo pulso, se pone verde, lo cual indica que el alumno asistió; si lo pongo rojo, es que faltó.
        //LabelTablaTitulo
        this.setLabelTablaTitulo(MetodosGeneralesFx.crearLabel("Asistencia"));
        this.getLabelTablaTitulo().setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

        //Traer todas las asistencias:
        if(TraerTodoAsistenciaControlador.esPosibleCrearTabla(this.getCurso().getID())){ //Debo corroborar que se pueda mostrar la tabla
            TraerTodoAsistenciaControlador TTAC = new TraerTodoAsistenciaControlador(this.getCurso(), this.getStage());
            this.setTableViewTraerTodoAsistencia(TTAC.agregarDatosALaTabla());
            //Agrego los nodos a mi layputs con la TablaView:
            this.getvBox().getChildren().addAll(this.getLabelCurso(), this.getLabelTablaTitulo(), this.tableViewTraerTodoAsistencia, this.getHboxCajaBotones(), this.getLabelClaseAgregadaConExito() );
        }else{
            //Agrego los nodos a mi layputs sin la TablaView:
            this.getvBox().getChildren().addAll(this.getLabelCurso(), this.getLabelTablaTitulo(), this.getHboxCajaBotones(), this.getLabelClaseAgregadaConExito() );
        }

        ////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ///////////////////////////////MANEJADORES DE EVENTOS
        //Button volver:
        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            stage.setScene(verCursoScene.crear());
        });
        //Button agregar clase:
        this.getButtonAgregarClase().setOnAction(e ->{
            //Creo una instancia de clase, con la fecha actual, y lo agrego a la BDD
            LocalDate fecha = LocalDate.now();
            Clase clase = new Clase(curso.getID(), fecha);
            ClaseControlador claseControlador = new ClaseControlador();
            claseControlador.agregarUnRegistro(clase); //Agregué un nuevo registro a la BDD.
            //Ahora debo crear los registros en la tabla intermedia:
            TablaIntermediaAsistenciaEstudiantesXClaseControlador TIAEXCC = new TablaIntermediaAsistenciaEstudiantesXClaseControlador();
            int claseID = claseControlador.traerIdUltimoRegistro(); //Necesito traerme el id de la clase que acabo de crear.
            TIAEXCC.cargarTodo(this.getCurso(), claseID); //Listo, ya tengo a todos los registros de la tabla intermedia creados
            // Refrezco la escena:
            VerAsistenciasScene VAS = new VerAsistenciasScene(this.getCurso(), this.getStage());
            Scene scene1 = VAS.crear();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(scene1);
            //Animación Label
            MetodosGeneralesFx.animacionEtiqueta(this.getLabelClaseAgregadaConExito(), textoLabel);
        });
        return scene;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Label getLabelCurso() {
        return labelCurso;
    }

    public void setLabelCurso(Label labelCurso) {
        this.labelCurso = labelCurso;
    }



    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Label getLabelTablaTitulo() {
        return labelTablaTitulo;
    }

    public Button getButtonAgregarClase() {
        return buttonAgregarClase;
    }

    public void setButtonAgregarClase(Button buttonAgregarClase) {
        this.buttonAgregarClase = buttonAgregarClase;
    }

    public void setLabelTablaTitulo(Label labelTablaTitulo) {
        this.labelTablaTitulo = labelTablaTitulo;
    }

    public HBox getHboxCajaBotones() {
        return hboxCajaBotones;
    }

    public void setHboxCajaBotones(HBox hboxCajaBotones) {
        this.hboxCajaBotones = hboxCajaBotones;
    }

    public TableView<TraerTodoAsistenciaPasandoFechaADiccionario> getTableViewTraerTodoAsistencia() {
        return tableViewTraerTodoAsistencia;
    }

    public void setTableViewTraerTodoAsistencia(TableView<TraerTodoAsistenciaPasandoFechaADiccionario> tableViewTraerTodoAsistencia) {
        this.tableViewTraerTodoAsistencia = tableViewTraerTodoAsistencia;
    }

    public Label getLabelClaseAgregadaConExito() {
        return labelClaseAgregadaConExito;
    }

    public void setLabelClaseAgregadaConExito(Label labelClaseAgregadaConExito) {
        this.labelClaseAgregadaConExito = labelClaseAgregadaConExito;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
