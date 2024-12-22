package ClasesPrincipales;

import java.util.Objects;

public class TraerTodo extends Estudiante
{
    private String nombreYnumeroExamen;
    private int nota;
    private int cursoID;
    private String nombreYapellido;
    private int numeroExamen;

    public TraerTodo(String nombreYApellido, int nota, String nombreYnumeroExamen, int numeroExamen)
    {
        this.setNombreYapellido(nombreYApellido);
        this.setNombreYnumeroExamen(nombreYnumeroExamen);
        this.setNota(nota);
        this.setNumeroExamen(numeroExamen);
    }

    @Override
    public String toString() {
        return "TraerTodo{" +
                "nombreYnumeroExamen='" + nombreYnumeroExamen + '\'' +
                ", nota=" + nota +
                ", cursoID=" + cursoID +
                ", nombreYapellido='" + nombreYapellido + '\'' +
                ", numeroExamen=" + numeroExamen +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraerTodo traerTodo)) return false;
        return nota == traerTodo.nota && cursoID == traerTodo.cursoID && Objects.equals(nombreYnumeroExamen, traerTodo.nombreYnumeroExamen) && Objects.equals(nombreYapellido, traerTodo.nombreYapellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreYnumeroExamen, nota, cursoID, nombreYapellido);
    }

    public String getNombreYnumeroExamen() {
        return nombreYnumeroExamen;
    }

    public void setNombreYnumeroExamen(String nombreYnumeroExamen) {
        this.nombreYnumeroExamen = nombreYnumeroExamen;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public int getNumeroExamen() {
        return numeroExamen;
    }

    public void setNumeroExamen(int numeroExamen) {
        this.numeroExamen = numeroExamen;
    }
}
