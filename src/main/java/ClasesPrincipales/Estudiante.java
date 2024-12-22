package ClasesPrincipales;

import Interfaces.ClasesGenerales;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class Estudiante extends Persona implements ClasesGenerales<Estudiante>
{
    private int  notas;
    private int examenID;
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

    @Override
    public String toString() {
        return "Estudiante{" +
                "notas=" + notas +
                ", examenID=" + examenID +
                ", ID=" + ID +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", edad=" + edad +
                '}';
    }

    public void setNotas(int notas) {
        this.notas = notas;
    }

    public void setExamenID(int examenID) {
        this.examenID = examenID;
    }

    public int getExamenID() {
        return examenID;
    }

    public int getNotas() {
        return notas;
    }

    @Override
    public void imprimirUnaInstancia()
    {

    }
}
