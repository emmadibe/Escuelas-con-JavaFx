package ClasesPrincipales;

public class AsistioYTablaIntermediaID
{
    private boolean asistio;
    private int tablaIntermediaID;

    public AsistioYTablaIntermediaID(int tablaIntermediaID, boolean asistio)
    {
        this.setAsistio(asistio);
        this.setTablaIntermediaID(tablaIntermediaID);
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
