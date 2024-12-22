package ClasesPrincipales;

import Excepciones.ArrayListVacioException;
import NodosControladores.MetodosGeneralesFx;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ArrayListGenerico<T>
{
    List<T> arrayListgenerico;
    public ArrayListGenerico()
    {
        this.arrayListgenerico = new ArrayList<T>();
    }

    public void agregar(T t)
    {
        this.arrayListgenerico.add(t);
    }
    public void eliminar(T t)
    {
        this.arrayListgenerico.remove(t);
    }
    public void imprimirTodo() throws ArrayListVacioException
    {
        if(!this.estaVacio()){ /////SIEMPRE debo comprobar que el array no esté vacío antes de iterarlo porque sino salta una excepción.
            for(T t : this.arrayListgenerico){
                System.out.println(t); //Automáticamente, si no aclaro, se aplica el metodo toString, el cual lo sobreescribí.
            }
        }else{
            throw new ArrayListVacioException("No posees datos.");
        }
    }
    public boolean estaVacio()
    {
        return this.arrayListgenerico.isEmpty();
    }
    public VBox transformarTEnBotones(Stage ventana) //La idea de este método, empleado en la escena verTodosLosCursosScene, es que todos los datos que me traigo de la BDD y son almacenados en el arrayList pasen a ser botones. Así, el usuario le da click a un curso y entra.
    {
        VBox vBox = new VBox(10); //instancio VBox. Es la caja en donde se almacenarán todos los botones.
        for(T t : this.arrayListgenerico){ //Voy iterando el array. En cada ciclo, el objeto creado será guardado en un botón gracias a userData, método que me permite almacenar un objeto en el botón.
            Button button = new Button();
            button.setUserData(t);
            vBox.getChildren().add(button);
        }
        vBox = MetodosGeneralesFx.transformarNombresDeLosBotones(vBox); //En este método transforma el nombre de los botones para que tengan el nombre de cada curso.
        vBox = MetodosGeneralesFx.agregarMetodoManejadorDeEventoABotonesTransformados(vBox, ventana); //Le agrego el manejador de evento para que cuando haga click en un botón me reedirija al curso almacenado en él.
        return vBox;
    }

    public boolean contieneElemento(Object t)
    {
        boolean contiene = false;
        if(this.arrayListgenerico.contains(t)){
            contiene = true;
            this.arrayListgenerico.contains(t);
        }
        return contiene;
    }
    public int tamanio()
    {
        return this.arrayListgenerico.size();
    }
    public T retornarUnElementoPorPosicion(int posicion)
    {
        return this.arrayListgenerico.get(posicion);
    }
}
