package Scenes;

import NodosControladores.MetodosGeneralesFx;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScene extends GridPane
{
    ////Los NODOS son atributos de mi clase-escena.
    protected TextField displayEmail; //Un display para el email. No está instanciado.
    protected PasswordField displayPassword; //Otro display para password. Este campo de texto, futura instancia de la clase passwordField, no muestra lo que el usuario escribe. No está instanciado aún.
    private Button buttonCrearUsuario;
    private Button buttonIniciarSesion;

    public LoginScene()
    {

    }

    public Scene crear()
    {
        /////////////////////////////LAYOUTS
        //Configuro mi LAYOUTS. Voy a elegir el layouts GridPane.
        GridPane gridPane = new GridPane(); //Mi layouts. Ordena los elementos o nodos en forma de grilla o matriz. Tiene filas y columnas.
        gridPane.setPadding(new Insets(20)); //Seteo el padding del layout.
        gridPane.setHgap(10); //Seteo el Hgap del layouts.
        gridPane.setVgap(50); //Seteo el Vgap del layouts.

        /////////////////////////////NODOS
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

        ////////////////////////////ESCENA
        //Creo/instancio mi ESCENA(Scene) y le agrego el layouts:
        Scene scene = new Scene(gridPane, 800, 600); //En el constructor de la escena paso el layout y el tamaño que deseo que tenga mi escena.
        // scene.setFill(Color.MAGENTA); //Seteo el color de fondo de mi escena.

        /////////////////////////MANEJADORES DE EVENTOS

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
}