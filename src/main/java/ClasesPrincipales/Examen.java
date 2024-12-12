package ClasesPrincipales;

import Interfaces.ClasesGenerales;

public class Examen implements ClasesGenerales<Examen>
{
    private int id;
    private String nombre;
    private int numeroExamen;
    private int cursoID;

    public Examen(){}
    public Examen(int id, int cursoID, String nombre, int numeroExamen)
    {
        this.setId(id);
        this.setCursoID(cursoID);
        this.setNumeroExamen(numeroExamen);
        this.setNombre(nombre);
    }
    public Examen(int cursoID, String nombre, int numeroExamen)
    {
        this.setNombre(nombre);
        this.setNumeroExamen(numeroExamen);
        this.setCursoID(cursoID);
    }

    @Override
    public void imprimirUnaInstancia()
    {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroExamen() {
        return numeroExamen;
    }

    public void setNumeroExamen(int numeroExamen) {
        this.numeroExamen = numeroExamen;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
