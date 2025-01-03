package Scenes;

import ClasesPrincipales.Docente;
import Controladores.DocenteControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class EditarUsuarioScene extends VBox implements Escenas
{
    private Docente docente;
    private VBox vBox;
    private Label labelNombre;
    private Label labelApellido;
    private Label labelPassword;
    private Label labelAntiguoPassword;
    private Label labelRepetirPassword;
    private Label labelDatosDocente;
    private Label labelEdad;
    private TextField displayNombre;
    private TextField displayApellido;
    private Spinner spinnerEdad;
    private PasswordField displayAntiguoPassword;
    private PasswordField displayPassword;
    private PasswordField displayRepetirPassword;
    private HBox cajaBotones;
    private Button buttonEditar;
    private Button buttonCancelar;

    public EditarUsuarioScene(Docente docente)
    {
        this.setDocente(docente);
    }

    @Override
    public Scene crear()
    {
        //////////////////////////////////////LAYOUTS
        //Instancio mi layouts Vbox
        this.setvBox(new VBox(10)); //10px es la distancia entre los nodos del  vbox
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        ////////////////////////////////////NODOS
        //label
        this.setLabelNombre(MetodosGeneralesFx.crearLabel("Nombre"));
        this.setLabelApellido(MetodosGeneralesFx.crearLabel("Apellido"));
        this.setLabelEdad(MetodosGeneralesFx.crearLabel("Edad"));
        this.setLabelAntiguoPassword(MetodosGeneralesFx.crearLabel("Antiguo password"));
        this.setLabelPassword(MetodosGeneralesFx.crearLabel("Nuevo Password"));
        this.setLabelRepetirPassword(MetodosGeneralesFx.crearLabel("Repetir nuevo Password"));
        this.setLabelDatosDocente(MetodosGeneralesFx.crearLabel("docente", "pink", "10", "10"));
        this.getLabelDatosDocente().setText(
                "docenteID: " + this.getDocente().getID() + "\n" +
                        "Nombre: " + this.getDocente().getNombre() + "n\n" +
                        "Apellido: " + this.getDocente().getApellido() + "n\n" +
                        "DNI: " + this.getDocente().getDni() + "n\n" +
                        "Email: " + this.getDocente().getEmail() + "n\n"
        );
        //Displays. Los instancio y les seteo el tamaño
        this.setDisplayNombre(new TextField());
        this.setDisplayApellido(new TextField());
        this.setDisplayAntiguoPassword(new PasswordField());
        this.setDisplayPassword(new PasswordField());
        this.setDisplayRepetirPassword(new PasswordField());
        //Spinner
        this.setSpinnerEdad(new Spinner<>(17, 120, 20)); //min, max, valor inicial.
        this.getSpinnerEdad().setEditable(true); //Permite la entrada directa de números.
        //Botones:
        this.setButtonEditar(MetodosGeneralesFx.createButton("Editar", 150));
        this.setButtonCancelar(MetodosGeneralesFx.createButton("Cancelar", 150));
        this.setCajaBotones(new HBox(10)); //Instancio el hbox, que uso como caja de botones.
        this.getCajaBotones().getChildren().addAll(this.getButtonEditar(), this.getButtonCancelar());

        //Agrego todos mis nodos a mi layouts:
        this.getvBox().getChildren().addAll(this.getLabelNombre(), this.getDisplayNombre(), this.getLabelApellido(), this.getDisplayApellido(), this.getLabelEdad(), this.getSpinnerEdad(), this.getLabelAntiguoPassword(), this.getDisplayAntiguoPassword(), this.getLabelPassword(), this.getDisplayPassword(), this.getLabelRepetirPassword(), this.getDisplayRepetirPassword(), this.getCajaBotones(), this.getLabelDatosDocente());

        ///////////////////////////////////////ESCENA:
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        ////////////////////////////////////////////MANEJADORES DE EVENTOS
        this.getButtonCancelar().setOnAction(e -> {
            DocenteScene docenteScene = new DocenteScene(this.getDocente());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(docenteScene.crear());
        });
        this.getButtonEditar().setOnAction(e -> {
            String antiguoPass = this.getDisplayAntiguoPassword().getText();
            if(antiguoPass.equals(this.getDocente().getPassword()))
            {
                System.out.println("Son iguales!");
                String nuevoPass = this.getDisplayPassword().getText();
                String nuevoPassRepetido = this.getDisplayRepetirPassword().getText();
                if(nuevoPass.equals(nuevoPassRepetido)){
                    System.out.println("Coinciden!");
                    String nombre = this.getDisplayNombre().getText();
                    String apellido = this.getDisplayApellido().getText();
                    int edad = (int) this.getSpinnerEdad().getValue();
                    String passNuevoDefinitivo = (nuevoPass.isEmpty() ? antiguoPass : nuevoPass); //O sea, utilizo el operador ternario de la siguiente manera: si el usuario no escribió nada en el display nuevoPassword (""), directamente dejo el antiguo pass, ya que significa que el usuario no quiso cambiar la contraseña; sino le pongo la nueva contraseña.
                    Docente docenteEditado = new Docente(this.getDocente().getID(), nombre, apellido, edad, passNuevoDefinitivo);
                    DocenteControlador docenteControlador = new DocenteControlador();
                    docenteControlador.editarRegistro(docenteEditado);
                }else{
                    System.out.println("No coinciden!");
                }
            }else{
                System.out.println("No son iguales!");
            }
        });
        return scene;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Label getLabelNombre() {
        return labelNombre;
    }

    public void setLabelNombre(Label labelNombre) {
        this.labelNombre = labelNombre;
    }

    public Label getLabelPassword() {
        return labelPassword;
    }

    public void setLabelPassword(Label labelPassword) {
        this.labelPassword = labelPassword;
    }

    public TextField getDisplayNombre() {
        return displayNombre;
    }

    public void setDisplayNombre(TextField displayNombre) {
        this.displayNombre = displayNombre;
    }

    public PasswordField getDisplayPassword() {
        return displayPassword;
    }

    public void setDisplayPassword(PasswordField displayPassword) {
        this.displayPassword = displayPassword;
    }

    public HBox getCajaBotones() {
        return cajaBotones;
    }

    public void setCajaBotones(HBox cajaBotones) {
        this.cajaBotones = cajaBotones;
    }

    public Button getButtonEditar() {
        return buttonEditar;
    }

    public void setButtonEditar(Button buttonEditar) {
        this.buttonEditar = buttonEditar;
    }

    public Button getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(Button buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public Label getLabelDatosDocente() {
        return labelDatosDocente;
    }

    public void setLabelDatosDocente(Label labelDatosDocente) {
        this.labelDatosDocente = labelDatosDocente;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Label getLabelRepetirPassword() {
        return labelRepetirPassword;
    }

    public void setLabelRepetirPassword(Label labelRepetirPassword) {
        this.labelRepetirPassword = labelRepetirPassword;
    }

    public PasswordField getDisplayRepetirPassword() {
        return displayRepetirPassword;
    }

    public void setDisplayRepetirPassword(PasswordField displayRepetirPassword) {
        this.displayRepetirPassword = displayRepetirPassword;
    }

    public Label getLabelAntiguoPassword() {
        return labelAntiguoPassword;
    }

    public void setLabelAntiguoPassword(Label labelAntiguoPassword) {
        this.labelAntiguoPassword = labelAntiguoPassword;
    }

    public PasswordField getDisplayAntiguoPassword() {
        return displayAntiguoPassword;
    }

    public void setDisplayAntiguoPassword(PasswordField displayAntiguoPassword) {
        this.displayAntiguoPassword = displayAntiguoPassword;
    }

    public TextField getDisplayApellido() {
        return displayApellido;
    }

    public void setDisplayApellido(TextField displayApellido) {
        this.displayApellido = displayApellido;
    }

    public Label getLabelApellido() {
        return labelApellido;
    }

    public void setLabelApellido(Label labelApellido) {
        this.labelApellido = labelApellido;
    }

    public Label getLabelEdad() {
        return labelEdad;
    }

    public void setLabelEdad(Label labelEdad) {
        this.labelEdad = labelEdad;
    }

    public Spinner getSpinnerEdad() {
        return spinnerEdad;
    }

    public void setSpinnerEdad(Spinner spinnerEdad) {
        this.spinnerEdad = spinnerEdad;
    }
}
