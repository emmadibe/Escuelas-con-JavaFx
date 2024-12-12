package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Docente;
import Interfaces.Controladores;
import Interfaces.Modelos;
import Modelos.General;
import Modelos.DocenteModelo;

import javax.print.Doc;

public class DocenteControlador implements Controladores<Docente>
{
    DocenteModelo docenteModelo = new DocenteModelo();
    @Override
    public void agregarUnRegistro(Docente docente)
    {
        if(!existeTabla()){
            this.crearTabla();
        }
        docenteModelo.agregarRegistroBDD(docente);
    }

    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("Docente");
    }

    @Override
    public void crearTabla()
    {
        docenteModelo.crearTablaBDD();
    }

    @Override
    public void editarRegistro(Docente docente) {

    }

    @Override
    public void eliminarRegistro(Docente docente) {

    }

    @Override
    public int traerIdUltimoRegistro() {
        return 0;
    }

    @Override
    public Docente traerRegistroAPartirDeID(int id) {
        Docente docente = docenteModelo.traerRegistroAPartirDeIDBDD(id);
        return docente;
    }

    @Override
    public boolean existeRegistro(Docente docente)
    {
        return docenteModelo.existeRegistroBDD(docente);
    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }

    @Override
    public Docente traerUnRegistro(Docente docente)
    {
        Docente docenteConTodosLosDatos = null;
        if(this.existeRegistro(docente)){
            docenteConTodosLosDatos = docenteModelo.traerRegistroBDD(docente);
        }
        return docenteConTodosLosDatos;
    }

}
