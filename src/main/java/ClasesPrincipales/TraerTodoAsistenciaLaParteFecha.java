package ClasesPrincipales;

import java.time.LocalDate;
import java.util.Objects;

public class TraerTodoAsistenciaLaParteFecha
{
    private LocalDate fechaClase;

    public TraerTodoAsistenciaLaParteFecha(LocalDate fechaClase)
    {
        this.setFechaClase(fechaClase);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TraerTodoAsistenciaLaParteFecha that)) return false;
        return Objects.equals(fechaClase, that.fechaClase);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fechaClase);
    }

    public LocalDate getFechaClase() {
        return fechaClase;
    }

    public void setFechaClase(LocalDate fechaClase) {
        this.fechaClase = fechaClase;
    }
}
