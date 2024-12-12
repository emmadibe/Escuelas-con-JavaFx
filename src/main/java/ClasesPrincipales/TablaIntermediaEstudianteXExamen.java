package ClasesPrincipales;

public class TablaIntermediaEstudianteXExamen
{
    private int id;
    private int estudianteID;
    private int examenID;
    private int nota;

    public TablaIntermediaEstudianteXExamen(int estudianteID, int examenID, int nota)
    {
        this.setEstudianteID(estudianteID);
        this.setExamenID(examenID);
        this.setNota(nota);
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public int getExamenID() {
        return examenID;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
