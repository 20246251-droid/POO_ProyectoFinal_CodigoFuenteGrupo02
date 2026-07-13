package clases;

import interfaces.Canjeable;

public class Cliente extends Persona implements Canjeable {
    private String fechaNacimiento;
    private String nacionalidad;
    private String telefono;
    private String correo;
    private int puntos;

    public Cliente(String documento, String nombres, String apellidos, String fechaNacimiento,
            String nacionalidad, String telefono, String correo) {
        super(documento, nombres, apellidos);
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.correo = correo;
        this.puntos = 0;
    }

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public int obtenerPuntos() {
        return puntos;
    }

    @Override
    public void acumularPuntos(int puntos) {
        this.puntos = this.puntos + puntos;
    }

    @Override
    public boolean canjearPuntos(int puntos) {
        if (puntos <= 0 || puntos > this.puntos) {
            return false;
        }
        this.puntos = this.puntos - puntos;
        return true;
    }
}
