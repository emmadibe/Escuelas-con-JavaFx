package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TraerTodoAsistencia;
import Modelos.TraerTodoAsistenciaModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TraerTodoAsistenciaControlador
{
    private int cursoID;
    private TableView<TraerTodoAsistencia> tableViewAsistencia;
    private TableColumn<TraerTodoAsistencia, String> colNombreAlumno;
    private ObservableList<TraerTodoAsistencia> observableList;
    private ArrayListGenerico<TraerTodoAsistencia> traerTodoAsistenciaArrayListGenerico;

    public void initialize()
    {
        this.setTableViewAsistencia(new TableView<>()); //Inicializo la instancia de mi nodo TableView. Este es el nodo que utilizo para volcar los datos en forma de tabla.
        this.observableList = FXCollections.observableArrayList(); //Inicializo el ObservableList
        this.setTraerTodoAsistenciaArrayListGenerico(new ArrayListGenerico<>()); //

        // Configuración de la columna "Nombre y apellido"
        this.setColNombreAlumno(new TableColumn<>("Nombre y apellido")); //A este columna le seteo un nombre cableado porque ya sé que debe tener ese nombre
        this.colNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreYapellido")); //Así, los valores de los registros de la columna Nombre y apellido serán los que retorne el getter nombreYapellido.

        //Cargar datos y crear las columnas de manera dinámica.
        this.traerTodoAsistenciaArrayListGenerico = TraerTodoAsistenciaModelo.traerTodo(this.getCursoID());

    }

    public TableView<TraerTodoAsistencia> agregarDatosALaTabla()
    {

        return  this.getTableViewAsistencia();
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public TableView<TraerTodoAsistencia> getTableViewAsistencia() {
        return tableViewAsistencia;
    }

    public void setTableViewAsistencia(TableView<TraerTodoAsistencia> tableViewAsistencia) {
        this.tableViewAsistencia = tableViewAsistencia;
    }

    public TableColumn<TraerTodoAsistencia, String> getColNombreAlumno() {
        return colNombreAlumno;
    }

    public void setColNombreAlumno(TableColumn<TraerTodoAsistencia, String> colNombreAlumno) {
        this.colNombreAlumno = colNombreAlumno;
    }

    public ObservableList<TraerTodoAsistencia> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<TraerTodoAsistencia> observableList) {
        this.observableList = observableList;
    }

    public ArrayListGenerico<TraerTodoAsistencia> getTraerTodoAsistenciaArrayListGenerico() {
        return traerTodoAsistenciaArrayListGenerico;
    }

    public void setTraerTodoAsistenciaArrayListGenerico(ArrayListGenerico<TraerTodoAsistencia> traerTodoAsistenciaArrayListGenerico) {
        this.traerTodoAsistenciaArrayListGenerico = traerTodoAsistenciaArrayListGenerico;
    }
}
