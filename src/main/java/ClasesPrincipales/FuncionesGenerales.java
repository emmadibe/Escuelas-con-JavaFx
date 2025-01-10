package ClasesPrincipales;
import java.time.Year;
import java.util.Arrays;

public class FuncionesGenerales
{
    public static int obtenerAnioActual() //Función que me retorna el año actual.
    {
        return Year.now().getValue();
    }

}
