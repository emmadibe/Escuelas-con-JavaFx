package ClasesPrincipales;

import java.util.Objects;

public class TraerTodoLaParteEstudiantes
{
    private String nombreYapellido;

    public TraerTodoLaParteEstudiantes(String nombreYapellido)
    {
        this.setNombreYapellido(nombreYapellido);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraerTodoLaParteEstudiantes that)) return false;
        return Objects.equals(nombreYapellido, that.nombreYapellido);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombreYapellido);
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }
}
