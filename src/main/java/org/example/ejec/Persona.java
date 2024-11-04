package org.example.ejec;

import java.util.Objects;

/**
 * Clase que representa una persona con atributos básicos como nombre, apellidos y edad.
 * Proporciona métodos para acceder y modificar estos atributos, así como métodos para
 * comparar instancias de la clase.
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;

    /**
     * Constructor para crear una nueva instancia de Persona con los atributos especificados.
     *
     * @param nombre el nombre de la persona.
     * @param apellidos los apellidos de la persona.
     * @param edad la edad de la persona.
     */
    public Persona(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    // Getters

    /**
     * Obtiene el nombre de la persona.
     *
     * @return el nombre de la persona.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene los apellidos de la persona.
     *
     * @return los apellidos de la persona.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Obtiene la edad de la persona.
     *
     * @return la edad de la persona.
     */
    public int getEdad() {
        return edad;
    }

    // Setters

    /**
     * Establece un nuevo nombre para la persona.
     *
     * @param nombre el nuevo nombre de la persona.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Establece nuevos apellidos para la persona.
     *
     * @param apellidos los nuevos apellidos de la persona.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Establece una nueva edad para la persona.
     *
     * @param edad la nueva edad de la persona.
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * Dos objetos Persona son iguales si tienen el mismo nombre, apellidos y edad.
     *
     * @param obj el objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Persona)) return false;
        Persona persona = (Persona) obj;
        return edad == persona.edad &&
                nombre.equals(persona.nombre) &&
                apellidos.equals(persona.apellidos);
    }

    /**
     * Genera un código hash para el objeto Persona basado en sus atributos.
     *
     * @return el código hash de la persona.
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre, apellidos, edad);
    }
}
