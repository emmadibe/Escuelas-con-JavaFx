package ClasesPrincipales;

import Interfaces.ClasesGenerales;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Estudiante extends Persona implements ClasesGenerales<Estudiante>
{
    private StringProperty nombre;
    private Map<String, String> notas;
    private int examenID;
    public Estudiante(String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
        this.notas = new HashMap<>();
    }

    public Estudiante(){}

    public Estudiante(String nombre, String apellido, String dni, String email, int edad)
    {
        this.setApellido(apellido);
        this.setNombre(nombre);
        this.setDni(dni);
        this.setEmail(email);
        this.setEdad(edad);
    }

    public Estudiante(int id, String nombre, String apellido, String dni, String email, int edad)
    {
        this.setID(id);
        this.setApellido(apellido);
        this.setNombre(nombre);
        this.setDni(dni);
        this.setEmail(email);
        this.setEdad(edad);
    }
    public Estudiante(int id, String nombre, String apellido, String dni, String email, int edad, int examenID)
    {
        this.setID(id);
        this.setApellido(apellido);
        this.setNombre(nombre);
        this.setDni(dni);
        this.setEmail(email);
        this.setEdad(edad);
        this.setExamenID(examenID);
    }

    private void setExamenID(int examenID) {
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public Map<String, String> getNotas() {
        return notas;
    }

    public void agregarNota(String examen, String nota) {
        notas.put(examen, nota);
    }

    public int getExamenID() {
        return examenID;
    }

    @Override
    public void imprimirUnaInstancia()
    {

    }
}
