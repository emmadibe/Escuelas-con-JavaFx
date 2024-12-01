package ClasesPrincipales;

import ENUMS.Rama;
import Interfaces.ClasesGenerales;

public class Docente extends Persona implements ClasesGenerales<Docente>
{
    protected Rama rama;

    public Docente(String nombre, String apellido, String dni, String email, int edad, Rama rama)
    {
        this.setRama(rama);
        this.setDni(dni);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setNombre(nombre);
        this.setEdad(edad);
    }
    @Override
    public void imprimirUnaInstancia(Docente docente)
    {
        System.out.println("----------------------------");
        System.out.println("Nombre: " + docente.getNombre());
        System.out.println("Apellido: " + docente.getApellido());
        System.out.println("DNI: " + docente.getDni());
        System.out.println("Edad: " + docente.getEdad());
        System.out.println("Email: " + docente.getEmail());
        System.out.println("Rama: " + docente.getRama());
        System.out.println("----------------------------");
    }

    public Rama getRama() {
        return rama;
    }

    public void setRama(Rama rama) {
        this.rama = rama;
    }
}
