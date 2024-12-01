package Modelos;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Docente;
import Interfaces.Modelos;

public class DocenteModelo extends General implements Modelos<Docente>
{
    @Override
    public void crearTablaBDD() {

    }

    @Override
    public Docente traerRegistroBDD(int id) {
        return null;
    }

    @Override
    public ArrayListGenerico traerTodosBDD() {
        return null;
    }

    @Override
    public void agregarRegistroBDD(Docente docente) {

    }

    @Override
    public void borrarRegistroBDD(Docente docente) {

    }

    @Override
    public void editarRegistroBDD(Docente docente) {

    }

    @Override
    public void existeTablaBDD() {

    }

    @Override
    public boolean existeRegistroBDD(Docente docente) {
        return false;
    }
}
