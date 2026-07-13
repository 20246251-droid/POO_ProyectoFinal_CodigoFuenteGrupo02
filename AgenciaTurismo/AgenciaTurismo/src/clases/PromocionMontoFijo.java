package clases;

public class PromocionMontoFijo extends Promocion {
    private double monto;

    public PromocionMontoFijo(String codigo, String descripcion, String codigoPaquete,
            String fechaInicio, String fechaFin, double monto) {
        super(codigo, descripcion, codigoPaquete, fechaInicio, fechaFin);
        this.monto = monto;
    }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    @Override
    public double calcularDescuento(double montoBase) {
        if (monto > montoBase) {
            return montoBase;
        }
        return monto;
    }

    @Override
    public String obtenerTipo() {
        return "Monto Fijo (S/ " + monto + ")";
    }
}
