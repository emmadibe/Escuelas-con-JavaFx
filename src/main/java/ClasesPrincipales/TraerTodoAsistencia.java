package ClasesPrincipales;

import java.time.LocalDate;

public class TraerTodoAsistencia
{
    private int cursoID;
    private String nombreYapellidoAlumno;
    private LocalDate fechaClase;
    private boolean asistio;
    private int tablaIntermediaID;

    public TraerTodoAsistencia(int tablaIntermediaID, int cursoID, String nombreYapellidoAlumno, LocalDate fechaClase, boolean asistio)
    {
        this.setAsistio(asistio);
        this.setFechaClase(fechaClase);
        this.setNombreYapellidoAlumno(nombreYapellidoAlumno);
        this.setCursoID(cursoID);
        this.setTablaIntermediaID(tablaIntermediaID);
    }

    @Override
    public String toString() {
        return "TraerTodoAsistencia{" +
                "cursoID=" + cursoID +
                ", nombreYapellidoAlumno='" + nombreYapellidoAlumno + '\'' +
                ", fechaClase=" + fechaClase +
                ", asistio=" + asistio +
                '}';
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

    public int getTablaIntermediaID() {
        return tablaIntermediaID;
    }

    public void setTablaIntermediaID(int tablaIntermediaID) {
        this.tablaIntermediaID = tablaIntermediaID;
    }
}
