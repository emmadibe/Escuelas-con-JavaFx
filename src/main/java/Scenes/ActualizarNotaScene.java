package Scenes;

import Controladores.TablaIntermediaEstudianteXExamenControlador;
import Interfaces.Escenas;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ActualizarNotaScene extends VBox implements Escenas
{
    public Label labelActualizarNota;
    private Spinner spinnerActualizarNota;
    private int estudianteID;
    private int examenID;
    private int nota;
    private VBox vBox;
    private Button buttonActualizarNota;
    private Button buttonCancelar;
    private HBox hboxBotones;

    public ActualizarNotaScene(int examenID, int estudianteID)
    {
        this.setExamenID(examenID);
        this.setEstudianteID(estudianteID);
    }
    @Override
    public Scene crear()
    {
        ////////////////////////////////lAYOUTS
        this.setvBox(new VBox(10));

        ////////////////////////////////NODOS
        //label
        this.labelActualizarNota = MetodosGeneralesFx.crearLabel("Nota");
        //Display
        this.setSpinnerActualizarNota(new Spinner<>(1, 10, 7));
        //Buttons
        this.setButtonActualizarNota(MetodosGeneralesFx.createButton("Actualizar nota", 150));
        this.setButtonCancelar(MetodosGeneralesFx.createButton("Cancelar", 150));
        //Agrego los botones a la caja hbox:
        this.setHboxBotones(new HBox(10)); //Instancio una caja HBox. 10px de distancia entre los nodos de la caja.
        this.getHboxBotones().getChildren().addAll(this.getButtonActualizarNota(), this.getButtonCancelar());
        //Agrego todos mis nodos a mi layouts:
        this.getvBox().getChildren().addAll(this.labelActualizarNota, this.getSpinnerActualizarNota(), this.getHboxBotones());
        ///////////////////////////////////ESCENA
        Scene scene = new Scene(this.getvBox(), 400, 200);

        ///////////////////////////////////MANEJADORES DE EVENTOS:
        //Botón actualizar nota
        this.getButtonActualizarNota().setOnAction(e -> {
            int nota = (int)this.getSpinnerActualizarNota().getValue();
            this.setNota(nota);
            TablaIntermediaEstudianteXExamenControlador TIEXEC = new TablaIntermediaEstudianteXExamenControlador(this.getEstudianteID(), this.getExamenID(), this.getNota());
            TIEXEC.actualizarNota();
            //Cerrar la ventana:
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        });
        //Botón cancelar
        this.getButtonCancelar().setOnAction(e -> {
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        });
        return scene;
    }

    public javafx.scene.control.Label getLabelActualizarNota() {
        return labelActualizarNota;
    }

    public void setLabelActualizarNota(javafx.scene.control.Label labelActualizarNota) {
        this.labelActualizarNota = labelActualizarNota;
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public Spinner getSpinnerActualizarNota() {
        return spinnerActualizarNota;
    }

    public void setSpinnerActualizarNota(Spinner spinnerActualizarNota) {
        this.spinnerActualizarNota = spinnerActualizarNota;
    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }

    public Button getButtonActualizarNota() {
        return buttonActualizarNota;
    }

    public void setButtonActualizarNota(Button buttonActualizarNota) {
        this.buttonActualizarNota = buttonActualizarNota;
    }

    public Button getButtonCancelar() {
        return buttonCancelar;
    }

    public void setButtonCancelar(Button buttonCancelar) {
        this.buttonCancelar = buttonCancelar;
    }

    public HBox getHboxBotones() {
        return hboxBotones;
    }

    public void setHboxBotones(HBox hboxBotones) {
        this.hboxBotones = hboxBotones;
    }

}
