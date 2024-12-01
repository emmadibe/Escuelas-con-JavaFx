package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Docente;
import Interfaces.Controladores;
import Interfaces.Modelos;
import Modelos.General;

public class DocenteControlador implements Controladores<Docente>
{
    @Override
    public void agregarUnRegistro(Docente docente)
    {
        if(!existeTabla()){

        }
    }

    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("Docente");
    }

    @Override
    public void crearTabla()
    {

    }

    @Override
    public void editarRegistro(Docente docente) {

    }

    @Override
    public void eliminarRegistro(Docente docente) {

    }

    @Override
    public void existeRegistro(Docente docente) {

    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }

    @Override
    public void traerUnRegistro() {

    }

}
