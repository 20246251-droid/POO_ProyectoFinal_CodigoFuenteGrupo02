package gestion;

import clases.Administrador;
import clases.AsesorViaje;
import clases.Empleado;
import clases.Operador;

public class GestionEmpleado {

    private Empleado[] empleados;
    private int totalEmpleados;

    public GestionEmpleado() {
        empleados = new Empleado[50];
        totalEmpleados = 0;

        registrar(new Administrador("70000001", "Rosa", "Quispe Torres", "admin", "123456"));
        registrar(new AsesorViaje("70000002", "Carlos", "Ramos Diaz", "carlos", "111111"));
        registrar(new AsesorViaje("70000003", "Maria", "Lopez Vega", "maria", "222222"));
        registrar(new Operador("70000004", "Juan", "Castro Ruiz", "juan", "333333"));
    }

    public Empleado buscar(String dni) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDni().equalsIgnoreCase(dni)) {
                return empleados[i];
            }
        }
        return null;
    }

    public Empleado buscarPorUsuario(String usuario) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getUsuario().equalsIgnoreCase(usuario)) {
                return empleados[i];
            }
        }
        return null;
    }

    public Empleado validarLogin(String usuario, String contrasenia) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getUsuario().equals(usuario)
                    && empleados[i].getContrasenia().equals(contrasenia)) {
                return empleados[i];
            }
        }
        return null;
    }

    public boolean registrar(Empleado empleado) {
        if (totalEmpleados == empleados.length) {
            return false;
        }
        if (buscar(empleado.getDni()) != null || buscarPorUsuario(empleado.getUsuario()) != null) {
            return false;
        }
        empleados[totalEmpleados] = empleado;
        totalEmpleados++;
        return true;
    }

    public boolean actualizar(String dni, String nombres, String apellidos,
            String usuario, String contrasenia, String rol) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDni().equalsIgnoreCase(dni)) {
                if (empleados[i].obtenerRol().equals(rol)) {
                    empleados[i].setNombres(nombres);
                    empleados[i].setApellidos(apellidos);
                    empleados[i].setUsuario(usuario);
                    empleados[i].setContrasenia(contrasenia);
                } else {
                    Empleado nuevo;
                    if (rol.equals("Administrador")) {
                        nuevo = new Administrador(dni, nombres, apellidos, usuario, contrasenia);
                    } else if (rol.equals("Asesor de Viaje")) {
                        nuevo = new AsesorViaje(dni, nombres, apellidos, usuario, contrasenia);
                    } else {
                        nuevo = new Operador(dni, nombres, apellidos, usuario, contrasenia);
                    }
                    empleados[i] = nuevo;
                }
                return true;
            }
        }
        return false;
    }

    public boolean eliminar(String dni) {
        for (int i = 0; i < totalEmpleados; i++) {
            if (empleados[i].getDni().equalsIgnoreCase(dni)) {
                for (int j = i; j < totalEmpleados - 1; j++) {
                    empleados[j] = empleados[j + 1];
                }
                empleados[totalEmpleados - 1] = null;
                totalEmpleados--;
                return true;
            }
        }
        return false;
    }

    public Empleado[] obtenerEmpleados() {
        return empleados;
    }

    public int obtenerTotalEmpleados() {
        return totalEmpleados;
    }
}
