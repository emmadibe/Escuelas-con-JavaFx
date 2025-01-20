package Scenes;

import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import ClasesPrincipales.TraerTodoPasandoNotasADiccionario;
import Controladores.CursoControlador;
import Controladores.DocenteControlador;
import Controladores.TraerTodoControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import Ventanas.AgregarAlumnoStage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class VerCursoScene extends VBox implements Escenas
{
    private Docente docente;
    private Curso curso;
    private VBox vBox;
    private Label labelCurso;
    private Label labelTablaTitulo;
    private Label labelCursoBorradoConExito;
    private Button buttonEditarCurso;
    private Button buttonEliminarCurso;
    private Button buttonAgregarAlumnos;
    private Button buttonAgregarExamen;
    private Button buttonVolver;
    private Button buttonVerExamenes;
    private Button buttonVerSeguimientos;
    private Button buttonAsistencia;
    private Alert alertEliminarCurso;
    private ButtonType buttonTypeAlertaEliminar;
    private ButtonType buttontypeAlertaCancelar;
    private TableView<TraerTodoPasandoNotasADiccionario> tableView = null; //tableView es un nodo más.

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
        //LabelTablaTitulo.
        this.setLabelTablaTitulo(MetodosGeneralesFx.crearLabel("Examenes"));
        this.getLabelTablaTitulo().setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");
        //LabelCurso
        this.setCursoLabel(MetodosGeneralesFx.crearLabel("cursoLabel", "pink", "10", "10"));
        this.getCursoLabel().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                "Nombre: " + this.getCurso().getNombre() + "\n" +
                "Escuela: " + this.getCurso().getEscuela() + "\n" +
                "Materia: " + this.getCurso().getMateria() + "\n" +
                "Ciclo Lectivo: " + this.getCurso().getCicloLectivo() + "\n" +
                "Cantidad de alumnos: " +this.getCurso().getCantAlumnos());//Le agrego todo este texto a la etiqueta.
        //Botones
        //instancio mis botones
        this.setButtonAgregarAlumnos(MetodosGeneralesFx.createButton("Agregar Alumnos", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        this.setButtonEditarCurso(MetodosGeneralesFx.createButton("Editar curso", 150));
        this.setButtonAgregarExamen(MetodosGeneralesFx.createButton("Agregar examen", 150));
        this.setButtonEliminarCurso(MetodosGeneralesFx.createButton("Eliminar curso", 150));
        this.setButtonVerExamenes(MetodosGeneralesFx.createButton("Ver exámenes", 150));
        this.setButtonVerSeguimientos(MetodosGeneralesFx.createButton("Ver seguimientos", 150));
        this.setButtonAsistencia(MetodosGeneralesFx.createButton("Asistencia", 150));
        //Creo un layouts HBox para mis botones y poder centrarlos.
        HBox buttonBox = new HBox(10); //10px es la distancia entre los elementos de mis box.
        buttonBox.getChildren().addAll(this.getButtonAgregarAlumnos(), this.getButtonAgregarExamen(), this.getButtonEditarCurso(), this.getButtonEliminarCurso(), this.getButtonVerExamenes(), this.getButtonVerSeguimientos(), this.getButtonAsistencia(), this.getButtonVolver());
        //////Alert
        String titulo = "Peligro";
        String cabecera = "¿Seguro que desea eliminar el curso " + this.getCurso().getNombre()+"?";
        String cuerpo = "Seleccione una opcion ";
        this.setAlertEliminarCurso(MetodosGeneralesFx.createAlerta(titulo, cabecera, cuerpo));
        //ButtonType de la alerta:
        this.setButtonTypeAlertaEliminar(MetodosGeneralesFx.createButtonType("Eliminar"));
        this.setButtontypeAlertaCancelar(MetodosGeneralesFx.createButtonType("Cancelar"));
        //Agrego mis buttontype a mi alerta:
        this.getAlertEliminarCurso().getButtonTypes().setAll(this.getButtonTypeAlertaEliminar(), this.getButtontypeAlertaCancelar());

        ///Ver todo
        if(TraerTodoControlador.esPosibleCrearTabla(this.getCurso().getID())){ //DEBO COMPROBAR QUE ME PUEDO TRAER TODOS LOS DATOS; es decir, debo corroborar que existan las tablas y que haya registros para ese cursoID así el programa no se rompe. De existir, seteo los valores de la tabla y la agrego a mi layouts.
            TraerTodoControlador traerTodoControlador = new TraerTodoControlador(this.getCurso().getID());
            this.tableView = traerTodoControlador.agregarDatosALaTabla(); //tableView es un nodo.
            //Agrego mis nodos a mi layouts
            vBox.getChildren().addAll(this.getCursoLabel(), this.getLabelTablaTitulo(), this.tableView, buttonBox);
        }else{
            //Agrego mis nodos a mi layouts sin la tabla
            vBox.getChildren().addAll(this.getCursoLabel(), buttonBox);
        }

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
        //Editar curso:
        this.getButtonEditarCurso().setOnAction(e -> {
            EditarCursoScene editarCursoScene = new EditarCursoScene(this.getCurso());
            Scene scene1 = editarCursoScene.crear();
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(scene1);
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
        //Borrar curso:
        this.getButtonEliminarCurso().setOnAction(e -> {
            this.getAlertEliminarCurso().showAndWait().ifPresent(response -> { //Ahora aclaro que acción realizar según que buttonType de la alerta apriete el usuario:
                if(response == buttonTypeAlertaEliminar){ //Si aprieto este boton, elimino el registro y vuelvo a la escena verTodosLoscursos.Scene
                    CursoControlador cursoControlador = new CursoControlador();
                    cursoControlador.eliminarRegistro(this.getCurso()); //Elimino el registro/curso de la BDD.
                    //Debo volver a la escena ver Todos los cursos:
                    Stage stage = (Stage) scene.getWindow();
                    VerTodosLosCursosScene verTodosLosCursosScene = new VerTodosLosCursosScene(this.getDocente(), stage);
                    stage.setScene(verTodosLosCursosScene.crear());
                    //Seteo la animación que dice que el curso se borró con éxito:
                    this.setLabelCursoBorradoConExito(MetodosGeneralesFx.crearLabel("Curso borrado con éxito"));
                    String texto = "El curso " + this.getCurso().getNombre() + "ha sido borrado con exito";
                    MetodosGeneralesFx.animacionEtiqueta(this.getLabelCursoBorradoConExito(), texto);
                    verTodosLosCursosScene.getvBox().getChildren().add(this.getLabelCursoBorradoConExito());
                }else{ //sino, cierro la alerta.
                    this.getAlertEliminarCurso().hide();
                }
            });
        });
        //Button ver seguimiento:
        this.getButtonVerSeguimientos().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerSeguimientosScene verSeguimientosScene = new VerSeguimientosScene(this.getCurso());
            stage.setScene(verSeguimientosScene.crear());
        });
        //Button asistencias:
        this.getButtonAsistencia().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerAsistenciasScene verAsistenciasScene = new VerAsistenciasScene(this.getCurso(),stage);
            stage.setScene(verAsistenciasScene.crear());
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

    public Alert getAlertEliminarCurso() {
        return alertEliminarCurso;
    }

    public void setAlertEliminarCurso(Alert alertEliminarCurso) {
        this.alertEliminarCurso = alertEliminarCurso;
    }

    public ButtonType getButtonTypeAlertaEliminar() {
        return buttonTypeAlertaEliminar;
    }

    public void setButtonTypeAlertaEliminar(ButtonType buttonTypeAlertaEliminar) {
        this.buttonTypeAlertaEliminar = buttonTypeAlertaEliminar;
    }

    public ButtonType getButtontypeAlertaCancelar() {
        return buttontypeAlertaCancelar;
    }

    public void setButtontypeAlertaCancelar(ButtonType buttontypeAlertaCancelar) {
        this.buttontypeAlertaCancelar = buttontypeAlertaCancelar;
    }

    public Label getLabelCursoBorradoConExito() {
        return labelCursoBorradoConExito;
    }

    public void setLabelCursoBorradoConExito(Label labelCursoBorradoConExito) {
        this.labelCursoBorradoConExito = labelCursoBorradoConExito;
    }

    public Label getLabelTablaTitulo() {
        return labelTablaTitulo;
    }

    public void setLabelTablaTitulo(Label labelTablaTitulo) {
        this.labelTablaTitulo = labelTablaTitulo;
    }

    public Button getButtonVerSeguimientos() {
        return buttonVerSeguimientos;
    }

    public void setButtonVerSeguimientos(Button buttonVerSeguimientos) {
        this.buttonVerSeguimientos = buttonVerSeguimientos;
    }

    public Button getButtonAsistencia() {
        return buttonAsistencia;
    }

    public void setButtonAsistencia(Button buttonAsistencia) {
        this.buttonAsistencia = buttonAsistencia;
    }

    public TableView<TraerTodoPasandoNotasADiccionario> getTableView() {
        return tableView;
    }

    public void setTableView(TableView<TraerTodoPasandoNotasADiccionario> tableView) {
        this.tableView = tableView;
    }
}
