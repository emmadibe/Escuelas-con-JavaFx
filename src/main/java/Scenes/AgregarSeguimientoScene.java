package Scenes;

import ClasesPrincipales.Curso;
import ClasesPrincipales.Seguimiento;
import Controladores.SeguimientoControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AgregarSeguimientoScene extends VBox implements Escenas
{

    private Curso curso;
    private Label labelCurso;
    private Label labelTitulo;
    private TextField displayTitulo;
    private Label labelCuerpo;
    private TextArea displayCuerpo;
    private Button buttonAgregarSeguimiento;
    private Button buttonVolver;
    private VBox vBoxlayouts;
    private HBox hboxBotones;

    public AgregarSeguimientoScene(Curso curso)
    {
        this.setCurso(curso);
    }
    @Override
    public Scene crear()
    {
        ////////////////////////LAYOUTS
        //Instancio layouts vbox:
        this.setvBoxlayouts(new VBox(10)); //10px es la distancia entre los nodos del layouts.
        this.getvBoxlayouts().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////NODOS
        //Buttons
        this.setButtonAgregarSeguimiento(MetodosGeneralesFx.createButton("Agregar seguimiento", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        this.setHboxBotones(new HBox(10)); //La caja con mis botones
        this.getHboxBotones().getChildren().addAll(this.getButtonAgregarSeguimiento(), this.getButtonVolver()); //agrego los botones a mi caja
        //Labels:
        this.setLabelCurso(MetodosGeneralesFx.crearLabel("Curso", "pink", "10", "10"));
        this.getLabelCurso().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                "Nombre: " + this.getCurso().getNombre() + "\n" +
                "Escuela: " + this.getCurso().getEscuela() + "\n" +
                "Materia: " + this.getCurso().getMateria() + "\n" +
                "Ciclo Lectivo: " + this.getCurso().getCicloLectivo() + "\n" +
                "Cantidad de alumnos: " +this.getCurso().getCantAlumnos()//Le agrego todo este texto a la etiqueta.
        );
        this.setLabelTitulo(MetodosGeneralesFx.crearLabel("Titulo"));
        this.setLabelCuerpo(MetodosGeneralesFx.crearLabel("Cuerpo"));
        //Displays:
        this.setDisplayTitulo(MetodosGeneralesFx.crearTextField());
        this.setDisplayCuerpo(MetodosGeneralesFx.createTextArea(""));

        ///Agrgeo los nodos a mi layouts:
        this.getvBoxlayouts().getChildren().addAll(this.getLabelCurso(), this.getLabelTitulo(), this.getDisplayTitulo(), this.getLabelCuerpo(), this.getDisplayCuerpo(), this.getHboxBotones());

        ////////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBoxlayouts(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////////MANEJO DE EVENTOS:
        //Volver
        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerSeguimientosScene verSeguimientosScene = new VerSeguimientosScene(this.getCurso());
            stage.setScene(verSeguimientosScene.crear());
        });
        //Agregar seguimiento
        this.getButtonAgregarSeguimiento().setOnAction(e -> {
            String titulo = this.getDisplayTitulo().getText();
            String cuerpo = this.getDisplayCuerpo().getText();
            Seguimiento seguimiento = new Seguimiento(titulo, cuerpo, this.getCurso().getID());
            SeguimientoControlador seguimientoControlador = new SeguimientoControlador();
            seguimientoControlador.agregarUnRegistro(seguimiento);
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

    public Button getButtonAgregarSeguimiento() {
        return buttonAgregarSeguimiento;
    }

    public void setButtonAgregarSeguimiento(Button buttonAgregarSeguimiento) {
        this.buttonAgregarSeguimiento = buttonAgregarSeguimiento;
    }

    public Button getButtonVolver() {
        return buttonVolver;
    }

    public void setButtonVolver(Button buttonVolver) {
        this.buttonVolver = buttonVolver;
    }

    public VBox getvBoxlayouts() {
        return vBoxlayouts;
    }

    public void setvBoxlayouts(VBox vBoxlayouts) {
        this.vBoxlayouts = vBoxlayouts;
    }

    public HBox getHboxBotones() {
        return hboxBotones;
    }

    public void setHboxBotones(HBox hboxBotones) {
        this.hboxBotones = hboxBotones;
    }

    public Label getLabelTitulo() {
        return labelTitulo;
    }

    public void setLabelTitulo(Label labelTitulo) {
        this.labelTitulo = labelTitulo;
    }

    public TextField getDisplayTitulo() {
        return displayTitulo;
    }

    public void setDisplayTitulo(TextField displayTitulo) {
        this.displayTitulo = displayTitulo;
    }

    public Label getLabelCuerpo() {
        return labelCuerpo;
    }

    public void setLabelCuerpo(Label labelCuerpo) {
        this.labelCuerpo = labelCuerpo;
    }

    public TextArea getDisplayCuerpo() {
        return displayCuerpo;
    }

    public void setDisplayCuerpo(TextArea displayCuerpo) {
        this.displayCuerpo = displayCuerpo;
    }
}
