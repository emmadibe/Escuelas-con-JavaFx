package Scenes;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import ClasesPrincipales.FuncionesGenerales;
import Controladores.CursoControlador;
import Excepciones.ArrayListVacioException;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Bounds;

public class VerTodosLosCursosScene extends VBox implements Escenas
{
    private Docente docente;
    private VBox vBox;
    private Label labelDocente;
    private VBox vBoxBotones;
    private Button buttonVolver;
    private ContextMenu contextMenuCicloLectivo;
    private HBox hBox;
    private Stage stage;
    private Button buttonAbrirContextMenu;
    private int CicloLectivo;

    public VerTodosLosCursosScene(Docente docente, Stage stage)
    {
        this.setDocente(docente);
        this.setStage(stage);
        this.setCicloLectivo(FuncionesGenerales.obtenerAnioActual()); //Por defecto, seteo el anio actual como ciclo lectivo. Después, el usuario lo puede cambiar.
    }

    public VerTodosLosCursosScene(Docente docente, Stage stage, int cicloLectivo) //Uso este controlador para recargar la escena si el usuario quiere cambiar de ciclo lectivo.
    {
        this.setCicloLectivo(cicloLectivo);
        this.setStage(stage);
        this.setDocente(docente);
    }

    @Override
    public Scene crear()
    {
        /////////////////////////////////LAYOUTS:
        //Instancio layout Vbox
        this.setvBox(new VBox(10)); //10px es la distancia entre los nodos del layout
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

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
        ArrayListGenerico<Curso> arrayListGenerico = cursoControlador.traerTodosPorCicloLectivo(this.getCicloLectivo()); //Me traigo, en forma de arrayList, todos los cursos del docente del ciclo lectivo seleccionado (el actual, por defecto).
        this.setvBoxBotones(arrayListGenerico.transformarTEnBotones(this.getStage())); //En esta caja VBox, tengo botones. Cada botón es un curso. La lógica está en arrayListGenerico.

        //Button volver
        this.setButtonVolver(MetodosGeneralesFx.createButton("Volver", 200));
        this.getButtonVolver().setAlignment(Pos.BOTTOM_CENTER);
        //ContextMenu Cambiar ciclo lectivo
        this.setContextMenuCicloLectivo(MetodosGeneralesFx.asignarMenues(this.getDocente().getID())); //Instancio mi nodo ContextMenu. En el método asignarMenues de MetodosGeneralesFx creo una instancia de ContextMenu y le asigno ya los items. Cada cicloLectivo es un item.
        this.setButtonAbrirContextMenu(MetodosGeneralesFx.createButton("Cambiar de ciclo lectivo", 200)); //Botón asociado a abrir el context menu.

        //Agrego los botones volver y el boton que abre el COntextMenu ciclos lectivos a la caja hbox:
        this.sethBox(new HBox(10)); //10px es la distancia entre los nodos de mi hbox.
        this.gethBox().getChildren().addAll(this.getButtonVolver(), this.getButtonAbrirContextMenu());

        ////Agrego los nodos  mi layouts:
        this.getvBox().getChildren().addAll(this.getLabelDocente(), this.getvBoxBotones(), this.gethBox());

        //////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////////MANEJO DE EVENTOS:
        //Button volver:
        this.getButtonVolver().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            DocenteScene docenteScene = new DocenteScene(this.getDocente());
            stage.setScene(docenteScene.crear());
        });
        // Asociar el menú contextual con el HBox
        this.getButtonAbrirContextMenu().setOnAction(e -> {
            // Para mostrar el ContextMenu justo al lado del botón en lugar de en una posición fija en la pantalla, necesito sí o sí obtener las coordenadas del botón en la escena. Para ello utilizo localToScreen para convertir las coordenadas locales del botón a coordenadas de pantalla.
            // Primero, obtenemos las coordenadas del botón en la escena
            Bounds bounds = this.getButtonAbrirContextMenu().localToScreen(this.getButtonAbrirContextMenu().getBoundsInLocal());
            // Mostramos el ContextMenu justo al lado del botón
            this.getContextMenuCicloLectivo().show(this.getButtonAbrirContextMenu(), bounds.getMinX(), bounds.getMaxY());
        });

        //Cambiar de cicloLectivo:
        for (MenuItem menuItem : this.contextMenuCicloLectivo.getItems()) { // Recorre todos los MenuItem dentro del nodo ContextMenu. Debo hacer esto para atrapar el item seleccionado por el usuario.
            menuItem.setOnAction(e -> {
                int cicloLectivo = Integer.parseInt(menuItem.getText()); //Atrapo el item selecciondo por el usuario en el nodo contextMenu.
               //Ahora debo recargar la escena, para que el valor de la propiedad cicloLectivo sea el que el usuario eligió:
                VerTodosLosCursosScene verTodosLosCursosScene = new VerTodosLosCursosScene(this.getDocente(), this.getStage(), this.getCicloLectivo());
                Stage stage1 = this.getStage();
                stage1.setScene(verTodosLosCursosScene.crear());
            });
        }

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

    public HBox gethBox() {
        return hBox;
    }

    public void sethBox(HBox hBox) {
        this.hBox = hBox;
    }

    public ContextMenu getContextMenuCicloLectivo() {
        return contextMenuCicloLectivo;
    }

    public void setContextMenuCicloLectivo(ContextMenu contextMenuCicloLectivo) {
        this.contextMenuCicloLectivo = contextMenuCicloLectivo;
    }

    public Button getButtonAbrirContextMenu() {
        return buttonAbrirContextMenu;
    }

    public void setButtonAbrirContextMenu(Button buttonAbrirContextMenu) {
        this.buttonAbrirContextMenu = buttonAbrirContextMenu;
    }

    public int getCicloLectivo() {
        return CicloLectivo;
    }

    public void setCicloLectivo(int cicloLectivo) {
        CicloLectivo = cicloLectivo;
    }
}
