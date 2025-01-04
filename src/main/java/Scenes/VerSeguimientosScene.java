package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Seguimiento;
import Controladores.SeguimientoControlador;
import Controladores.TraerTodosLosSeguimientosControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;

public class VerSeguimientosScene extends VBox implements Escenas
{
    private Curso curso;
    private Label labelCurso;
    private VBox vbox;
    private HBox hBox;
    private Button buttonAgregarSeguimiento;
    private Button buttonVolver;
    private TableView<Seguimiento> tableViewSeguimiento;

    public VerSeguimientosScene(Curso curso)
    {
        this.setCurso(curso);
    }
    @Override
    public Scene crear()
    {
        ////////////////////////LAYOUTS
        this.setVbox(new VBox(10));
        this.getVbox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////////NODOS
        //Label
        //LabelCurso
        this.setLabelCurso(MetodosGeneralesFx.crearLabel("cursoLabel", "pink", "10", "10"));
        this.getLabelCurso().setText(
                "CursoID: " + this.getCurso().getID() + "\n" +
                        "Nombre: " + this.getCurso().getNombre() + "\n" +
                        "Escuela: " + this.getCurso().getEscuela() + "\n" +
                        "Materia: " + this.getCurso().getMateria() + "\n" +
                        "Ciclo Lectivo: " + this.getCurso().getCicloLectivo() + "\n" +
                        "Cantidad de alumnos: " +this.getCurso().getCantAlumnos());//Le agrego todo este texto a la etiqueta.

        //Buttons
        this.setButtonAgregarSeguimiento(MetodosGeneralesFx.createButton("Agregar un seguimiento", 150));
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 150));
        this.sethBox(new HBox(10)); //10px, distancia entre nodos del layouts
        //Agrego los botones a la caja hbox
        this.gethBox().getChildren().addAll(this.getButtonAgregarSeguimiento(), this.getButtonVolver());

        //Traerme todos los seguimientos y agregar los nodos a mi layouts.
        TraerTodosLosSeguimientosControlador TTSC = new TraerTodosLosSeguimientosControlador(this.getCurso());
        if(TTSC.esPosibleCrearTabla()){ //Corrobora que sea posible crear la tabla. Para ello, debe existir la tabla Seguimientos en la base de datos y debe haber, por lo menos, un registro en ella para mi cursoID.
            this.setTableViewSeguimiento(TTSC.agregarDatosALaTabla()); //Ya tengo la tabla con todos los registros. Luego se la debo setear a mi nodo Vbox.
            this.getVbox().getChildren().addAll(this.getLabelCurso(), this.getTableViewSeguimiento(), this.gethBox()); //Agrgeo todos los nodos a mi layouts.
        }else{
            this.getVbox().getChildren().addAll(this.getLabelCurso(), this.gethBox()); //Si no es posible crear la tabla, agrego el resto de los nodos a mi layouts.
        }

        /////////////////////////////////////////////ESCENA
        Scene scene = new Scene(this.getVbox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////////MANEJO DE EVENTOS:
        //Agregar un seguimiento
        this.getButtonAgregarSeguimiento().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            AgregarSeguimientoScene agregarSeguimientoScene = new AgregarSeguimientoScene(this.getCurso());
            stage.setScene(agregarSeguimientoScene.crear());
        });
        //Volver
        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            VerCursoScene verCursoScene = new VerCursoScene(this.getCurso());
            stage.setScene(verCursoScene.crear());
        });
        return scene;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public TableView<Seguimiento> getTableViewSeguimiento() {
        return tableViewSeguimiento;
    }

    public void setTableViewSeguimiento(TableView<Seguimiento> tableViewSeguimiento) {
        this.tableViewSeguimiento = tableViewSeguimiento;
    }
}
