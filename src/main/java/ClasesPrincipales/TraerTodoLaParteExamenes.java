package ClasesPrincipales;

import java.util.Objects;

public class TraerTodoLaParteExamenes
{
    private String nombreYNumeroExamen;
    public TraerTodoLaParteExamenes (String nombreYNumeroExamen)
    {
        this.setNombreYNumeroExamen(nombreYNumeroExamen);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraerTodoLaParteExamenes that)) return false;
        return Objects.equals(nombreYNumeroExamen, that.nombreYNumeroExamen);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombreYNumeroExamen);
    }

    public String getNombreYNumeroExamen() {
        return nombreYNumeroExamen;
    }

    public void setNombreYNumeroExamen(String nombreYNumeroExamen) {
        this.nombreYNumeroExamen = nombreYNumeroExamen;
    }
}
