package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Docente;
import Interfaces.Controladores;
import Modelos.CursoModelo;
import Modelos.ExamenModelo;
import Modelos.General;

public class CursoControlador implements Controladores<Curso>
{
    private Curso curso;
    private int docenteID;
    public CursoControlador(){}
    public CursoControlador(Curso curso)
    {
        this.setCurso(curso);
        this.setDocenteID(curso.getDocenteID());
    }

    @Override
    public Curso traerRegistroAPartirDeID(int id) {
        return cursoModelo.traerRegistroAPartirDeIDBDD(id);
    }

    public CursoControlador(Docente docente)
    {
        this.setDocenteID(docente.getID());
    }
    public CursoModelo cursoModelo = new CursoModelo();
    @Override
    public boolean existeRegistro(Curso curso) {
        return false;
    }

    @Override
    public void eliminarRegistro(Curso curso) {

    }

    @Override
    public ArrayListGenerico traerTodos()
    {
        ArrayListGenerico arrayListGenerico = new ArrayListGenerico<Curso>();
        arrayListGenerico = cursoModelo.traerTodosBDD(this.getDocenteID());
        return arrayListGenerico;
    }

    public static boolean existenExamenesEnElCurso(int cursoID)
    {
        return ExamenModelo.existenRegistrosParaUnCurso(cursoID);
    }

    @Override
    public void editarRegistro(Curso curso) {

    }

    @Override
    public void crearTabla()
    {
        cursoModelo.crearTablaBDD();
    }

    @Override
    public boolean existeTabla() {
        return General.existeTabla("cursos");
    }

    @Override
    public Curso traerUnRegistro(Curso curso) {
        return null;
    }

    @Override
    public void agregarUnRegistro(Curso curso)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        cursoModelo.agregarRegistroBDD(curso);
    }

    @Override
    public int traerIdUltimoRegistro() {
        return cursoModelo.traerIdUltimoRegistroBDD();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getDocenteID() {
        return docenteID;
    }

    public void setDocenteID(int docenteID) {
        this.docenteID = docenteID;
    }
}
