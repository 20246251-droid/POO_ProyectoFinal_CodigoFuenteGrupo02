package clases;

public class PaqueteTuristico {
    private String codigo;
    private String nombre;
    private DestinoTuristico[] destinos;
    private int totalDestinos;
    private int duracionDias;
    private int duracionNoches;
    private String tipo;
    private double precioPorPersona;
    private String fechaSalida;
    private String fechaRetorno;
    private int cupoMaximo;
    private int cupoDisponible;
    private String estado;
    private ServicioIncluido[] servicios;
    private int totalServicios;
    private ItinerarioDia[] itinerario;
    private int totalDiasItinerario;

    public PaqueteTuristico(String codigo, String nombre, int duracionDias, int duracionNoches,
            String tipo, double precioPorPersona, String fechaSalida, String fechaRetorno,
            int cupoMaximo, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.duracionDias = duracionDias;
        this.duracionNoches = duracionNoches;
        this.tipo = tipo;
        this.precioPorPersona = precioPorPersona;
        this.fechaSalida = fechaSalida;
        this.fechaRetorno = fechaRetorno;
        this.cupoMaximo = cupoMaximo;
        this.cupoDisponible = cupoMaximo;
        this.estado = estado;
        this.destinos = new DestinoTuristico[10];
        this.totalDestinos = 0;
        this.servicios = new ServicioIncluido[20];
        this.totalServicios = 0;
        this.itinerario = new ItinerarioDia[30];
        this.totalDiasItinerario = 0;
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getDuracionDias() { return duracionDias; }
    public void setDuracionDias(int duracionDias) { this.duracionDias = duracionDias; }
    public int getDuracionNoches() { return duracionNoches; }
    public void setDuracionNoches(int duracionNoches) { this.duracionNoches = duracionNoches; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getPrecioPorPersona() { return precioPorPersona; }
    public void setPrecioPorPersona(double precioPorPersona) { this.precioPorPersona = precioPorPersona; }
    public String getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(String fechaSalida) { this.fechaSalida = fechaSalida; }
    public String getFechaRetorno() { return fechaRetorno; }
    public void setFechaRetorno(String fechaRetorno) { this.fechaRetorno = fechaRetorno; }
    public int getCupoMaximo() { return cupoMaximo; }
    public void setCupoMaximo(int cupoMaximo) { this.cupoMaximo = cupoMaximo; }
    public int getCupoDisponible() { return cupoDisponible; }
    public void setCupoDisponible(int cupoDisponible) { this.cupoDisponible = cupoDisponible; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public boolean agregarDestino(DestinoTuristico destino) {
        if (destino == null || totalDestinos == destinos.length) {
            return false;
        }
        for (int i = 0; i < totalDestinos; i++) {
            if (destinos[i].getNombre().equalsIgnoreCase(destino.getNombre())) {
                return false;
            }
        }
        destinos[totalDestinos] = destino;
        totalDestinos++;
        return true;
    }

    public boolean eliminarDestino(String nombre) {
        for (int i = 0; i < totalDestinos; i++) {
            if (destinos[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < totalDestinos - 1; j++) {
                    destinos[j] = destinos[j + 1];
                }
                destinos[totalDestinos - 1] = null;
                totalDestinos--;
                return true;
            }
        }
        return false;
    }

    public DestinoTuristico[] obtenerDestinos() { return destinos; }
    public int obtenerTotalDestinos() { return totalDestinos; }

    public String obtenerNombresDestinos() {
        String texto = "";
        for (int i = 0; i < totalDestinos; i++) {
            if (i > 0) {
                texto = texto + ", ";
            }
            texto = texto + destinos[i].getNombre();
        }
        return texto;
    }

    public boolean agregarServicio(ServicioIncluido servicio) {
        if (totalServicios == servicios.length) {
            return false;
        }
        servicios[totalServicios] = servicio;
        totalServicios++;
        return true;
    }

    public boolean eliminarServicio(int posicion) {
        if (posicion < 0 || posicion >= totalServicios) {
            return false;
        }
        for (int j = posicion; j < totalServicios - 1; j++) {
            servicios[j] = servicios[j + 1];
        }
        servicios[totalServicios - 1] = null;
        totalServicios--;
        return true;
    }

    public ServicioIncluido[] obtenerServicios() { return servicios; }
    public int obtenerTotalServicios() { return totalServicios; }

    public boolean agregarDiaItinerario(ItinerarioDia dia) {
        if (totalDiasItinerario == itinerario.length) {
            return false;
        }
        itinerario[totalDiasItinerario] = dia;
        totalDiasItinerario++;
        return true;
    }

    public ItinerarioDia[] obtenerItinerario() { return itinerario; }
    public int obtenerTotalDiasItinerario() { return totalDiasItinerario; }

    public boolean reservarCupos(int cantidad) {
        if (cantidad <= 0 || cantidad > cupoDisponible) {
            return false;
        }
        cupoDisponible = cupoDisponible - cantidad;
        if (cupoDisponible == 0) {
            estado = "Agotado";
        }
        return true;
    }

    public void liberarCupos(int cantidad) {
        cupoDisponible = cupoDisponible + cantidad;
        if (cupoDisponible > cupoMaximo) {
            cupoDisponible = cupoMaximo;
        }
        if (cupoDisponible > 0 && estado.equals("Agotado")) {
            estado = "Activo";
        }
    }
}
