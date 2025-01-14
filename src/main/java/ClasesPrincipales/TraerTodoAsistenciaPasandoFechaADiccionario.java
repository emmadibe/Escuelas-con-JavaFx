package ClasesPrincipales;

import Excepciones.ArrayListVacioException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

public class TraerTodoAsistenciaPasandoFechaADiccionario
{
    private String nombreYapellidoAlumno;
    private HashMap<LocalDate, Boolean> asistioMapa;

    public ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> arrayListFechasDic = new ArrayListGenerico<>(); //Es un arrayList en donde cada elemento es una instancia de TraerTodoAsistenciaPAsandoFechaADiccionario.

    public TraerTodoAsistenciaPasandoFechaADiccionario(){}

    public TraerTodoAsistenciaPasandoFechaADiccionario(String nombreYapellidoAlumno, HashMap<LocalDate, Boolean> hasMapFechas)
    {
        this.setAsistioMapa(hasMapFechas);
        this.setNombreYapellidoAlumno(nombreYapellidoAlumno);
    }

    public ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> pasarfechasADiccionario(ArrayListGenerico<TraerTodoAsistencia> arrayListOriginal) //Convierte un arrayList en otro.
    {
        for(int i = 0; i < arrayListOriginal.tamanio(); i++){
            String nombreYApellido = arrayListOriginal.retornarUnElementoPorPosicion(i).getNombreYapellidoAlumno();
            LocalDate fecha = arrayListOriginal.retornarUnElementoPorPosicion(i).getFechaClase();
            boolean asistio = arrayListOriginal.retornarUnElementoPorPosicion(i).isAsistio();
            int existe = this.buscarNombreAlumno(this.arrayListFechasDic, nombreYApellido);
            if(existe == -1){//Si retorna -1, no existe un elemento en el arrayList que contenga ese nombre como atributo. Si existe, retorna el índice en donde se encuentra.
                HashMap<LocalDate, Boolean> fechaMapa = new HashMap<>();
                fechaMapa.put(fecha, asistio);
                TraerTodoAsistenciaPasandoFechaADiccionario TTPFD = new TraerTodoAsistenciaPasandoFechaADiccionario(nombreYApellido, fechaMapa);
                this.arrayListFechasDic.agregar(TTPFD);
            }else{
                this.arrayListFechasDic.retornarUnElementoPorPosicion(existe).getAsistioMapa().put(fecha, asistio); //Si ya existe el alumnop, debo agregar la clase y la asistencia y en el índice que está dicho alumno.
            }
        }
        return this.arrayListFechasDic;
    }

    public int buscarNombreAlumno(ArrayListGenerico<TraerTodoAsistenciaPasandoFechaADiccionario> array, String nombreYApellido)
    {
        int existe = -1;
        for(int i = 0; i < array.tamanio(); i++){
            if(!array.estaVacio()){ //Siempre debo corroborar que el array no esté vacío. Pues, me tira una excepción.
                if(Objects.equals(nombreYApellido, array.retornarUnElementoPorPosicion(i).getNombreYapellidoAlumno())){
                    existe = i;
                    break; //PAra que no siga recorriendo el arrayList al pedo.
                }
            }
        }
        return existe;
    }

    @Override
    public String toString() {
        return "TraerTodoAsistenciaPasandoFechaADiccionario{" +
                "nombreYapellidoAlumno='" + nombreYapellidoAlumno + '\'' +
                ", asistioMapa=" + asistioMapa +
                ", arrayListFechasDic=" + arrayListFechasDic +
                '}';
    }

    public String getNombreYapellidoAlumno() {
        return nombreYapellidoAlumno;
    }

    public void setNombreYapellidoAlumno(String nombreYapellidoAlumno) {
        this.nombreYapellidoAlumno = nombreYapellidoAlumno;
    }

    public HashMap<LocalDate, Boolean> getAsistioMapa() {
        return asistioMapa;
    }

    public void setAsistioMapa(HashMap<LocalDate, Boolean> asistioMapa) {
        this.asistioMapa = asistioMapa;
    }
}
