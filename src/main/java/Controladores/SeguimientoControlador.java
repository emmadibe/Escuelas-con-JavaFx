package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Seguimiento;
import Interfaces.Controladores;
import Modelos.General;
import Modelos.SeguimientoModelo;

public class SeguimientoControlador implements Controladores<Seguimiento>
{
    public SeguimientoModelo seguimientoModelo = new SeguimientoModelo();
    @Override
    public boolean existeTabla() {
        return General.existeTabla("seguimientos");
    }

    @Override
    public void crearTabla()
    {
        seguimientoModelo.crearTablaBDD();
    }

    @Override
    public boolean existeRegistro(Seguimiento seguimiento) {
        return false;
    }

    @Override
    public void eliminarRegistro(Seguimiento seguimiento) {

    }

    @Override
    public void editarRegistro(Seguimiento seguimiento) {

    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }

    @Override
    public Seguimiento traerUnRegistro(Seguimiento seguimiento) {
        return null;
    }

    @Override
    public void agregarUnRegistro(Seguimiento seguimiento)
    {
        if(!existeTabla()){
            this.crearTabla();
        }
        seguimientoModelo.agregarRegistroBDD(seguimiento);
    }

    @Override
    public int traerIdUltimoRegistro() {
        return 0;
    }

    @Override
    public Seguimiento traerRegistroAPartirDeID(int id) {
        return null;
    }
}
