package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.ObservableListGenerico;
import ClasesPrincipales.TraerTodo;
import Modelos.TraerTodoModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TraerTodoControlador
{
    private int cursoID;
    private TableView<TraerTodo> todoTableView;
    private ObservableList<TraerTodo> observableList; //Es como un arrayList de JavaFX. agregamos los elementos que queremos ver en la tabla y luego los seteamos en la tabla.
    private TableColumn<TraerTodo, String> colNombreAlumno;
    private TableColumn<TraerTodo, Integer> colNota;

    public TraerTodoControlador(int cursoID)
    {
        this.setCursoID(cursoID);
    }

    public void initialize() //Seteamos qué columna va a tener cada cosa.
    {
        this.setTodoTableView(new TableView<>());
        this.observableList = FXCollections.observableArrayList(); //Estoy inicializando el "ArrayList"de Java FX. Así es como se inicializa el arrayList de JavaFx, sin la palabra reservada new. Medio raro.
        //Ahora voy a decirle a las columnas qué atributo es lo que van a atrapar:
        this.setColNombreAlumno(new TableColumn("nombreYapellido"));
        this.colNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreYapellido")); //Esta columna atrapará el atributo nombreYapellido de TraerTodo. Sabe que es un atributo de una instancia de TraerTodo porque así se lo indiqué en el ObservableList.
        this.setColNota(new TableColumn("nota"));
        this.colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
        //Agrego las columnas a la tabla:
        this.todoTableView.getColumns().addAll(this.colNombreAlumno, this.colNota);
    }

    public TableView<TraerTodo> agregarDatosALaTabla()
    {
        //Inicializo:
        this.initialize();
        //Me traigo los datos:
        ArrayListGenerico<TraerTodo> arrayListTraerTodo = TraerTodoModelo.traerATodos(this.getCursoID()); //Ya tengo todos los datos en mi arrayList.
        System.out.println("El curso id es: " + this.getCursoID());
        //Paso los datos al observableArrayList y los seteo en la tabla.
        for(int i = 0; i < arrayListTraerTodo.tamanio(); i ++){
            TraerTodo traerTodo = arrayListTraerTodo.retornarUnElementoPorPosicion(i);
            this.observableList.add(traerTodo); //Ya tengo el elemento en la posición i agregado. Y así, en cada ciclo va agregando.
        }
        //Agrego el observableList a la tabla:
        this.todoTableView.setItems(this.observableList);

        return todoTableView;
    }

    public TableColumn getColNombreAlumno() {
        return colNombreAlumno;
    }

    public void setColNombreAlumno(TableColumn colNombreAlumno) {
        this.colNombreAlumno = colNombreAlumno;
    }

    public TableColumn getColNota() {
        return colNota;
    }

    public void setColNota(TableColumn colNota) {
        this.colNota = colNota;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public TableView<TraerTodo> getTodoTableView() {
        return todoTableView;
    }

    public void setTodoTableView(TableView<TraerTodo> todoTableView) {
        this.todoTableView = todoTableView;
    }
}
