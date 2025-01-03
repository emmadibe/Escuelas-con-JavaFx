package ClasesPrincipales;
import java.time.Year;

public class FuncionesGenerales
{
    public static int obtenerAnioActual() //Función que me retorna el año actual.
    {
        return Year.now().getValue();
    }
}
