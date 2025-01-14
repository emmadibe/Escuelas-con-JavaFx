package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TraerTodoAsistencia;
import ClasesPrincipales.TraerTodoAsistenciaLaParteFecha;
import ClasesPrincipales.TraerTodoAsistenciaPasandoFechaADiccionario;
import Excepciones.ArrayListVacioException;
import Modelos.General;
import Modelos.TraerTodoAsistenciaModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import net.bytebuddy.utility.privilege.GetMethodAction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;

public class TraerTodoAsistenciaControlador
{
    private int cursoID;
    private TableView<TraerTodoAsistenciaPasandoFechaADiccionario> tableViewAsistencia;
    private TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, String> colNombreAlumno;
    private ObservableList<TraerTodoAsistenciaPasandoFechaADiccionario> observableList;
    private ArrayListGenerico<TraerTodoAsistencia> traerTodoAsistenciaArrayListGenerico;
    private ArrayListGenerico<TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>>> arrayListColumnas;
    private ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> arrayPasandofechaADic;

    public TraerTodoAsistenciaControlador (int cursoID)
    {
        this.setCursoID(cursoID);
    }

    public void initialize()
    {
        this.setTableViewAsistencia(new TableView<>()); //Inicializo la instancia de mi nodo TableView. Este es el nodo que utilizo para volcar los datos en forma de tabla.
        this.observableList = FXCollections.observableArrayList(); //Inicializo el ObservableList
        this.setTraerTodoAsistenciaArrayListGenerico(new ArrayListGenerico<>()); //

        // Configuración de la columna "Nombre y apellido"
        this.setColNombreAlumno(new TableColumn<>("Nombre y apellido")); //A este columna le seteo un nombre cableado porque ya sé que debe tener ese nombre
        this.colNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreYapellidoAlumno")); //Así, los valores de los registros de la columna Nombre y apellido serán los que retorne el getter nombreYapellidoAlumno.

        //Cargar datos. Traigo mis registros desde la BDD al arrayList.
        this.setTraerTodoAsistenciaArrayListGenerico(TraerTodoAsistenciaModelo.traerTodo(this.getCursoID())); //Tengo todos mis registros en el arrayList. Este formato de coleción, empero, no me sirve. Voy a pasarlo, a continuación, a otro formato que contenga un diccionario adentro.

        //Pasar los registros de ese arrayList original a otro arrayList que posea como uno de sus atributos a un HashMap:
        TraerTodoAsistenciaPasandoFechaADiccionario TTAPFD = new TraerTodoAsistenciaPasandoFechaADiccionario();
        this.setArrayPasandofechaADic(TTAPFD.pasarfechasADiccionario(this.getTraerTodoAsistenciaArrayListGenerico()));//Listo. Ahora, en este arrayList poseo todos los registros de mi BDD.
        //Columnas

        //Creo las columnas de manera dinámica. Debo tener una columna por clase. El nombre de la columna será la fecha de la clase.
        this.setArrayListColumnas(this.crearColumnasDinamicas()); //Tengo mis columnas, NO mis registros. O sea, los nombres de las columnas sin valores en las filas.

        //Agregar columnas a la tabla:
        this.agregarColumnasALaTablaDeFormaDinamica();
    }

    public void agregarColumnasALaTablaDeFormaDinamica()
    {
        this.tableViewAsistencia.getColumns().add(this.getColNombreAlumno()); //Esa columna ya sé que la debo tener.
        for(int i = 0; i < this.getArrayListColumnas().tamanio(); i++){
            TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>> colDinamic = this.getArrayListColumnas().retornarUnElementoPorPosicion(i);
            this.tableViewAsistencia.getColumns().add(colDinamic); //Y, así, voy agregando columna por columna.
        }
    }

    public ArrayListGenerico<TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>>> crearColumnasDinamicas() //En este método voy a crear las columnas de mi tabla correspondientes a cada clase. Una clase es igual a una columna, cuyo título será la fecha de la clase y cada fila tendrá el valor boool del atributo asistió.
    {
        ArrayListGenerico<TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>>> arrayListColumnas = new ArrayListGenerico<>(); //En este arrayList, cada elemento es una columna.
        ArrayListGenerico<TraerTodoAsistenciaLaParteFecha> arrayListfecha = new ArrayListGenerico<>(); //ArrayList de fechas. Es un arrayList auxiliar creado para garantizar que no se repitan columnas-fechas en mi tabla.
        for(int i = 0; i < this.getTraerTodoAsistenciaArrayListGenerico().tamanio(); i++){ //Itero el arrayList que contiene a todos los registros de la BDD.
            LocalDate fechaClase = this.getTraerTodoAsistenciaArrayListGenerico().retornarUnElementoPorPosicion(i).getFechaClase(); //Almaceno la fecha del presente registro.
            if(!arrayListfecha.contieneElemento(fechaClase)){ //Si el arrayList auxiliar NO contiene la fecha...
                TraerTodoAsistenciaLaParteFecha TTPF = new TraerTodoAsistenciaLaParteFecha(fechaClase);
                arrayListfecha.agregar(TTPF); //Agrego la fecha al arrayListAuxiliar.
                TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>> columnDinamica = new TableColumn<>(fechaClase.toString()); //Creo una nueva columna que tendrá, como título, la fecha de la clase.
                columnDinamica.setCellValueFactory(new PropertyValueFactory<>("asistio"));
                arrayListColumnas.agregar(columnDinamica);
            }

        }
        return arrayListColumnas;
    }

    public TableView<TraerTodoAsistenciaPasandoFechaADiccionario> agregarDatosALaTabla()
    {
        this.initialize();
        for(int i = 0; i < this.getArrayPasandofechaADic().tamanio(); i++){ //Tengo que pasar todos mis registros del arrayList nuevo(con diccionario) al observablelist. Pues, el nodo TableView solo admite la colección observableList, que es un arrayList de javaFX.
            TraerTodoAsistenciaPasandoFechaADiccionario TTAPFD = this.getArrayPasandofechaADic().retornarUnElementoPorPosicion(i);
            this.getObservableList().add(TTAPFD);
        }
        this.getTableViewAsistencia().setItems(this.getObservableList());
        return  this.getTableViewAsistencia();
    }

    public static boolean esPosibleCrearTabla(int cursoID)
    {
        boolean posible = false;
        boolean existeClases = General.existeTabla("clases");
        boolean existeEstudiantes = General.existeTabla("estudiantes");
        boolean existeIntermedia = General.existeTabla("tablaintermediaasistenciaestudiantesxclases");
        if(existeIntermedia && existeClases && existeEstudiantes){ //Primero, garantizo que existan las tablas; sino, me tirará un error.
            posible = TablaIntermediaAsistenciaEstudiantesXClaseControlador.existenregistrosParaCursoID(cursoID);
        }
        return posible;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public TableView<TraerTodoAsistenciaPasandoFechaADiccionario> getTableViewAsistencia() {
        return tableViewAsistencia;
    }

    public void setTableViewAsistencia(TableView<TraerTodoAsistenciaPasandoFechaADiccionario> tableViewAsistencia) {
        this.tableViewAsistencia = tableViewAsistencia;
    }

    public TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, String> getColNombreAlumno() {
        return colNombreAlumno;
    }

    public void setColNombreAlumno(TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, String> colNombreAlumno) {
        this.colNombreAlumno = colNombreAlumno;
    }

    public ObservableList<TraerTodoAsistenciaPasandoFechaADiccionario> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<TraerTodoAsistenciaPasandoFechaADiccionario> observableList) {
        this.observableList = observableList;
    }

    public ArrayListGenerico<TraerTodoAsistencia> getTraerTodoAsistenciaArrayListGenerico() {
        return traerTodoAsistenciaArrayListGenerico;
    }

    public void setTraerTodoAsistenciaArrayListGenerico(ArrayListGenerico<TraerTodoAsistencia> traerTodoAsistenciaArrayListGenerico) {
        this.traerTodoAsistenciaArrayListGenerico = traerTodoAsistenciaArrayListGenerico;
    }

    public ArrayListGenerico<TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>>> getArrayListColumnas() {
        return arrayListColumnas;
    }

    public void setArrayListColumnas(ArrayListGenerico<TableColumn<TraerTodoAsistenciaPasandoFechaADiccionario, HashMap<LocalDate, Boolean>>> arrayListColumnas) {
        this.arrayListColumnas = arrayListColumnas;
    }

    public ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> getArrayPasandofechaADic() {
        return arrayPasandofechaADic;
    }

    public void setArrayPasandofechaADic(ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> arrayPasandofechaADic) {
        this.arrayPasandofechaADic = arrayPasandofechaADic;
    }
}
