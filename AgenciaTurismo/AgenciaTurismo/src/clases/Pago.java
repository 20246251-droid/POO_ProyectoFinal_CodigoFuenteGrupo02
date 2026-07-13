package clases;

public class Pago {
    private String fecha;
    private double monto;
    private String metodo;

    public Pago(String fecha, double monto, String metodo) {
        this.fecha = fecha;
        this.monto = monto;
        this.metodo = metodo;
    }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }
    public String getMetodo() { return metodo; }
    public void setMetodo(String metodo) { this.metodo = metodo; }
}
