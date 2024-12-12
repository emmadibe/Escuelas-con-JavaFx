package Interfaces;

import ClasesPrincipales.ArrayListGenerico;

public interface Controladores<T>
{
    public boolean existeTabla();
    public void crearTabla();
    public boolean existeRegistro(T t);
    public void eliminarRegistro(T t);
    public void editarRegistro(T t);
    public ArrayListGenerico traerTodos();
    public T traerUnRegistro(T t);
    public void agregarUnRegistro(T t);
    public int traerIdUltimoRegistro();
    public T traerRegistroAPartirDeID(int id);
}
