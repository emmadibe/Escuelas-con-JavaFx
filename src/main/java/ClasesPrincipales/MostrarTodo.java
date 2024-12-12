package ClasesPrincipales;

public class MostrarTodo
{
    int estudianteID;
    String estudianteNombre;
    int examenID;
    int numeroExamen;
    String nombreExamen;
    int nota;

    public MostrarTodo(int estudianteID, String estudianteNombre, int examenID, int numeroExamen, String nombreExamen, int nota)
    {
        this.setEstudianteID(estudianteID);
        this.setNota(nota);
        this.setNombreExamen(nombreExamen);
        this.setExamenID(examenID);
        this.setNumeroExamen(numeroExamen);
        this.setEstudianteNombre(estudianteNombre);
    }

    @Override
    public String toString() {
        return "MostrarTodo{" +
                "estudianteID=" + estudianteID +
                ", estudianteNombre='" + estudianteNombre + '\'' +
                ", examenID=" + examenID +
                ", numeroExamen=" + numeroExamen +
                ", nombreExamen='" + nombreExamen + '\'' +
                ", nota=" + nota +
                '}';
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public int getNumeroExamen() {
        return numeroExamen;
    }

    public void setNumeroExamen(int numeroExamen) {
        this.numeroExamen = numeroExamen;
    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }
}
