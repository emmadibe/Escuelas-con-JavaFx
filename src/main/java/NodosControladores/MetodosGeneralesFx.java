package NodosControladores;

import javafx.scene.control.Button;

public class MetodosGeneralesFx
{
    public static Button createButton(String etiquetaBoton, int width)
    {
        Button button = new Button(etiquetaBoton); //Instancio la clase Button. Le paso por parámetro la etieuqtea que deseo que tenga.
        button.setPrefWidth(width); //Le seteo un ancho al botón.
        return button;
    }
}
