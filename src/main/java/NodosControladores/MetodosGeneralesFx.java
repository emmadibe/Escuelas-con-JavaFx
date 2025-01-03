package NodosControladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Estudiante;
import ClasesPrincipales.Examen;
import Controladores.CursoControlador;
import Scenes.VerCursoScene;
import Scenes.VerExamenScene;
import Ventanas.AgregarNotaStage;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Objects;

public class MetodosGeneralesFx
{
    public static Label labelMensajeDeError = MetodosGeneralesFx.crearLabel("errorMensaje", "red", "10", "10");

    public static Button createButton(String etiquetaBoton, int width)
    {
        Button button = new Button(etiquetaBoton); //Instancio la clase Button. Le paso por parámetro la etieuqtea que deseo que tenga.
        button.setPrefWidth(width); //Le seteo un ancho al botón.
        return button;
    }

    public static Label crearLabel(String etiquetaLabel, String color, String padding, String borderRadius)
    {
        Label label = new Label(etiquetaLabel);
        label.setStyle("-fx-background-color: " + color +"; -fx-padding: " + padding + "; -fx-border-radius: " + borderRadius +";");
        return label;
    }

    public static Label crearLabel(String etiquetaLabel)
    {
        return crearLabel(etiquetaLabel, "null", "0", "0"); //Sobrecargo el método para que si envío solo la etiqueta como parámetro se pongan los otros valores por defecto.
    }

    public static TextField crearTextField() {
        return crearTextField(""); // Llama a la otra versión con un valor por defecto
    }

    public static TextField crearTextField(String etiquetaDisplay) //Sobrecargo el método
    {
        return new TextField(etiquetaDisplay);
    }
    public static VBox transformarNombresDeLosBotones(VBox vBox)
    {
        for(Node node : vBox.getChildren()){ //Voy a iterar cada nodo de mi caja
            if(node instanceof Button){ //Me aseguro que el nodo sea una instancia de Button.
                if(node.getUserData() instanceof Curso){ //Si el botón almacena un curso...
                    ((Button) node).setText(((Curso) node.getUserData()).getNombre()); //Le seteo como texto el valor del campo nombre.
                }else if(node.getUserData() instanceof Examen){
                    ((Button) node).setText(((Examen) node.getUserData()).getNombre());
                }else if(node.getUserData() instanceof Estudiante){
                    ((Button) node).setText(((Estudiante) node.getUserData()).getNombre() + " " + ((Estudiante) node.getUserData()).getApellido());
            }
            }
        }
        return vBox; //retorno la caja.
    }

    public static VBox agregarMetodoManejadorDeEventoABotonesTransformados(VBox vBox, Stage ventana)
    {
        for(Node node : vBox.getChildren()){
            if(node instanceof Button){
                if(node.getUserData() instanceof Curso){ //Esto es clave. Ahora, node es tomado como un curso, por lo que puedo acceder a todos sus métodos y atributos.
                    ((Button) node).setOnAction(e -> { //Cuando hago click enel botón...
                        VerCursoScene verCursoScene = new VerCursoScene((Curso) node.getUserData()); //Seteo verCursoScene. Le paso como parámetro el curso del bucle actual.
                        Scene scene = verCursoScene.crear(); //Creo la nueva escena.
                        ventana.setScene(scene); //A la ventana Le cambio la escena.
                    });
                }else if(node.getUserData() instanceof Examen){
                    ((Button) node).setOnAction(e ->{ //Cuando haga click... la ventana cambia de escena a ver el examen donde hago click.
                        VerExamenScene verExamenScene = new VerExamenScene((Examen) node.getUserData());
                        Scene scene = verExamenScene.crear();
                        if(!Objects.isNull(scene)){
                            ventana.setScene(scene);
                        }else{
                            vBox.getChildren().add(labelMensajeDeError); //Recién agrego el label en mi layouts cuando el usuario intenta ingresar aun examen de un curso sin alumnos.
                            // Creo la animaciónn
                            MetodosGeneralesFx.animacionEtiqueta(labelMensajeDeError, "No posees estudiantes en el curso!");
                        }
                    });
                }else if(node.getUserData() instanceof Estudiante){
                    ((Button) node).setOnAction(e -> {
                        AgregarNotaStage agregarNotaStage = new AgregarNotaStage(((Estudiante) node.getUserData()).getExamenID(), ((Estudiante) node.getUserData()).getID());
                        try {
                            agregarNotaStage.start(new Stage());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
            }
        }
        return vBox;
    }
    public static void limpiarInputs(Parent parent)  //La idea es limpiar todos los displays.
    {
        for(Node node : parent.getChildrenUnmodifiable()){ //Recorro el parent (Un Vvbx, por ejemplo)
            if(node instanceof TextInputControl){ //si en el nodo se puede agregar información (input)...
                ((TextInputControl) node).clear(); //Borro lo que sería el display.
            }
        }
    }

    public static void animacionEtiqueta(Label label, String texto)
    {
        label.setStyle("-fx-background-color: yellow; -fx-padding: 10px;");
        label.setText(texto); //Seteo el texto en la etiqueta.
        label.setVisible(true); // Aseguro que el label sea visible-
        // Creo la animaciónn
        TranslateTransition animacion = new TranslateTransition(Duration.millis(500), label);
        animacion.setFromY(0);
        animacion.setToY(-50);
        animacion.setCycleCount(2);
        animacion.setAutoReverse(true);
        animacion.play();
    }

    public static ContextMenu asignarMenues(int docenteID)
    {
        ContextMenu contextMenu = new ContextMenu();
        CursoControlador cursoControlador = new CursoControlador();
        ArrayListGenerico<Integer> arrayListGenericoCiclosLectivos = cursoControlador.traerTodosLosCiclosLectivosDelDocente(docenteID); //Tengo en el arayList todos los ciclos lectivos del docente.
        //Cada valor del arrayList, cacada anio, dbee ser un item del ContexMenu. Voy a iterar el arrayList. En cada bucle, tendré un cicloLectivo, el cual asignaré como item al contexMenu:
        for(int i = 0; i < arrayListGenericoCiclosLectivos.tamanio(); i++){
            Integer cicloLectivo = arrayListGenericoCiclosLectivos.retornarUnElementoPorPosicion(i); //Obtengo el ciclo lectivo actual
            MenuItem menuItem = new MenuItem(cicloLectivo.toString()); //Creo un item nuevo cuyo nombre será el ciclo lectivo
            contextMenu.getItems().add(menuItem); //Agrego el item al contextMenu.
        }
        return contextMenu;
    }

    public static Alert createAlerta(String titulo, String cabecera, String cuerpo)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(cuerpo);
        // Obtener el diálogo de la alerta y setear el estilo
        Region content = (Region) alert.getDialogPane().getChildren().get(0);
        content.setStyle("-fx-background-color: red;");
        return alert;
    }
    public static ButtonType createButtonType(String etiqueta)
    {
        return new ButtonType(etiqueta);
    }

    public static TextArea createTextArea(String etiqueta)
    {
        TextArea notaArea = new TextArea(etiqueta);
        notaArea.setPromptText("Escribe tu nota aquí...");
        notaArea.setWrapText(true);  // Permite que el texto se envuelva automáticamente
        notaArea.setPrefHeight(200); // Ajusta la altura preferida
        return notaArea;
    }
}
