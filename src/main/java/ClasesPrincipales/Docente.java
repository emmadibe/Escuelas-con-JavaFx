package ClasesPrincipales;

import ENUMS.Rama;
import Interfaces.ClasesGenerales;

public class Docente extends Persona implements ClasesGenerales<Docente>
{
    protected Rama rama;
    protected String password;

    public Docente(int id, String nombre, String apellido, String dni, String email, int edad, Rama rama, String password)
    {
        this.setID(id);
        this.setRama(rama);
        this.setDni(dni);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setPassword(password);
    }
    public Docente(String nombre, String apellido, String dni, String email, int edad, Rama rama, String password)
    {
        this.setRama(rama);
        this.setDni(dni);
        this.setApellido(apellido);
        this.setEmail(email);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setPassword(password);
    }

    public Docente(String email, String password)
    {
        this.setPassword(password);
        this.setEmail(email);
    }
    @Override
    public void imprimirUnaInstancia()
    {
        System.out.println("----------------------------");
        System.out.println("ID: " + this.getID());
        System.out.println("Nombre: " + this.getNombre());
        System.out.println("Apellido: " + this.getApellido());
        System.out.println("DNI: " + this.getDni());
        System.out.println("Edad: " + this.getEdad());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Rama: " + this.getRama());
        System.out.println("----------------------------");
    }

    public Rama getRama() {
        return rama;
    }

    public void setRama(Rama rama) {
        this.rama = rama;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
