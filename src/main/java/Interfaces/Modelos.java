package Interfaces;
import ClasesPrincipales.ArrayListGenerico;

public interface Modelos<T>
{
    public void crearTablaBDD();
    public void agregarRegistroBDD(T t); //Le paso como parámetro el registro que deseo agrear a la bdd.
    public void existeTablaBDD();
    public void borrarRegistroBDD(T t);
    public void editarRegistroBDD(T t);
    public T traerRegistroBDD(T t); //Siempre voy a necesitar, en algún momento, traerme algún registro de la base de datos. Traerme un docente, un curso, unalumno, un examen, etc.
    public ArrayListGenerico traerTodosBDD(int id);
    public boolean existeRegistroBDD(T t);
    public int traerIdUltimoRegistroBDD();
    public T traerRegistroAPartirDeIDBDD(int id);
}
