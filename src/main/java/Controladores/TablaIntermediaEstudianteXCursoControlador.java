package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TablaIntermediaEstudianteXCurso;
import Interfaces.Controladores;
import Modelos.General;
import Modelos.TablaIntermediaEstudianteXCursoModelo;

public class TablaIntermediaEstudianteXCursoControlador implements Controladores<TablaIntermediaEstudianteXCurso>
{
    TablaIntermediaEstudianteXCursoModelo TIEXC = new TablaIntermediaEstudianteXCursoModelo();
    @Override
    public boolean existeRegistro(TablaIntermediaEstudianteXCurso tablaIntermediaEstudianteXCurso) {
        return false;
    }

    @Override
    public void eliminarRegistro(TablaIntermediaEstudianteXCurso tablaIntermediaEstudianteXCurso) {

    }

    @Override
    public ArrayListGenerico traerTodos() {
        return null;
    }
    @Override
    public void crearTabla()
    {
        TIEXC.crearTablaIntermedia();
    }

    @Override
    public TablaIntermediaEstudianteXCurso traerUnRegistro(TablaIntermediaEstudianteXCurso tablaIntermediaEstudianteXCurso) {
        return null;
    }

    @Override
    public TablaIntermediaEstudianteXCurso traerRegistroAPartirDeID(int id) {
        return null;
    }

    @Override
    public void editarRegistro(TablaIntermediaEstudianteXCurso tablaIntermediaEstudianteXCurso) {

    }

    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("TablaIntermediaEstudiantesXCurso");
    }

    @Override
    public void agregarUnRegistro(TablaIntermediaEstudianteXCurso tablaIntermediaEstudianteXCurso)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        TIEXC.crearregistroTablaIntermedia(tablaIntermediaEstudianteXCurso.getEstudianteID(), tablaIntermediaEstudianteXCurso.getCursoID());
    }

    @Override
    public int traerIdUltimoRegistro() {
        return 0;
    }
    
}
