package clases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Promocion {
    protected String codigo;
    protected String descripcion;
    protected String codigoPaquete;
    protected String fechaInicio;
    protected String fechaFin;

    public Promocion(String codigo, String descripcion, String codigoPaquete,
            String fechaInicio, String fechaFin) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoPaquete = codigoPaquete;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getCodigoPaquete() { return codigoPaquete; }
    public void setCodigoPaquete(String codigoPaquete) { this.codigoPaquete = codigoPaquete; }
    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }
    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public boolean estaVigente(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaConsulta = LocalDate.parse(fecha, formato);
        LocalDate inicio = LocalDate.parse(fechaInicio, formato);
        LocalDate fin = LocalDate.parse(fechaFin, formato);
        return !fechaConsulta.isBefore(inicio) && !fechaConsulta.isAfter(fin);
    }

    public abstract double calcularDescuento(double montoBase);

    public abstract String obtenerTipo();
}
