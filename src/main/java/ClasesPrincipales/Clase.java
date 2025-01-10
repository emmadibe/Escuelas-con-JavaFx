package ClasesPrincipales;

import java.time.LocalDate;

public class Clase
{
    private int id;
    private int cursoID;
    private LocalDate fechaDeLaClase;

    public Clase(int cursoID, LocalDate fechaDeLaClase)
    {
        this.setFechaDeLaClase(fechaDeLaClase);
        this.setCursoID(cursoID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaDeLaClase() {
        return fechaDeLaClase;
    }

    public void setFechaDeLaClase(LocalDate fechaDeLaClase) {
        this.fechaDeLaClase = fechaDeLaClase;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }
}
