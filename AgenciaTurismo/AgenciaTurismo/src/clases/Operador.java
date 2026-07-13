package clases;

public class Operador extends Empleado {

    public Operador(String dni, String nombres, String apellidos, String usuario, String contrasenia) {
        super(dni, nombres, apellidos, usuario, contrasenia);
    }

    @Override
    public String obtenerRol() {
        return "Operador";
    }

    @Override
    public String obtenerDescripcionPermisos() {
        return "Accede solo a itinerarios y cupos";
    }
}
