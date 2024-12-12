package ClasesPrincipales;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ObservableListGenerico<T>
{
    public ObservableList<T> observableListGenerico;

    public ObservableListGenerico()
    {
        this.observableListGenerico = FXCollections.observableArrayList();
    }

    public void agregar(T t)
    {
        this.observableListGenerico.add(t);
    }
}
