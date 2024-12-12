package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Estudiante;
import Interfaces.Controladores;
import Modelos.EstudianteModelo;
import Modelos.General;

public class EstudianteControlador implements Controladores<Estudiante>
{
    private int cursoID;
    public EstudianteControlador(){}
    public EstudianteControlador(int cursoID)
    {
        this.setCursoID(cursoID);
    }

    EstudianteModelo estudianteModelo = new EstudianteModelo();
    @Override
    public Estudiante traerRegistroAPartirDeID(int id) {
        return null;
    }

    @Override
    public int traerIdUltimoRegistro() {
        return estudianteModelo.traerIdUltimoRegistroBDD();
    }

    @Override
    public Estudiante traerUnRegistro(Estudiante estudiante) {
        return null;
    }

    public ArrayListGenerico<Estudiante> traerTodosLosAlumnosDeUnExamen(int examenID)
    {
        return estudianteModelo.traerTodosLosAlumnosDeUnExamen(examenID);
    }

    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("estudiantes");
    }

    @Override
    public void agregarUnRegistro(Estudiante estudiante)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        estudianteModelo.agregarRegistroBDD(estudiante);
    }

    @Override
    public void crearTabla()
    {
        estudianteModelo.crearTablaBDD();
    }

    @Override
    public void editarRegistro(Estudiante estudiante) {

    }

    @Override
    public void eliminarRegistro(Estudiante estudiante) {

    }

    @Override
    public boolean existeRegistro(Estudiante estudiante) {
        return false;
    }

    @Override
    public ArrayListGenerico<Estudiante> traerTodos()
    {
        ArrayListGenerico<Estudiante> arrayListGenerico = estudianteModelo.traerTodosBDD(this.getCursoID());
        return arrayListGenerico;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }
}
