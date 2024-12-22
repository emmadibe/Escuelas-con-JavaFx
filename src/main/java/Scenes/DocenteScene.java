package Scenes;

import ClasesPrincipales.Docente;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DocenteScene extends VBox implements Escenas
{
    private Docente docente;
    private VBox vBox;
    private Button crearCurso;
    private Button verCursos;
    private Button entrarAUnCurso;
    private Button eliminarUnCurso;
    private Button editarPerfil;
    private Button cerrarSesion;
    private Label docenteLabel;

    public DocenteScene(Docente docente)
    {
        this.setDocente(docente);
    }

    @Override
    public Scene crear()
    {
        ///////////////////////////////////LAYOUTS
        //Uso un layouts VBox
        vBox = new VBox();
        this.getvBox().setSpacing(10);//Seteo la distancia entre los nodos del layouts.
        this.getvBox().getStyleClass().add("vbox-background"); //Le agrego los estilos, los cuales losconfiguro en mi archivo css style.css.

        ///////////////////////////////////NODOS
        //Botones
        this.setCrearCurso(MetodosGeneralesFx.createButton("Crear curso", 500));
        this.setVerCursos(MetodosGeneralesFx.createButton("Ver mis cursos", 500));
        this.setEditarPerfil(MetodosGeneralesFx.createButton("Editar mi perfil", 500));
        this.setEliminarUnCurso(MetodosGeneralesFx.createButton("Eliminar un curso", 500));
        this.setCerrarSesion(MetodosGeneralesFx.createButton("LogOut", 500));
        //Creo otro VBox para los botones y poder centrarlos:
        VBox buttonBox = new VBox(10);
        buttonBox.getChildren().addAll(this.getCrearCurso(), this.getVerCursos(), this.getEliminarUnCurso(), this.getEditarPerfil(), this.getCerrarSesion());
        //Label del docente:
        this.setDocenteLabel(MetodosGeneralesFx.crearLabel("docenteLabel", "pink", "10", "10"));
        docenteLabel.setText("ID: " + this.getDocente().getID() + "\n" +
                            "Nombre: " + this.getDocente().getNombre() + "\n" +
                            "Apellido: " + this.getDocente().getApellido() + "\n" +
                            "Email: " + this.getDocente().getEmail() + "\n" +
                            "DNI: " + this.getDocente().getDni() + "\n" +
                            "Edad: " + this.getDocente().getEdad() + "\n" +
                            "Rama: " + this.getDocente().getRama().toString());
        //Agrego mis nodos(botones y label) a mi layouts:
        vBox.getChildren().addAll(buttonBox, docenteLabel);

        ///////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 800, 600);
        //Le seteo la ruta de mi archivo css para aplicarle estilos a mi escena:
        scene.getStylesheets().add(getClass().getResource("/Estilos/styles.css").toExternalForm());
        //////////////////////////////////MANEJADORES DE EVENTOS.
        crearCurso.setOnAction(e -> {
            this.getDocente().imprimirUnaInstancia();
        });

        cerrarSesion.setOnAction(e -> {
            LoginScene loginScene = new LoginScene();
            Stage stage = (Stage) scene.getWindow(); //VENTANA actual.
            stage.setScene(loginScene.crear());
            //La animación:
            loginScene.getLabelLogOut().setText("¡Nos vemos luego, " + this.getDocente().getNombre() + "!");
            loginScene.getGridPane().add(loginScene.getLabelLogOut(), 3, 5); //Recién agrego mi nodo-label a mi layout cuando hay un elogOut. Para que aparezca recién ahí.
            loginScene.getLabelLogOut().setVisible(true); // Ensure the label is visible

            // Create the animation
            TranslateTransition animacion = new TranslateTransition(Duration.millis(500), loginScene.getLabelLogOut());
            animacion.setFromY(0);
            animacion.setToY(-50);
            animacion.setCycleCount(2);
            animacion.setDuration(Duration.millis(1000));
            animacion.setAutoReverse(true);
            animacion.setOnFinished(e2 -> {
                loginScene.getLabelLogOut().setVisible(false); // Oculta el label al finalizar la animación
            });
            animacion.play();
        });

        crearCurso.setOnAction((e -> {
            CrearCursoScene crearCursoScene = new CrearCursoScene(this.getDocente());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(crearCursoScene.crear());
        }));

        verCursos.setOnAction(e -> {
            VerTodosLosCursosScene verTodosLosCursosScene = new VerTodosLosCursosScene(this.getDocente(), (Stage) scene.getWindow()); //Necesito saber de qué docente traerme los cursos
            Stage stage = (Stage) scene.getWindow();
            Scene scene1 = verTodosLosCursosScene.crear();
            stage.setScene(scene1);
        });

        return scene;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getCrearCurso() {
        return crearCurso;
    }

    public void setCrearCurso(Button crearCurso) {
        this.crearCurso = crearCurso;
    }

    public Button getVerCursos() {
        return verCursos;
    }

    public void setVerCursos(Button verCursos) {
        this.verCursos = verCursos;
    }

    public Button getEliminarUnCurso() {
        return eliminarUnCurso;
    }

    public void setEliminarUnCurso(Button eliminarUnCurso) {
        this.eliminarUnCurso = eliminarUnCurso;
    }

    public Button getEditarPerfil() {
        return editarPerfil;
    }

    public void setEditarPerfil(Button editarPerfil) {
        this.editarPerfil = editarPerfil;
    }

    public Label getDocenteLabel() {
        return docenteLabel;
    }

    public void setDocenteLabel(Label docenteLabel) {
        this.docenteLabel = docenteLabel;
    }

    public Button getCerrarSesion() {
        return cerrarSesion;
    }

    public void setCerrarSesion(Button cerrarSesion) {
        this.cerrarSesion = cerrarSesion;
    }
}
