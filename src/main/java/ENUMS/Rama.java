package ENUMS;

public enum Rama
{
    HUMANIDADES("Humanidades", 1),
    EXACTAS("Ciencias Exactas", 2),
    ECONOMICAS("Ciencias Económicas", 3),
    LENGUA("Prácticas del lenguaje y literatura", 4),
    ARTE("Artística", 5),
    EDUCACIONFISICA("Educación física", 6),
    IDIOMAS("Idioma extranjero", 7),
    OTRO("otro", 8);

    private String nombre;
    private int orden;

    private Rama(String nombre, int orden)
    {
        this.setNombre(nombre);
        this.setOrden(orden);
    }

    public static Rama asignarRama(String nombreRama)
    {
        nombreRama = nombreRama.toLowerCase(); //Así no hay confusiones por mayúsculas o minúsculas.
        Rama rama = OTRO;
        switch (nombreRama){
            case "humanidades":
                rama = HUMANIDADES;
                break;
            case "ciencias exactas":
                rama = EXACTAS;
                break;
            case "ciencias economicas":
                rama = ECONOMICAS;
                break;
            case "arte":
                rama = ARTE;
                break;
            case "educacion fisica":
                rama = EDUCACIONFISICA;
                break;
            case "lengua y literatura":
                rama = LENGUA;
                break;
            case "otro":
                rama = OTRO;
                break;
        }
        return rama;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
