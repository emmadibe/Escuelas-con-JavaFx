package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TraerTodo;
import ClasesPrincipales.TraerTodoLaParteExamenes;
import ClasesPrincipales.TraerTodoPasandoNotasADiccionario;
import Excepciones.ArrayListVacioException;
import Modelos.General;
import Modelos.TraerTodoModelo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TraerTodoControlador {
    private int cursoID;
    private TableView<TraerTodoPasandoNotasADiccionario> todoTableView;
    private ObservableList<TraerTodoPasandoNotasADiccionario> observableList;
    private TableColumn<TraerTodoPasandoNotasADiccionario, String> colNombreAlumno;
    private ArrayListGenerico<TraerTodo> arrayListTraerTodo;
    private ArrayListGenerico<TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>> arrayListColumnas;
    private ArrayListGenerico<TraerTodoPasandoNotasADiccionario> TTPND;

    public TraerTodoControlador(int cursoID) {
        this.setCursoID(cursoID);
    }

    public void initialize() {
        this.setTodoTableView(new TableView<>()); //Inicializo la tabla. Este es el nodo que utilizaré para mostrar mis datos.
        this.observableList = FXCollections.observableArrayList(); //Inicializo el ObservableList
        TraerTodoPasandoNotasADiccionario TTPasandoNotas = new TraerTodoPasandoNotasADiccionario();//Instancio. Luego, en TTPasandoNotas tendré mis registros.

        // Configuración de la columna "Nombre y apellido"
        this.setColNombreAlumno(new TableColumn<>("Nombre y apellido")); //A este columna le seteo un nombre cableado porque ya sé que debe tener ese nombre
        this.colNombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreYapellido")); //Así, los valores de los registros de la columna Nombre y apellido serán los que retorne el getter nombreYapellido.

        // Cargar datos y crear columnas dinámicas
        this.setArrayListTraerTodo(TraerTodoModelo.traerATodos(this.getCursoID())); //Ya tengo todos los registros de mi BDD en el formato original, que no sirve.
        this.setTTPND(TTPasandoNotas.pasarNotasADiccionario(this.getArrayListTraerTodo())); //Convierto el arrayList original a otro nuevo, con un mejor formato en donde tengo tantos registros como alumnos posea el curso. Es un formato de guardar los datos más sencillo para trabajar.Y dentro de ese arrayList nuevo hay, como atributo, una colección de tipo diccionario(HashMap) en donde la clave es el examen; y el valor, la nota.


        //Columnas:

        this.setArrayListColumnas(this.crearColumnasDinamicas()); //Tengo mis columnas, NO mis registros. O sea, los nombres de las columnas sin valores en las filas.

        // Agregar columnas a la tabla
        this.agregarColumnasALaTablaDeFormaDinamica();
    }

    public void agregarColumnasALaTablaDeFormaDinamica() {
        this.todoTableView.getColumns().add(this.getColNombreAlumno()); //Esa columna ya sé que la debo tener. Es la columna "nombre alumno"
        for (int i = 0; i < this.getArrayListColumnas().tamanio(); i++) { //Voy agregando columnas a mi tabla. Cada columna será el nombre de un examen.
            this.todoTableView.getColumns().add(this.getArrayListColumnas().retornarUnElementoPorPosicion(i));  //En cada bucle agrego una nueva columna a mi tabla.
        }
    }

    public ArrayListGenerico<TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>> crearColumnasDinamicas() { //En este método voy a crear las columnas de mi tabla correspondientes a cada examen. Un examen es igual a una columna.
        ArrayListGenerico<TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>> arrayListColumnas = new ArrayListGenerico<>(); //En este arrayList, cada elemento es una columna
        ArrayListGenerico<TraerTodoLaParteExamenes> arrayListExamen = new ArrayListGenerico<>(); //ArayList de examen. Es un arraylist auxiliar que cree para garantizar que no se repitan olumnas-exámenes en mi tabla. 

        for (int i = 0; i < this.getArrayListTraerTodo().tamanio(); i++) {
            String nombreYnumeroExamen = this.getArrayListTraerTodo().retornarUnElementoPorPosicion(i).getNombreYnumeroExamen();
            TraerTodoLaParteExamenes traerTodoLaParteExamenes = new TraerTodoLaParteExamenes(nombreYnumeroExamen);

            if (!arrayListExamen.contieneElemento(traerTodoLaParteExamenes)) {
                TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>> columnaNueva = new TableColumn<>(nombreYnumeroExamen);
                columnaNueva.setCellValueFactory(new PropertyValueFactory<>("notamapa"));

                columnaNueva.setCellFactory(column -> new TableCell<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>() {
                    @Override
                    protected void updateItem(HashMap<String, Integer> item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            TraerTodoPasandoNotasADiccionario objetoActual = getTableView().getItems().get(getIndex());
                            Integer nota = objetoActual.getNotamapa().get(nombreYnumeroExamen);
                            setText(nota != null ? String.valueOf(nota) : null);
                        }
                    }
                });

                arrayListColumnas.agregar(columnaNueva);
                arrayListExamen.agregar(traerTodoLaParteExamenes);
            }
        }
        return arrayListColumnas;
    }

    public TableView<TraerTodoPasandoNotasADiccionario> agregarDatosALaTabla() {
        this.initialize();

        for (int i = 0; i < this.getTTPND().tamanio(); i++) { //Debo pasar todos mis registros al observableList. Pues, el nodo TableView lo que admite es observableList, que es un arraylist de JavaFx.
            TraerTodoPasandoNotasADiccionario traerTodo = this.getTTPND().retornarUnElementoPorPosicion(i);
            this.getObservableList().add(traerTodo);
        }
        // Establecer el ObservableList en la tabla
        this.getTodoTableView().setItems(this.getObservableList());

        return todoTableView;
    }


    public static boolean esPosibleCrearTabla(int cursoID) //Quiero averiguar si el curso ya tiene alumnos y exámenes para poder armar la tabla.
    {
        boolean posible = false;

        boolean existeEstudiantes = General.existeTabla("estudiantes");
        boolean existeExamenes = General.existeTabla("examenes");
        boolean existeTIEXE = General.existeTabla("tablaintermediaestudiantexexamen");
        boolean existeTIEXC = General.existeTabla("tablaintermediaestudiantesxcursos");
        //Primero, debo corroborar que existan las tablas:
        if(existeTIEXC && existeTIEXE && existeEstudiantes && existeExamenes){
            //Luego de corroborar que existan las tablas, compruebo que haya regitros para este cursoID.
            posible = TraerTodoModelo.existenRegistrosParaCursoID(cursoID);
        }
        return posible;
    }

    // Getters y Setters
    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public TableView<TraerTodoPasandoNotasADiccionario> getTodoTableView() {
        return todoTableView;
    }

    public void setTodoTableView(TableView<TraerTodoPasandoNotasADiccionario> todoTableView) {
        this.todoTableView = todoTableView;
    }

    public ObservableList<TraerTodoPasandoNotasADiccionario> getObservableList() {
        return observableList;
    }

    public void setObservableList(ObservableList<TraerTodoPasandoNotasADiccionario> observableList) {
        this.observableList = observableList;
    }

    public TableColumn<TraerTodoPasandoNotasADiccionario, String> getColNombreAlumno() {
        return colNombreAlumno;
    }

    public void setColNombreAlumno(TableColumn<TraerTodoPasandoNotasADiccionario, String> colNombreAlumno) {
        this.colNombreAlumno = colNombreAlumno;
    }

    public ArrayListGenerico<TraerTodo> getArrayListTraerTodo() {
        return arrayListTraerTodo;
    }

    public void setArrayListTraerTodo(ArrayListGenerico<TraerTodo> arrayListTraerTodo) {
        this.arrayListTraerTodo = arrayListTraerTodo;
    }

    public ArrayListGenerico<TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>> getArrayListColumnas() {
        return arrayListColumnas;
    }

    public void setArrayListColumnas(ArrayListGenerico<TableColumn<TraerTodoPasandoNotasADiccionario, HashMap<String, Integer>>> arrayListColumnas) {
        this.arrayListColumnas = arrayListColumnas;
    }

    public ArrayListGenerico<TraerTodoPasandoNotasADiccionario> getTTPND() {
        return TTPND;
    }

    public void setTTPND(ArrayListGenerico<TraerTodoPasandoNotasADiccionario> TTPND) {
        this.TTPND = TTPND;
    }
}