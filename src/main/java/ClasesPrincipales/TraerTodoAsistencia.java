package ClasesPrincipales;

import java.time.LocalDate;

public class TraerTodoAsistencia
{
    private int cursoID;
    private String nombreYapellidoAlumno;
    private LocalDate fechaClase;
    private boolean asistio;

    public TraerTodoAsistencia(int cursoID, String nombreYapellidoAlumno, LocalDate fechaClase, boolean asistio)
    {
        this.setAsistio(asistio);
        this.setFechaClase(fechaClase);
        this.setNombreYapellidoAlumno(nombreYapellidoAlumno);
        this.setCursoID(cursoID);
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public String getNombreYapellidoAlumno() {
        return nombreYapellidoAlumno;
    }

    public void setNombreYapellidoAlumno(String nombreYapellidoAlumno) {
        this.nombreYapellidoAlumno = nombreYapellidoAlumno;
    }

    public LocalDate getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(LocalDate fechaClase) {
        this.fechaClase = fechaClase;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }
}
