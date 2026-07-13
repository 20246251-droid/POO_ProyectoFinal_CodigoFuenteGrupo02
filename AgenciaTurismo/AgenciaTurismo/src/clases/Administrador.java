package clases;

public class Administrador extends Empleado {

    public Administrador(String dni, String nombres, String apellidos, String usuario, String contrasenia) {
        super(dni, nombres, apellidos, usuario, contrasenia);
    }

    @Override
    public String obtenerRol() {
        return "Administrador";
    }

    @Override
    public String obtenerDescripcionPermisos() {
        return "Gestiona empleados, destinos, paquetes y promociones";
    }
}
