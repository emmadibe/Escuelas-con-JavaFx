package ClasesPrincipales;

public class TraerTodo extends Estudiante
{
    private String nombreYnumeroExamen;
    private int nota;
    private int cursoID;
    private String nombreYapellido;

    public TraerTodo(String nombreYApellido, int nota, String nombreYnumeroExamen)
    {
        this.setNombreYapellido(nombreYApellido);
        this.setNombreYnumeroExamen(nombreYnumeroExamen);
        this.setNota(nota);
    }

    @Override
    public String toString() {
        return "TraerTodo{" +
                "nombreYnumeroExamen='" + nombreYnumeroExamen + '\'' +
                ", nota=" + nota +
                ", nombre y apellido ='" + this.getNombreYapellido() + '\'' +
                '}';
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
}
