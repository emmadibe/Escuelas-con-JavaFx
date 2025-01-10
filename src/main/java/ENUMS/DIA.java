package ENUMS;

public enum DIA
{
    LUNES("Lunes", 1),
    MARTES("Martes", 2),
    MIERCOLES("Miercoles", 3),
    JUEVES("Jueves", 4),
    VIERNES("Viernes", 5),
    SABADO("Sabado", 6);

    private String nombre;
    private int orden;

    private DIA(String nombre, int orden)
    {
        this.setNombre(nombre);
        this.setOrden(orden);
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
