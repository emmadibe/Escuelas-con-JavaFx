package Controladores;

import ClasesPrincipales.ArrayListGenerico;
import ClasesPrincipales.TablaIntermediaEstudianteXExamen;
import Interfaces.Controladores;
import Modelos.General;
import Modelos.TablaIntermediaEstudianteXCursoModelo;
import Modelos.TablaIntermediaEstudianteXExamenModelo;
import javafx.scene.control.Tab;

public class TablaIntermediaEstudianteXExamenControlador implements Controladores<TablaIntermediaEstudianteXExamen>
{
    private int estudianteID;
    private int examenID;
    private int Nota;
    public TablaIntermediaEstudianteXExamenControlador(){}
    public TablaIntermediaEstudianteXExamenControlador(int estudianteID, int examenID, int nota)
    {
        this.setEstudianteID(estudianteID);
        this.setExamenID(examenID);
        this.setNota(nota);
    }

    TablaIntermediaEstudianteXExamenModelo tablaIntermediaEstudianteXExamenModelo = new TablaIntermediaEstudianteXExamenModelo();
    @Override
    public TablaIntermediaEstudianteXExamen traerRegistroAPartirDeID(int id) {
        return null;
    }

    @Override
    public TablaIntermediaEstudianteXExamen traerUnRegistro(TablaIntermediaEstudianteXExamen tablaIntermediaEstudianteXExamen) {
        return null;
    }

    @Override
    public boolean existeRegistro(TablaIntermediaEstudianteXExamen tablaIntermediaEstudianteXExamen) {
        return false;
    }

    @Override
    public void eliminarRegistro(TablaIntermediaEstudianteXExamen tablaIntermediaEstudianteXExamen) {

    }

    @Override
    public ArrayListGenerico traerTodos()
    {
        return null;
    }

    @Override
    public int traerIdUltimoRegistro() {
        return 0;
    }

    @Override
    public boolean existeTabla()
    {
        return General.existeTabla("TablaIntermediaEstudianteXExamen");
    }
    public void actualizarNota()
    {
        tablaIntermediaEstudianteXExamenModelo.actualizarNota(this.getEstudianteID(), this.getExamenID(), this.getNota());
    }

    @Override
    public void editarRegistro(TablaIntermediaEstudianteXExamen tablaIntermediaEstudianteXExamen) {

    }

    @Override
    public void crearTabla()
    {
        tablaIntermediaEstudianteXExamenModelo.crearTablaBDD();
    }

    @Override
    public void agregarUnRegistro(TablaIntermediaEstudianteXExamen tablaIntermediaEstudianteXExamen)
    {
        if(!this.existeTabla()){
            this.crearTabla();
        }
        tablaIntermediaEstudianteXExamenModelo.crearregistroTablaIntermedia(tablaIntermediaEstudianteXExamen.getExamenID(), tablaIntermediaEstudianteXExamen.getEstudianteID(), tablaIntermediaEstudianteXExamen.getNota());
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public int getNota() {
        return Nota;
    }

    public void setNota(int nota) {
        Nota = nota;
    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }
}
