package gestion;

public class Agencia {

    private GestionEmpleado gestionEmpleado;
    private GestionCliente gestionCliente;
    private GestionDestino gestionDestino;
    private GestionPaquete gestionPaquete;
    private GestionPromocion gestionPromocion;
    private GestionReserva gestionReserva;

    public Agencia() {
        gestionEmpleado = new GestionEmpleado();
        gestionCliente = new GestionCliente();
        gestionDestino = new GestionDestino();
        gestionPaquete = new GestionPaquete(gestionDestino);
        gestionPromocion = new GestionPromocion();
        gestionReserva = new GestionReserva(gestionPromocion);
    }

    public GestionEmpleado getGestionEmpleado() { return gestionEmpleado; }
    public GestionCliente getGestionCliente() { return gestionCliente; }
    public GestionDestino getGestionDestino() { return gestionDestino; }
    public GestionPaquete getGestionPaquete() { return gestionPaquete; }
    public GestionPromocion getGestionPromocion() { return gestionPromocion; }
    public GestionReserva getGestionReserva() { return gestionReserva; }
}
