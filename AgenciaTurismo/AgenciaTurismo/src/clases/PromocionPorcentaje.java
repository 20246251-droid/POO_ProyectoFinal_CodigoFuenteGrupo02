package clases;

public class PromocionPorcentaje extends Promocion {
    private double porcentaje;

    public PromocionPorcentaje(String codigo, String descripcion, String codigoPaquete,
            String fechaInicio, String fechaFin, double porcentaje) {
        super(codigo, descripcion, codigoPaquete, fechaInicio, fechaFin);
        this.porcentaje = porcentaje;
    }

    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }

    @Override
    public double calcularDescuento(double montoBase) {
        return montoBase * porcentaje / 100.0;
    }

    @Override
    public String obtenerTipo() {
        return "Porcentaje (" + porcentaje + "%)";
    }
}
