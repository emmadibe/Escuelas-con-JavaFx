package ClasesPrincipales;

public class TablaIntermediaAsistenciaEstudiantesXClases
{
    private int id;
    private int estudianteID;
    private int claseID;
    private byte asistio;

    public TablaIntermediaAsistenciaEstudiantesXClases (int id, byte asistio)
    {
        this.setAsistio(asistio);
        this.setId(id);
    }

    public TablaIntermediaAsistenciaEstudiantesXClases(int estudianteID, int claseID, byte asistio)
    {
        this.setAsistio(asistio);
        this.setClaseID(claseID);
        this.setEstudianteID(estudianteID);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstudianteID() {
        return estudianteID;
    }

    public void setEstudianteID(int estudianteID) {
        this.estudianteID = estudianteID;
    }

    public int getClaseID() {
        return claseID;
    }

    public void setClaseID(int claseID) {
        this.claseID = claseID;
    }

    public byte getAsistio() {
        return asistio;
    }

    public void setAsistio(byte asistio) {
        this.asistio = asistio;
    }
}
