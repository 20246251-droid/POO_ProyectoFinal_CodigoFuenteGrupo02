package clases;

import interfaces.Calculable;

public class Reserva implements Calculable {

    public static final double DESCUENTO_MENOR = 0.50;
    public static final double PORCENTAJE_PAGO_MINIMO = 0.30;
    public static final double VALOR_PUNTO = 0.10;

    private String codigo;
    private String fechaReserva;
    private PaqueteTuristico paquete;
    private Cliente[] clientes;
    private int totalClientes;
    private int adultos;
    private int menores;
    private double descuentoPromocion;
    private double descuentoPuntos;
    private String estado;
    private Pago[] pagos;
    private int totalPagos;
    private AsesorViaje asesor;

    public Reserva(String codigo, String fechaReserva, PaqueteTuristico paquete,
            int adultos, int menores, AsesorViaje asesor) {
        this.codigo = codigo;
        this.fechaReserva = fechaReserva;
        this.paquete = paquete;
        this.adultos = adultos;
        this.menores = menores;
        this.asesor = asesor;
        this.clientes = new Cliente[20];
        this.totalClientes = 0;
        this.pagos = new Pago[20];
        this.totalPagos = 0;
        this.descuentoPromocion = 0;
        this.descuentoPuntos = 0;
        this.estado = "Pendiente";
    }

    public String getCodigo() { return codigo; }
    public String getFechaReserva() { return fechaReserva; }
    public PaqueteTuristico getPaquete() { return paquete; }
    public int getAdultos() { return adultos; }
    public int getMenores() { return menores; }
    public double getDescuentoPromocion() { return descuentoPromocion; }
    public void setDescuentoPromocion(double descuentoPromocion) { this.descuentoPromocion = descuentoPromocion; }
    public double getDescuentoPuntos() { return descuentoPuntos; }
    public void setDescuentoPuntos(double descuentoPuntos) { this.descuentoPuntos = descuentoPuntos; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public AsesorViaje getAsesor() { return asesor; }

    public boolean agregarCliente(Cliente cliente) {
        if (totalClientes == clientes.length) {
            return false;
        }
        clientes[totalClientes] = cliente;
        totalClientes++;
        return true;
    }

    public Cliente[] obtenerClientes() { return clientes; }
    public int obtenerTotalClientes() { return totalClientes; }

    public Cliente obtenerTitular() {
        if (totalClientes > 0) {
            return clientes[0];
        }
        return null;
    }

    public int obtenerTotalPasajeros() {
        return adultos + menores;
    }

    @Override
    public double calcularPrecioTotal() {
        double precio = paquete.getPrecioPorPersona();
        double montoAdultos = precio * adultos;
        double montoMenores = precio * DESCUENTO_MENOR * menores;
        double total = montoAdultos + montoMenores - descuentoPromocion - descuentoPuntos;
        if (total < 0) {
            total = 0;
        }
        return total;
    }

    public double calcularTotalPagado() {
        double suma = 0;
        for (int i = 0; i < totalPagos; i++) {
            suma = suma + pagos[i].getMonto();
        }
        return suma;
    }

    @Override
    public double calcularSaldoPendiente() {
        double saldo = calcularPrecioTotal() - calcularTotalPagado();
        if (saldo < 0) {
            saldo = 0;
        }
        return saldo;
    }

    public double calcularPagoMinimo() {
        return calcularPrecioTotal() * PORCENTAJE_PAGO_MINIMO;
    }

    public boolean registrarPago(Pago pago) {
        if (totalPagos == pagos.length) {
            return false;
        }
        if (pago.getMonto() <= 0 || pago.getMonto() > calcularSaldoPendiente()) {
            return false;
        }
        pagos[totalPagos] = pago;
        totalPagos++;
        if (estado.equals("Pendiente") && calcularTotalPagado() >= calcularPagoMinimo()) {
            estado = "Confirmada";
        }
        if (calcularSaldoPendiente() == 0) {
            estado = "Pagada";
        }
        return true;
    }

    public Pago[] obtenerPagos() { return pagos; }
    public int obtenerTotalPagos() { return totalPagos; }

    public int calcularPuntosGanados() {
        return (int) (calcularPrecioTotal() / 10.0);
    }

    public String generarVoucher() {
        String linea = "==========================================\n";
        String texto = linea;
        texto = texto + "     AGENCIA DE VIAJES ULIMA TRAVEL\n";
        texto = texto + "       VOUCHER DE RESERVA " + codigo + "\n";
        texto = texto + linea;
        texto = texto + "Fecha de reserva : " + fechaReserva + "\n";
        texto = texto + "Estado           : " + estado + "\n";
        texto = texto + "Asesor           : " + asesor.obtenerNombreCompleto() + "\n";
        texto = texto + linea;
        texto = texto + "CLIENTES:\n";
        for (int i = 0; i < totalClientes; i++) {
            texto = texto + " - " + clientes[i].obtenerNombreCompleto()
                    + " (" + clientes[i].getDni() + ")\n";
        }
        texto = texto + linea;
        texto = texto + "PAQUETE: " + paquete.getNombre() + " (" + paquete.getCodigo() + ")\n";
        texto = texto + "Destinos  : " + paquete.obtenerNombresDestinos() + "\n";
        texto = texto + "Salida    : " + paquete.getFechaSalida() + "\n";
        texto = texto + "Retorno   : " + paquete.getFechaRetorno() + "\n";
        texto = texto + "Pasajeros : " + adultos + " adulto(s), " + menores + " menor(es)\n";
        texto = texto + linea;
        texto = texto + "SERVICIOS INCLUIDOS:\n";
        ServicioIncluido[] servicios = paquete.obtenerServicios();
        for (int i = 0; i < paquete.obtenerTotalServicios(); i++) {
            texto = texto + " - " + servicios[i].getTipo() + ": " + servicios[i].getDescripcion() + "\n";
        }
        texto = texto + linea;
        if (descuentoPromocion > 0) {
            texto = texto + "Descuento promocion : S/ " + String.format("%.2f", descuentoPromocion) + "\n";
        }
        if (descuentoPuntos > 0) {
            texto = texto + "Descuento por puntos: S/ " + String.format("%.2f", descuentoPuntos) + "\n";
        }
        texto = texto + "PRECIO TOTAL   : S/ " + String.format("%.2f", calcularPrecioTotal()) + "\n";
        texto = texto + "MONTO ABONADO  : S/ " + String.format("%.2f", calcularTotalPagado()) + "\n";
        texto = texto + "SALDO PENDIENTE: S/ " + String.format("%.2f", calcularSaldoPendiente()) + "\n";
        texto = texto + linea;
        return texto;
    }
}
