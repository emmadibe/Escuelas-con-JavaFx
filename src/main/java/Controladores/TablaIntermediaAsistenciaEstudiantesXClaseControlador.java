package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.Curso;
import ClasesPrincipales.Estudiante;
import ClasesPrincipales.TablaIntermediaAsistenciaEstudiantesXClases;
import Interfaces.Controladores;
import Modelos.General;
import Modelos.TablaIntermediaAsistenciaEstudiantesXClasesModelo;

public class TablaIntermediaAsistenciaEstudiantesXClaseControlador implements Controladores<TablaIntermediaAsistenciaEstudiantesXClases>
{
    TablaIntermediaAsistenciaEstudiantesXClasesModelo TIAEXCM = new TablaIntermediaAsistenciaEstudiantesXClasesModelo();
    @Override
    public boolean existeTabla() {
        return General.existeTabla("tablaIntermediaAsistenciaEstudiantesXClases");
    }

    @Override
    public void crearTabla() {
        TablaIntermediaAsistenciaEstudiantesXClasesModelo.crearTabla();
    }

    @Override
    public boolean existeRegistro(TablaIntermediaAsistenciaEstudiantesXClases tablaIntermediaAsistenciaEstudiantesXClases) {
        return false;
    }

    @Override
    public void eliminarRegistro(TablaIntermediaAsistenciaEstudiantesXClases tablaIntermediaAsistenciaEstudiantesXClases) {

    }

    @Override
    public void editarRegistro(TablaIntermediaAsistenciaEstudiantesXClases tablaIntermediaAsistenciaEstudiantesXClases) {

    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }

    @Override
    public TablaIntermediaAsistenciaEstudiantesXClases traerUnRegistro(TablaIntermediaAsistenciaEstudiantesXClases tablaIntermediaAsistenciaEstudiantesXClases) {
        return null;
    }

    @Override
    public void agregarUnRegistro(TablaIntermediaAsistenciaEstudiantesXClases tablaIntermediaAsistenciaEstudiantesXClases)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        TIAEXCM.agregarRegistroBDD(tablaIntermediaAsistenciaEstudiantesXClases);
    }

    @Override
    public int traerIdUltimoRegistro()
    {
        return TIAEXCM.traerIDUltimoRegistro();
    }

    public void cargarTodo(Curso curso, int claseID) //En este método voy a cargar todos los registros de Asistencia. Cuando se crea una nueva clase se debe, de forma automática, crear todos los registros intermedios entre dicha clase y cada estudiante del curso para ver si asistió o no.
    {
        //Primero, debo ver cómo hago para traerme un arrayList con todos los estudiantes que compartan curso:
        TablaIntermediaAsistenciaEstudiantesXClaseControlador TIEXCC = new TablaIntermediaAsistenciaEstudiantesXClaseControlador();
        EstudianteControlador estudianteControlador = new EstudianteControlador(curso.getID());
        ArrayListGenerico<Estudiante> estudianteArrayListGenerico = new ArrayListGenerico<>(); //En este arrayList almacenaré a todos los estudiantes del curso. Un índice, un estudiante.
        estudianteArrayListGenerico = estudianteControlador.traerTodos(); //Listo. Ya tengo en el arrayList a todos los estudiantes que comparten ese cursoID.
        TablaIntermediaAsistenciaEstudiantesXClaseControlador TIAEXCC = new TablaIntermediaAsistenciaEstudiantesXClaseControlador();
        for(int i = 0; i < estudianteArrayListGenerico.tamanio(); i++){
            Estudiante estudiante = estudianteArrayListGenerico.retornarUnElementoPorPosicion(i); //Tengo un estudiante.
            TablaIntermediaAsistenciaEstudiantesXClases TIAEXC = new TablaIntermediaAsistenciaEstudiantesXClases(estudiante.getID(), claseID, (byte) 1); //Tengo una instancia de TI.
            TIEXCC.agregarUnRegistro(TIAEXC); //Agrego el registro a la base de datos. 
        }
    }

    @Override
    public TablaIntermediaAsistenciaEstudiantesXClases traerRegistroAPartirDeID(int id) {
        return null;
    }
}
