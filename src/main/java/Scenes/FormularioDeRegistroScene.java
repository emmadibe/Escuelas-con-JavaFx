package Scenes;

import ClasesPrincipales.Docente;
import Controladores.DocenteControlador;
import ENUMS.Rama;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import Ventanas.Inicio;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FormularioDeRegistroScene extends VBox implements Escenas
{
    private TextField displayEmail;
    private TextField displayDNI;
    private TextField displayNombre;
    private TextField displayApellido;
    private PasswordField displayPassword;
    private VBox vBox;
    private Button buttonCrearUsuario;
    private Button buttonCancelar;
    private ToggleGroup toggleGroup;

    public FormularioDeRegistroScene()
    {
        this.setButtonCancelar(MetodosGeneralesFx.createButton("Cancelar", 150));
        this.setButtonCrearUsuario(MetodosGeneralesFx.createButton("Crear usuario", 150));
    }

    public Scene crear()
    {
        /////////////////////////////LAYOUTS

        // Uso un Layouts VBox.
        vBox = new VBox(); //Instancio el layouts VBox.
        this.getvBox().setSpacing(10); // el 10 pasado como parámetro es el espacio entre elementos.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        /////////////////////////////NODOS
        // Creo las etiquetas(labels) y los campos de texto(displays).
        Label labelDNI = new Label("DNI:");
        this.displayDNI = new TextField();

        Label labelApellido = new Label("Apellido:");
        this.displayApellido = new TextField();

        Label labelEmail = new Label("Email:");
        this.displayEmail = new TextField();

        Label labelNombre = new Label("Nombre:");
        this.displayNombre = new TextField();

        Label labelPassword = new Label("Password:");
        this.displayPassword = new PasswordField();

        //Nodos botones
        // Creo un Hbox para los botones y poder centrarlos
        HBox buttonBox = new HBox(10); // 10 es la distancia entre los elementos del HBox
        buttonBox.getChildren().addAll(this.getButtonCrearUsuario(), this.getButtonCancelar());
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER); // Alineación al centro

        //Utilizo el nodo RadioButtons para para que el docente seleccione una opción para RamaDocente.
        //Instancio ToggleGroup.
        this.setToggleGroup(new ToggleGroup());
        //Instancio un Label(etiqueta) para mi grupo de botones de opciones:
        Label labelOpciones = new Label("Rama: ");
        //Creo los botones de opción:
        RadioButton opcion1 = new RadioButton("Humanidades");
        RadioButton opcion2 = new RadioButton("Ciencias Exactas");
        RadioButton opcion3 = new RadioButton("Arte");
        RadioButton opcion4 = new RadioButton("Idiomas");
        RadioButton opcion5 = new RadioButton("Educacion Fisica");
        RadioButton opcion6 = new RadioButton("Lengua y Literatura");
        RadioButton opcion7 = new RadioButton("Ciencias Económicas");
        RadioButton opcion8 = new RadioButton("Otro");
        //Agrego los botones de opción al grupo:
        opcion1.setToggleGroup(this.getToggleGroup());
        opcion2.setToggleGroup(this.getToggleGroup());
        opcion3.setToggleGroup(this.getToggleGroup());
        opcion4.setToggleGroup(this.getToggleGroup());
        opcion5.setToggleGroup(this.getToggleGroup());
        opcion6.setToggleGroup(this.getToggleGroup());
        opcion7.setToggleGroup(this.getToggleGroup());
        opcion8.setToggleGroup(this.getToggleGroup());

        //Agrego los nodos a mi layouts,
        this.getvBox().getChildren().addAll(labelDNI, this.getDisplayDNI(),
                labelApellido, this.getDisplayApellido(),
                labelEmail, this.getDisplayEmail(),
                labelNombre, this.getDisplayNombre(),
                labelPassword, this.getDisplayPassword(),
                labelOpciones,
                opcion1,
                opcion2,
                opcion3,
                opcion4,
                opcion5,
                opcion6,
                opcion7,
                opcion8,
                buttonBox);

        ////////////////////////////ESCENA
        //Creo/instancio mi escena y le agrego el layouts.
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////MANEJADORES DE EVENTOS:
        //Botón cancelar:
        buttonCancelar.setOnAction(e -> {
            Stage stage = (Stage)scene.getWindow();
            LoginScene loginScene = new LoginScene();
            stage.setScene(loginScene.crear());
        });

        //Botón crear usuario:
        buttonCrearUsuario.setOnAction(e -> {
            Rama rama = Rama.asignarRama(toggleGroup.getToggles().toString()); //Asigno la rama.
            Docente docente = new Docente(displayNombre.getText(), displayApellido.getText(), displayDNI.getText(), displayEmail.getText(), 28, rama, displayPassword.getText()); //Instancio un Docente. Con el método getText(), obtengo el valor ingresado por el usuairo en los displays.
            DocenteControlador docenteControlador = new DocenteControlador();
            docenteControlador.agregarUnRegistro(docente); //Creo un registro en la base de datos.
            Stage stage = (Stage) scene.getWindow(); //Con el método getWindow() obtengo la ventana actual en la que estoy.
            LoginScene loginScene = new LoginScene();
            stage.setScene(loginScene.crear()); //A la ventana le asigno la escena de logueo.
        });

        return scene;
    }

    public TextField getDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(TextField displayEmail) {
        this.displayEmail = displayEmail;
    }

    public TextField getDisplayDNI() {
        return displayDNI;
    }

    public void setDisplayDNI(TextField displayDNI) {
        this.displayDNI = displayDNI;
    }

    public PasswordField getDisplayPassword() {
        return displayPassword;
    }

    public void setDisplayPassword(PasswordField displayPassword) {
        this.displayPassword = displayPassword;
    }

    public TextField getDisplayApellido() {
        return displayApellido;
    }

    public void setDisplayApellido(TextField displayApellido) {
        this.displayApellido = displayApellido;
    }

    public TextField getDisplayNombre() {
        return displayNombre;
    }

    public void setDisplayNombre(TextField displayNombre) {
        this.displayNombre = displayNombre;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getButtonCrearUsuario() {
        return buttonCrearUsuario;
    }

    public void setButtonCrearUsuario(Button buttonCrearUsuario) {
        this.buttonCrearUsuario = buttonCrearUsuario;
    }

    public Button getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(Button buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public ToggleGroup getToggleGroup() {
        return toggleGroup;
    }

    public void setToggleGroup(ToggleGroup toggleGroup) {
        this.toggleGroup = toggleGroup;
    }
}
