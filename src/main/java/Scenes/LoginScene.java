package Scenes;

import ClasesPrincipales.Docente;
import Controladores.DocenteControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.animation.TranslateTransition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class LoginScene extends GridPane implements Escenas
{
    ////Los NODOS son atributos de mi clase-escena.
    protected TextField displayEmail; //Un display para el email. No está instanciado.
    protected PasswordField displayPassword; //Otro display para password. Este campo de texto, futura instancia de la clase passwordField, no muestra lo que el usuario escribe. No está instanciado aún.
    private Button buttonCrearUsuario;
    private Button buttonIniciarSesion;
    private Label labelErrorCredenciales;
    private Label labelLogOut;
    private Label labelEmail;
    private Label labelPassword;
    private GridPane gridPane;

    //Instancias necesarias para los eventos:
    DocenteControlador docenteControlador = new DocenteControlador();

    public Scene crear()
    {
        /////////////////////////////////////////////////////////////////////////LAYOUTS
        //Configuro mi LAYOUTS. Voy a elegir el layouts GridPane.
        this.setGridPane(new GridPane()); //Mi layouts. Ordena los elementos o nodos en forma de grilla o matriz. Tiene filas y columnas.
        gridPane.setPadding(new Insets(20)); //Seteo el padding del layout.
        gridPane.setHgap(10); //Seteo el Hgap del layouts.
        gridPane.setVgap(50); //Seteo el Vgap del layouts.

        ///////////////////////////////////////////////////////////////////////NODOS
        //Instancio mis atributos, le seteo propiedades y los agrego a mi layouts ya que son nodos o componentes:
        displayEmail = new TextField(); //Instancio.
        displayPassword = new PasswordField(); //Instancio.

        displayEmail.setPrefWidth(600); //Seteo ancho.
        displayPassword.setPrefWidth(600); //

        gridPane.add(displayEmail, 0, 0, 4, 4);
        gridPane.add(displayPassword, 0, 2, 4, 4);

        //Botón Inciar Sesión
        String tagIniciarSesion = "Iniciar sesión";
        this.setButtonIniciarSesion(MetodosGeneralesFx.createButton(tagIniciarSesion, 100));
        gridPane.add(this.getButtonIniciarSesion(), 3, 5);
        GridPane.setHalignment(this.getButtonIniciarSesion(), HPos.CENTER); // Alineación horizontal al centro
        GridPane.setValignment(this.getButtonIniciarSesion(), VPos.CENTER); // Alineación vertical al centro

        //Botón Crear Usuario:
        String tagCrearUsuario = "Crear nuevo usuario";
        this.setButtonCrearUsuario(MetodosGeneralesFx.createButton(tagCrearUsuario, 150));
        gridPane.add(this.getButtonCrearUsuario(), 3, 6); //Agrego el botón al layouts
        GridPane.setHalignment(this.getButtonCrearUsuario(), HPos.CENTER); // Alineación horizontal al centro
        GridPane.setValignment(this.getButtonCrearUsuario(), VPos.CENTER); // Alineación vertical al centro

        //Label error
        this.setLabelErrorCredenciales(MetodosGeneralesFx.crearLabel("errorCredenciales", "red", "10", "10"));

        //////////////////////////////////////////////////////////////////////////ESCENA
        //Creo/instancio mi ESCENA(Scene) y le agrego el layouts:
        Scene scene = new Scene(gridPane, 800, 600); //En el constructor de la escena paso el layout y el tamaño que deseo que tenga mi escena.
        // scene.setFill(Color.MAGENTA); //Seteo el color de fondo de mi escena.

        //Label LogOut
        this.setLabelLogOut(MetodosGeneralesFx.crearLabel("labelLogOut","yellow", "10", "10"));

        //Label Email
       // this.setLabelEmail(MetodosGeneralesFx.crearLabel("Email"));

        /////////////////////////////////////////////////////////////////////////MANEJADORES DE EVENTOS
        //Botón para crear un usuario
        buttonCrearUsuario.setOnAction(e -> { //Cuando haga click en el botón buttonCrearUsuario hacé lo siguiente;
            FormularioDeRegistroScene formularioDeRegistroScene = new FormularioDeRegistroScene(); //Instancio
            Scene newScene = formularioDeRegistroScene.crear(); //Creo la nueva escena. El formulario
            Stage stage = (Stage)scene.getWindow(); //Me traigo la VENTANA actual.
            stage.setScene(newScene); //A la VENTANA le seteo la ESCENA de formulario.
        });
        //Botón para Iniciar sesión
        buttonIniciarSesion.setOnAction(e -> {
            Docente docente = new Docente(displayEmail.getText(), displayPassword.getText());
            docente = docenteControlador.traerUnRegistro(docente);
            if(Objects.nonNull(docente)){
                Stage stage = (Stage)scene.getWindow();
                DocenteScene docenteScene = new DocenteScene(docente);
                Scene scene1 = docenteScene.crear();
                stage.setScene(scene1);
            }else{///////DEBERÍA SALTAR UN CARTEL DE ERROR (OTRO NODO) POR SI LÑAS CREDENCIALES NO SON VÁLIDAS.
                gridPane.add(labelErrorCredenciales, 3, 5); //Recién agrego mi nodo-label a mi layout cuando hay un error en credenciales. Para que aparezca recién ahí.
                // Creo la animaciónn
                MetodosGeneralesFx.animacionEtiqueta(labelErrorCredenciales, "Error de creedenciales.");
            }
        });

        return scene;

    }

    public PasswordField getDisplay2() {
        return displayPassword;
    }

    public void setDisplay2(PasswordField displayPassword) {
        this.displayPassword = displayPassword;
    }

    public TextField getDisplay1() {
        return displayEmail;
    }

    public void setDisplay1(TextField displayEmail) {
        this.displayEmail = displayEmail;
    }

    public TextField getDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(TextField displayEmail) {
        this.displayEmail = displayEmail;
    }

    public PasswordField getDisplayPassword() {
        return displayPassword;
    }

    public void setDisplayPassword(PasswordField displayPassword) {
        this.displayPassword = displayPassword;
    }

    public Button getButtonCrearUsuario() {
        return buttonCrearUsuario;
    }

    public void setButtonCrearUsuario(Button buttonCrearUsuario) {
        this.buttonCrearUsuario = buttonCrearUsuario;
    }

    public Button getButtonIniciarSesion() {
        return buttonIniciarSesion;
    }

    public void setButtonIniciarSesion(Button buttonIniciarSesion) {
        this.buttonIniciarSesion = buttonIniciarSesion;
    }

    public Label getLabelErrorCredenciales() {
        return labelErrorCredenciales;
    }

    public void setLabelErrorCredenciales(Label labelErrorCredenciales) {
        this.labelErrorCredenciales = labelErrorCredenciales;
    }

    public DocenteControlador getDocenteControlador() {
        return docenteControlador;
    }

    public void setDocenteControlador(DocenteControlador docenteControlador) {
        this.docenteControlador = docenteControlador;
    }

    public Label getLabelLogOut() {
        return labelLogOut;
    }

    public void setLabelLogOut(Label labelLogOut) {
        this.labelLogOut = labelLogOut;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public Label getLabelEmail() {
        return labelEmail;
    }

    public void setLabelEmail(Label labelEmail) {
        this.labelEmail = labelEmail;
    }

    public Label getLabelPassword() {
        return labelPassword;
    }

    public void setLabelPassword(Label labelPassword) {
        this.labelPassword = labelPassword;
    }
}