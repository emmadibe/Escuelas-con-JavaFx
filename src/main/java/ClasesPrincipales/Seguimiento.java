package ClasesPrincipales;

import java.time.LocalDate;

public class Seguimiento
{
    private String titulo;
    private String cuerpo;
    private LocalDate fecha;
    private int id;
    private int cursoID;

    public Seguimiento(String titulo, String cuerpo, LocalDate fecha, int cursoID)
    {
        this.setTitulo(titulo);
        this.setCuerpo(cuerpo);
        this.setCursoID(cursoID);
        this.setFecha(fecha);
    }
    public Seguimiento(int id, String titulo, String cuerpo, LocalDate fecha, int cursoID)
    {
        this.setId(id);
        this.setTitulo(titulo);
        this.setCuerpo(cuerpo);
        this.setCursoID(cursoID);
        this.setFecha(fecha);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
