package clases;

public class DestinoTuristico {
    private String nombre;
    private String pais;
    private String ciudad;
    private String descripcion;
    private String clima;
    private String idioma;
    private String rutaImagen;

    public DestinoTuristico(String nombre, String pais, String ciudad, String descripcion,
            String clima, String idioma, String rutaImagen) {
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.clima = clima;
        this.idioma = idioma;
        this.rutaImagen = rutaImagen;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getClima() { return clima; }
    public void setClima(String clima) { this.clima = clima; }
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public String getRutaImagen() { return rutaImagen; }
    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }
}
