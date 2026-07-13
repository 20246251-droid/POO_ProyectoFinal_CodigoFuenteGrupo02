package gestion;

import clases.Cliente;

public class GestionCliente {

    private Cliente[] clientes;
    private int totalClientes;

    public GestionCliente() {
        clientes = new Cliente[100];
        totalClientes = 0;

        registrar(new Cliente("71234567", "Zowe", "Fernandez Rios", "12/05/2004", "Peruana", "987654321", "zowe@correo.com"));
        registrar(new Cliente("72345678", "Lucia", "Mendoza Paz", "03/09/1998", "Peruana", "912345678", "lucia@correo.com"));
        registrar(new Cliente("AB123456", "John", "Smith", "20/01/1990", "Estadounidense", "999888777", "john@mail.com"));
    }

    public Cliente buscar(String documento) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getDni().equalsIgnoreCase(documento)) {
                return clientes[i];
            }
        }
        return null;
    }

    public Cliente buscar(String nombres, String apellidos) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getNombres().equalsIgnoreCase(nombres)
                    && clientes[i].getApellidos().equalsIgnoreCase(apellidos)) {
                return clientes[i];
            }
        }
        return null;
    }

    public boolean registrar(Cliente cliente) {
        if (totalClientes == clientes.length) {
            return false;
        }
        if (buscar(cliente.getDni()) != null) {
            return false;
        }
        clientes[totalClientes] = cliente;
        totalClientes++;
        return true;
    }

    public boolean actualizar(Cliente cliente) {
        Cliente encontrado = buscar(cliente.getDni());
        if (encontrado == null) {
            return false;
        }
        encontrado.setNombres(cliente.getNombres());
        encontrado.setApellidos(cliente.getApellidos());
        encontrado.setFechaNacimiento(cliente.getFechaNacimiento());
        encontrado.setNacionalidad(cliente.getNacionalidad());
        encontrado.setTelefono(cliente.getTelefono());
        encontrado.setCorreo(cliente.getCorreo());
        return true;
    }

    public boolean eliminar(String documento) {
        for (int i = 0; i < totalClientes; i++) {
            if (clientes[i].getDni().equalsIgnoreCase(documento)) {
                for (int j = i; j < totalClientes - 1; j++) {
                    clientes[j] = clientes[j + 1];
                }
                clientes[totalClientes - 1] = null;
                totalClientes--;
                return true;
            }
        }
        return false;
    }

    public Cliente[] obtenerClientes() {
        return clientes;
    }

    public int obtenerTotalClientes() {
        return totalClientes;
    }
}
