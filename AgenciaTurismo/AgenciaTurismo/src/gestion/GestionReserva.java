package gestion;

import clases.AsesorViaje;
import clases.Cliente;
import clases.Pago;
import clases.PaqueteTuristico;
import clases.Promocion;
import clases.Reserva;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GestionReserva {

    private Reserva[] reservas;
    private int totalReservas;
    private GestionPromocion gestionPromocion;

    public GestionReserva(GestionPromocion gestionPromocion) {
        reservas = new Reserva[200];
        totalReservas = 0;
        this.gestionPromocion = gestionPromocion;
    }

    public Reserva buscar(String codigo) {
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i].getCodigo().equalsIgnoreCase(codigo)) {
                return reservas[i];
            }
        }
        return null;
    }

    public String generarCodigo() {
        return String.format("R%04d", totalReservas + 1);
    }

    public Reserva crearReserva(String fecha, PaqueteTuristico paquete, Cliente[] clientes,
            int totalClientes, int adultos, int menores, AsesorViaje asesor) {

        if (paquete == null || !paquete.getEstado().equals("Activo")) {
            return null;
        }
        int totalPasajeros = adultos + menores;
        if (totalPasajeros <= 0 || totalClientes <= 0) {
            return null;
        }
        if (!paquete.reservarCupos(totalPasajeros)) {
            return null;
        }

        Reserva reserva = new Reserva(generarCodigo(), fecha, paquete, adultos, menores, asesor);
        for (int i = 0; i < totalClientes; i++) {
            reserva.agregarCliente(clientes[i]);
        }

        Promocion promocion = gestionPromocion.buscarVigentePorPaquete(paquete.getCodigo(), fecha);
        if (promocion != null) {
            double precio = paquete.getPrecioPorPersona();
            double montoBase = precio * adultos + precio * Reserva.DESCUENTO_MENOR * menores;
            reserva.setDescuentoPromocion(promocion.calcularDescuento(montoBase));
        }

        reservas[totalReservas] = reserva;
        totalReservas++;
        return reserva;
    }

    public boolean cancelarReserva(String codigo) {
        Reserva reserva = buscar(codigo);
        if (reserva == null || reserva.getEstado().equals("Cancelada")) {
            return false;
        }
        reserva.getPaquete().liberarCupos(reserva.obtenerTotalPasajeros());
        reserva.setEstado("Cancelada");
        return true;
    }

    public boolean registrarPago(String codigoReserva, Pago pago) {
        Reserva reserva = buscar(codigoReserva);
        if (reserva == null || reserva.getEstado().equals("Cancelada")) {
            return false;
        }
        String estadoAnterior = reserva.getEstado();
        boolean resultado = reserva.registrarPago(pago);
        if (resultado && estadoAnterior.equals("Pendiente")
                && !reserva.getEstado().equals("Pendiente")) {
            Cliente titular = reserva.obtenerTitular();
            if (titular != null) {
                titular.acumularPuntos(reserva.calcularPuntosGanados());
            }
        }
        return resultado;
    }

    public Reserva[] obtenerReservas() {
        return reservas;
    }

    public int obtenerTotalReservas() {
        return totalReservas;
    }

    private LocalDate convertir(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha, formato);
    }

    public String reporteReservasPorPaquete(String codigoPaquete, String fechaInicio, String fechaFin) {
        String texto = "REPORTE DE RESERVAS - PAQUETE " + codigoPaquete
                + " (" + fechaInicio + " al " + fechaFin + ")\n";
        texto = texto + "--------------------------------------------------------\n";
        LocalDate inicio = convertir(fechaInicio);
        LocalDate fin = convertir(fechaFin);
        int contador = 0;
        double total = 0;
        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];
            LocalDate fecha = convertir(r.getFechaReserva());
            if (r.getPaquete().getCodigo().equalsIgnoreCase(codigoPaquete)
                    && !fecha.isBefore(inicio) && !fecha.isAfter(fin)) {
                texto = texto + r.getCodigo() + " | " + r.getFechaReserva() + " | "
                        + r.obtenerTitular().obtenerNombreCompleto() + " | "
                        + r.obtenerTotalPasajeros() + " pax | "
                        + r.getEstado() + " | S/ "
                        + String.format("%.2f", r.calcularPrecioTotal()) + "\n";
                contador++;
                total = total + r.calcularPrecioTotal();
            }
        }
        texto = texto + "--------------------------------------------------------\n";
        texto = texto + "Total de reservas: " + contador + "  |  Monto total: S/ "
                + String.format("%.2f", total) + "\n";
        return texto;
    }

    public String reporteDestinosMasSolicitados() {
        String texto = "REPORTE DE DESTINOS MAS SOLICITADOS\n";
        texto = texto + "--------------------------------------------------------\n";
        String[] nombres = new String[100];
        int[] contadores = new int[100];
        int totalNombres = 0;

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];
            if (r.getEstado().equals("Cancelada")) {
                continue;
            }
            for (int d = 0; d < r.getPaquete().obtenerTotalDestinos(); d++) {
                String nombre = r.getPaquete().obtenerDestinos()[d].getNombre();
                int posicion = -1;
                for (int k = 0; k < totalNombres; k++) {
                    if (nombres[k].equalsIgnoreCase(nombre)) {
                        posicion = k;
                        break;
                    }
                }
                if (posicion == -1) {
                    nombres[totalNombres] = nombre;
                    contadores[totalNombres] = r.obtenerTotalPasajeros();
                    totalNombres++;
                } else {
                    contadores[posicion] = contadores[posicion] + r.obtenerTotalPasajeros();
                }
            }
        }

        for (int i = 0; i < totalNombres - 1; i++) {
            for (int j = 0; j < totalNombres - 1 - i; j++) {
                if (contadores[j] < contadores[j + 1]) {
                    int auxiliarContador = contadores[j];
                    contadores[j] = contadores[j + 1];
                    contadores[j + 1] = auxiliarContador;
                    String auxiliarNombre = nombres[j];
                    nombres[j] = nombres[j + 1];
                    nombres[j + 1] = auxiliarNombre;
                }
            }
        }

        for (int i = 0; i < totalNombres; i++) {
            texto = texto + (i + 1) + ". " + nombres[i] + " - " + contadores[i] + " pasajero(s)\n";
        }
        if (totalNombres == 0) {
            texto = texto + "No hay reservas registradas.\n";
        }
        return texto;
    }

    public String reporteIngresosPorAsesor() {
        String texto = "REPORTE DE INGRESOS POR ASESOR\n";
        texto = texto + "--------------------------------------------------------\n";
        String[] nombres = new String[50];
        double[] montos = new double[50];
        int totalNombres = 0;

        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];
            if (r.getEstado().equals("Cancelada")) {
                continue;
            }
            String nombre = r.getAsesor().obtenerNombreCompleto();
            int posicion = -1;
            for (int k = 0; k < totalNombres; k++) {
                if (nombres[k].equalsIgnoreCase(nombre)) {
                    posicion = k;
                    break;
                }
            }
            if (posicion == -1) {
                nombres[totalNombres] = nombre;
                montos[totalNombres] = r.calcularTotalPagado();
                totalNombres++;
            } else {
                montos[posicion] = montos[posicion] + r.calcularTotalPagado();
            }
        }

        for (int i = 0; i < totalNombres; i++) {
            texto = texto + nombres[i] + " : S/ " + String.format("%.2f", montos[i]) + "\n";
        }
        if (totalNombres == 0) {
            texto = texto + "No hay reservas registradas.\n";
        }
        return texto;
    }

    public String reporteClientesConReservas() {
        String texto = "REPORTE DE CLIENTES CON RESERVAS ACTIVAS Y PENDIENTES DE PAGO\n";
        texto = texto + "--------------------------------------------------------\n";
        int contador = 0;
        for (int i = 0; i < totalReservas; i++) {
            Reserva r = reservas[i];
            if (!r.getEstado().equals("Cancelada") && r.calcularSaldoPendiente() > 0) {
                texto = texto + r.obtenerTitular().obtenerNombreCompleto()
                        + " | Reserva " + r.getCodigo()
                        + " | " + r.getPaquete().getNombre()
                        + " | Estado: " + r.getEstado()
                        + " | Saldo: S/ " + String.format("%.2f", r.calcularSaldoPendiente()) + "\n";
                contador++;
            }
        }
        if (contador == 0) {
            texto = texto + "No hay clientes con pagos pendientes.\n";
        }
        return texto;
    }
}
