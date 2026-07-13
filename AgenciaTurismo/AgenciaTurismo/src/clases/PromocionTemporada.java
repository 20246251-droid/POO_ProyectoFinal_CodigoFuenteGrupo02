package clases;

public class PromocionTemporada extends Promocion {
    private String temporada;
    private double porcentaje;

    public PromocionTemporada(String codigo, String descripcion, String codigoPaquete,
            String fechaInicio, String fechaFin, String temporada, double porcentaje) {
        super(codigo, descripcion, codigoPaquete, fechaInicio, fechaFin);
        this.temporada = temporada;
        this.porcentaje = porcentaje;
    }

    public String getTemporada() { return temporada; }
    public void setTemporada(String temporada) { this.temporada = temporada; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }

    @Override
    public double calcularDescuento(double montoBase) {
        return montoBase * porcentaje / 100.0;
    }

    @Override
    public String obtenerTipo() {
        return "Temporada " + temporada + " (" + porcentaje + "%)";
    }
}
