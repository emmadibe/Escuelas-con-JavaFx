package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Seguimiento;
import Modelos.General;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;

public class TraerTodosLosSeguimientosControlador
{
    private Curso curso;
    private TableView<Seguimiento> tableViewSeguimiento;
    private ObservableList<Seguimiento> observableListSeguimiento;
    private TableColumn<Seguimiento, String> tableColumnTitulo;
    private TableColumn<Seguimiento, Date> tableColumnFecha;
    private TableColumn<Seguimiento, String> tableColumnCuerpo;
    private ArrayListGenerico<Seguimiento> arrayListSeguimientos;
    private SeguimientoControlador seguimientoControlador;

    public TraerTodosLosSeguimientosControlador(Curso curso)
    {
        this.setCurso(curso);
    }

    public void initialize()
    {
        //Primero, me traigo todos los registros, todos los seguimientos
        this.setSeguimientoControlador(new SeguimientoControlador(this.getCurso().getID())); //Inicializo la instancia de SeguimientoControlador.
        this.setArrayListSeguimientos(this.getSeguimientoControlador().traerTodos());//Tengo, en el arrayList, todos los seguimientos.
        //Ahora la idea es crear una tabla que tenga tres columnas. En una, estará el botón cuyo nombre será el título del seguimiento; en otra, la fecha en la que se escribió el seguimiento; en la tercera, el cuerpo.
        this.setTableViewSeguimiento(new TableView<>()); //Inicializo mi tabla, nodo que usaré paramostrar mis seguimientos.
        this.setObservableListSeguimiento(FXCollections.observableArrayList()); //Instancio el observableList, el cual es un ArrayList de JavaFx

        //Configurar columnas. En esta tabla no necesitaré crear nombres de columnas dinámicamente.
        this.setTableColumnTitulo(new TableColumn<>("Titulo")); //Instancio la columna cuyas filas contarán de botonoes. Los nombres de estos serán los títulos de mis seguimientos.
        this.getTableColumnTitulo().setCellValueFactory(new PropertyValueFactory<>("titulo")); //Así, los valores de los registros de la columna titulo serán los que retorne el getter titulo.
        this.setTableColumnFecha(new TableColumn<>("Fecha"));
        this.getTableColumnFecha().setCellValueFactory(new PropertyValueFactory<>("fecha")); //Los valores de los registros de la columna fecha serán los que retorne el getter fecha.
        this.setTableColumnCuerpo(new TableColumn<>("Cuerpo"));
        this.getTableColumnCuerpo().setCellValueFactory(new PropertyValueFactory<>("cuerpo"));

        //Agrego las columnas a mi tabla:
        this.getTableViewSeguimiento().getColumns().addAll(this.getTableColumnTitulo(), this.getTableColumnFecha(), this.getTableColumnCuerpo());
    }

    public TableView<Seguimiento> agregarDatosALaTabla()
    {
        this.initialize(); // Ya tengo seteadas las columnas y agregadas a la tabla. También tengo todos mis registros, todos mis seguimientos, en el arrayList

        //Ahora lo que debo hacer es pasar mis registros del arrayList al observableList. Pues, el nodo TableView lo que admite es observableList, que es un arrayList de Javafx,
        for(int i = 0; i < this.getArrayListSeguimientos().tamanio(); i++){
            Seguimiento seguimiento = this.getArrayListSeguimientos().retornarUnElementoPorPosicion(i);
            this.getObservableListSeguimiento().add(seguimiento);
        }
        //Ahora que el observableList ya tiene todos los registros, debo seteárselo a la tabla:
        this.getTableViewSeguimiento().setItems(this.getObservableListSeguimiento());

        return this.getTableViewSeguimiento(); //Retorno la tabla.
    }

    public boolean esPosibleCrearTabla()
    {
        boolean posible = false;

        boolean existeSeguimientos = General.existeTabla("seguimientos");
        if(existeSeguimientos){ //Primero que nada, debo corroborar que exista la tabla seguimientos. Luego, corroboro si dicha tabla tiene registros. Es para que no me tire una excepción.
            this.setSeguimientoControlador(new SeguimientoControlador(this.getCurso().getID()));
            posible = this.getSeguimientoControlador().existeRegistroParaCursoID();
        }
        return posible;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TableView<Seguimiento> getTableViewSeguimiento() {
        return tableViewSeguimiento;
    }

    public void setTableViewSeguimiento(TableView<Seguimiento> tableViewSeguimiento) {
        this.tableViewSeguimiento = tableViewSeguimiento;
    }

    public ObservableList<Seguimiento> getObservableListSeguimiento() {
        return observableListSeguimiento;
    }

    public void setObservableListSeguimiento(ObservableList<Seguimiento> observableListSeguimiento) {
        this.observableListSeguimiento = observableListSeguimiento;
    }

    public TableColumn<Seguimiento, String> getTableColumnTitulo() {
        return tableColumnTitulo;
    }

    public void setTableColumnTitulo(TableColumn<Seguimiento, String> tableColumnTitulo) {
        this.tableColumnTitulo = tableColumnTitulo;
    }

    public TableColumn<Seguimiento, Date> getTableColumnFecha() {
        return tableColumnFecha;
    }

    public void setTableColumnFecha(TableColumn<Seguimiento, Date> tableColumnFecha) {
        this.tableColumnFecha = tableColumnFecha;
    }

    public ArrayListGenerico<Seguimiento> getArrayListSeguimientos() {
        return arrayListSeguimientos;
    }

    public void setArrayListSeguimientos(ArrayListGenerico<Seguimiento> arrayListSeguimientos) {
        this.arrayListSeguimientos = arrayListSeguimientos;
    }

    public SeguimientoControlador getSeguimientoControlador() {
        return seguimientoControlador;
    }

    public void setSeguimientoControlador(SeguimientoControlador seguimientoControlador) {
        this.seguimientoControlador = seguimientoControlador;
    }

    public TableColumn<Seguimiento, String> getTableColumnCuerpo() {
        return tableColumnCuerpo;
    }

    public void setTableColumnCuerpo(TableColumn<Seguimiento, String> tableColumnCuerpo) {
        this.tableColumnCuerpo = tableColumnCuerpo;
    }
}
