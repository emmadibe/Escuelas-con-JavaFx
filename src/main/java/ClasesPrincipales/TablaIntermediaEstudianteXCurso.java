package ClasesPrincipales;

public class TablaIntermediaEstudianteXCurso
{
    private int id;
    private int estudianteID;
    private int cursoID;

    public TablaIntermediaEstudianteXCurso(int cursoID, int estudianteID)
    {
        this.setCursoID(cursoID);
        this.setEstudianteID(estudianteID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCursoID() {
        return cursoID;
    }

    public void setCursoID(int cursoID) {
        this.cursoID = cursoID;
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }
}
