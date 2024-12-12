package ClasesPrincipales;

import ENUMS.Rama;
import Interfaces.ClasesGenerales;

public class Curso implements ClasesGenerales<Curso>
{
    private int ID;
    private String nombre;
    private int cantAlumnos;
    private String escuela;
    private String materia;
    private int docenteID;

    public Curso(){}
    public Curso(int id, String nombre, int cantAlumnos, String escuela, String materia, int docenteID)
    {
        this.setID(id);
        this.setCantAlumnos(cantAlumnos);
        this.setEscuela(escuela);
        this.setMateria(materia);
        this.setNombre(nombre);
        this.setDocenteID(docenteID);
    }
    @Override
    public void imprimirUnaInstancia()
    {

    }

    @Override
    public String toString() {
        return "Curso{" +
                "ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", cantAlumnos=" + cantAlumnos +
                ", escuela='" + escuela + '\'' +
                ", materia='" + materia + '\'' +
                ", docenteID=" + docenteID +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCantAlumnos() {
        return cantAlumnos;
    }

    public void setCantAlumnos(int cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getDocenteID() {
        return docenteID;
    }

    public void setDocenteID(int docenteID) {
        this.docenteID = docenteID;
    }
}
