package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Clase;
import Interfaces.Controladores;
import Modelos.ClaseModelo;
import Modelos.General;

public class ClaseControlador implements Controladores<Clase>
{

    public ClaseModelo claseModelo = new ClaseModelo();
    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("clases");
    }

    @Override
    public void crearTabla()
    {
        claseModelo.crearTablaBDD();
    }

    @Override
    public boolean existeRegistro(Clase clase) {
        return false;
    }

    @Override
    public void eliminarRegistro(Clase clase) {

    }

    @Override
    public void editarRegistro(Clase clase) {

    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }

    @Override
    public Clase traerUnRegistro(Clase clase) {
        return null;
    }

    @Override
    public void agregarUnRegistro(Clase clase)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        claseModelo.agregarRegistroBDD(clase);
    }

    @Override
    public int traerIdUltimoRegistro() {
        return 0;
    }

    @Override
    public Clase traerRegistroAPartirDeID(int id) {
        return null;
    }
}
