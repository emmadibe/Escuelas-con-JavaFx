package ClasesPrincipales;

import java.util.HashMap;
import java.util.Objects;

public class TraerTodoPasandoNotasADiccionario
{
    private String nombreYapellido;
    private HashMap<String, Integer> notamapa;

    public ArrayListGenerico<TraerTodoPasandoNotasADiccionario> arrayListpasadoADiccionario = new ArrayListGenerico<>(); //Es un arrayList en donde cada elemento es una instancia de TraerTddoPasandoNotasADiccionario.

    public TraerTodoPasandoNotasADiccionario(String nombreYapellido, HashMap<String, Integer> notamapa)
    {
        this.setNombreYapellido(nombreYapellido);
        this.setNotamapa(notamapa);
    }

    public TraerTodoPasandoNotasADiccionario(){}

    public ArrayListGenerico<TraerTodoPasandoNotasADiccionario> pasarNotasADiccionario(ArrayListGenerico<TraerTodo> arrayListSinPasar)
    {
        for(int i = 0; i < arrayListSinPasar.tamanio(); i++){ //itero mi arrayList.
            String nombreYapellido = arrayListSinPasar.retornarUnElementoPorPosicion(i).getNombreYapellido();
            String numeroYnombreExamen = arrayListSinPasar.retornarUnElementoPorPosicion(i).getNombreYnumeroExamen();
            int nota = arrayListSinPasar.retornarUnElementoPorPosicion(i).getNota();
            int existe = this.buscarNombreAlumno(this.arrayListpasadoADiccionario, nombreYapellido);
            if(existe == -1){ //Si retorna -1, no existe un elemento en el arrayList que contenga ese nombre como atributo. Si existe, retorna el índice en donde se encuentra.
                HashMap<String , Integer> notamapa = new HashMap<>();
                notamapa.put(numeroYnombreExamen, nota);
                TraerTodoPasandoNotasADiccionario TTPD = new TraerTodoPasandoNotasADiccionario(nombreYapellido, notamapa);
                this.arrayListpasadoADiccionario.agregar(TTPD); //Agrego una nueva instancia de TraerTodoPsando... a mi arrayList,
            }else{
                this.arrayListpasadoADiccionario.retornarUnElementoPorPosicion(existe).getNotamapa().put(numeroYnombreExamen, nota); //Si ya existe el alumno, debo agregarle la nota y el examen.
            }
        }
        return this.arrayListpasadoADiccionario;
    }

    public int buscarNombreAlumno(ArrayListGenerico<TraerTodoPasandoNotasADiccionario> array, String nombreYapellido)
    {
        int existe = -1;
        for(int i = 0; i < array.tamanio(); i++){
            if(!array.estaVacio()){ //Siempre, pero siempre, debo verificar que el aray no esté vacío.
                if(Objects.equals(nombreYapellido, array.retornarUnElementoPorPosicion(i).getNombreYapellido())){
                    existe = i;
                    break;
                }
            }
        }
        return existe;
    }

    @Override
    public String toString() {
        return "TraerTodoPasandoNotasADiccionario{" +
                "nombreYapellido='" + nombreYapellido + '\'' +
                ", notamapa=" + notamapa +
                ", arrayListpasadoADiccionario=" + arrayListpasadoADiccionario +
                '}';
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public HashMap<String, Integer> getNotamapa() {
        return notamapa;
    }

    public void setNotamapa(HashMap<String, Integer> notamapa) {
        this.notamapa = notamapa;
    }
}
