package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Examen;
import Interfaces.Controladores;
import Modelos.ExamenModelo;
import Modelos.General;

public class ExamenControlador implements Controladores<Examen>
{
    private int cursoID;
    public ExamenModelo examenModelo = new ExamenModelo();

    public ExamenControlador(){}
    public ExamenControlador(int cursoID)
    {
        this.setCursoID(cursoID);
    }
    @Override
    public void agregarUnRegistro(Examen examen)
    {
        if(! this.existeTabla()){
            this.crearTabla();
        }
        examenModelo.agregarRegistroBDD(examen);
    }

    @Override
    public boolean existeTabla() {
        return General.existeTabla("examen");
    }

    @Override
    public void editarRegistro(Examen examen) {

    }

    @Override
    public int traerIdUltimoRegistro() {
        return examenModelo.traerIdUltimoRegistroBDD();
    }

    @Override
    public Examen traerUnRegistro(Examen examen) {
        return null;
    }

    @Override
    public Examen traerRegistroAPartirDeID(int id) {
        return null;
    }

    @Override
    public ArrayListGenerico traerTodos()
    {
        return examenModelo.traerTodosBDD(this.getCursoID());
    }

    @Override
    public void eliminarRegistro(Examen examen) {

    }

    @Override
    public boolean existeRegistro(Examen examen) {
        return false;
    }

    @Override
    public void crearTabla()
    {
        examenModelo.crearTablaBDD();
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }
}
