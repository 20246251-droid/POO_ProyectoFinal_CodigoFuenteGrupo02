package clases;

public class AsesorViaje extends Empleado {

    public AsesorViaje(String dni, String nombres, String apellidos, String usuario, String contrasenia) {
        super(dni, nombres, apellidos, usuario, contrasenia);
    }

    @Override
    public String obtenerRol() {
        return "Asesor de Viaje";
    }

    @Override
    public String obtenerDescripcionPermisos() {
        return "Gestiona clientes, reservas y pagos";
    }
}
