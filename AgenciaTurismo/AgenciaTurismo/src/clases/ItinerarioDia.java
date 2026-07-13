package clases;

public class ItinerarioDia {
    private int dia;
    private String descripcion;

    public ItinerarioDia(int dia, String descripcion) {
        this.dia = dia;
        this.descripcion = descripcion;
    }

    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
