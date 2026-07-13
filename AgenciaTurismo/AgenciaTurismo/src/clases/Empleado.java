package clases;

public abstract class Empleado extends Persona {
    protected String usuario;
    protected String contrasenia;

    public Empleado(String dni, String nombres, String apellidos, String usuario, String contrasenia) {
        super(dni, nombres, apellidos);
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public abstract String obtenerRol();

    public abstract String obtenerDescripcionPermisos();
}
