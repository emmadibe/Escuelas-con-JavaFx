package Interfaces;

import ClasesPrincipales.ArrayListGenerico;

public interface Controladores<T>
{
    public boolean existeTabla();
    public void crearTabla();
    public void existeRegistro(T t);
    public void eliminarRegistro(T t);
    public void editarRegistro(T t);
    public ArrayListGenerico traerTodos();
    public void traerUnRegistro();
    public void agregarUnRegistro(T t);
}
