package gestion;

import clases.ItinerarioDia;
import clases.PaqueteTuristico;
import clases.ServicioIncluido;

public class GestionPaquete {

    private PaqueteTuristico[] paquetes;
    private int totalPaquetes;

    public GestionPaquete(GestionDestino gestionDestino) {
        paquetes = new PaqueteTuristico[50];
        totalPaquetes = 0;

        PaqueteTuristico p1 = new PaqueteTuristico("P001", "Cusco Magico", 4, 3, "Cultural",
                1200.00, "15/08/2026", "18/08/2026", 20, "Activo");
        p1.agregarDestino(gestionDestino.buscar("Cusco"));
        p1.agregarServicio(new ServicioIncluido("Vuelo", "Vuelo Lima - Cusco - Lima"));
        p1.agregarServicio(new ServicioIncluido("Hotel", "Hotel 3 estrellas con desayuno"));
        p1.agregarServicio(new ServicioIncluido("Visita guiada", "Tour Machu Picchu con guia"));
        p1.agregarDiaItinerario(new ItinerarioDia(1, "Llegada a Cusco y traslado al hotel"));
        p1.agregarDiaItinerario(new ItinerarioDia(2, "City tour y Valle Sagrado"));
        p1.agregarDiaItinerario(new ItinerarioDia(3, "Visita a Machu Picchu"));
        p1.agregarDiaItinerario(new ItinerarioDia(4, "Manana libre y retorno a Lima"));
        registrar(p1);

        PaqueteTuristico p2 = new PaqueteTuristico("P002", "Paracas Relax", 3, 2, "Relax",
                650.00, "22/08/2026", "24/08/2026", 15, "Activo");
        p2.agregarDestino(gestionDestino.buscar("Paracas"));
        p2.agregarServicio(new ServicioIncluido("Traslado", "Bus turistico Lima - Paracas"));
        p2.agregarServicio(new ServicioIncluido("Hotel", "Resort frente al mar"));
        p2.agregarServicio(new ServicioIncluido("Visita guiada", "Islas Ballestas en lancha"));
        p2.agregarDiaItinerario(new ItinerarioDia(1, "Salida de Lima y llegada a Paracas"));
        p2.agregarDiaItinerario(new ItinerarioDia(2, "Islas Ballestas y Reserva Nacional"));
        p2.agregarDiaItinerario(new ItinerarioDia(3, "Manana de playa y retorno"));
        registrar(p2);

        PaqueteTuristico p3 = new PaqueteTuristico("P003", "Cancun Total", 6, 5, "Internacional",
                3500.00, "05/09/2026", "10/09/2026", 12, "Activo");
        p3.agregarDestino(gestionDestino.buscar("Cancun"));
        p3.agregarServicio(new ServicioIncluido("Vuelo", "Vuelo Lima - Cancun - Lima"));
        p3.agregarServicio(new ServicioIncluido("Hotel", "Hotel 5 estrellas todo incluido"));
        p3.agregarServicio(new ServicioIncluido("Seguro de viaje", "Seguro internacional"));
        p3.agregarDiaItinerario(new ItinerarioDia(1, "Llegada a Cancun y check-in"));
        p3.agregarDiaItinerario(new ItinerarioDia(2, "Dia de playa en zona hotelera"));
        p3.agregarDiaItinerario(new ItinerarioDia(3, "Excursion a Chichen Itza"));
        registrar(p3);
    }

    public PaqueteTuristico buscar(String codigo) {
        for (int i = 0; i < totalPaquetes; i++) {
            if (paquetes[i].getCodigo().equalsIgnoreCase(codigo)) {
                return paquetes[i];
            }
        }
        return null;
    }

    public boolean registrar(PaqueteTuristico paquete) {
        if (totalPaquetes == paquetes.length) {
            return false;
        }
        if (buscar(paquete.getCodigo()) != null) {
            return false;
        }
        paquetes[totalPaquetes] = paquete;
        totalPaquetes++;
        return true;
    }

    public boolean actualizar(PaqueteTuristico paquete) {
        PaqueteTuristico encontrado = buscar(paquete.getCodigo());
        if (encontrado == null) {
            return false;
        }
        encontrado.setNombre(paquete.getNombre());
        encontrado.setDuracionDias(paquete.getDuracionDias());
        encontrado.setDuracionNoches(paquete.getDuracionNoches());
        encontrado.setTipo(paquete.getTipo());
        encontrado.setPrecioPorPersona(paquete.getPrecioPorPersona());
        encontrado.setFechaSalida(paquete.getFechaSalida());
        encontrado.setFechaRetorno(paquete.getFechaRetorno());
        int diferencia = paquete.getCupoMaximo() - encontrado.getCupoMaximo();
        encontrado.setCupoMaximo(paquete.getCupoMaximo());
        int nuevoDisponible = encontrado.getCupoDisponible() + diferencia;
        encontrado.setCupoDisponible(Math.max(0, Math.min(nuevoDisponible, paquete.getCupoMaximo())));
        encontrado.setEstado(paquete.getEstado());
        return true;
    }

    public boolean eliminar(String codigo) {
        for (int i = 0; i < totalPaquetes; i++) {
            if (paquetes[i].getCodigo().equalsIgnoreCase(codigo)) {
                for (int j = i; j < totalPaquetes - 1; j++) {
                    paquetes[j] = paquetes[j + 1];
                }
                paquetes[totalPaquetes - 1] = null;
                totalPaquetes--;
                return true;
            }
        }
        return false;
    }

    public PaqueteTuristico[] obtenerPaquetes() {
        return paquetes;
    }

    public int obtenerTotalPaquetes() {
        return totalPaquetes;
    }

    public String generarCodigo() {
        return String.format("P%03d", totalPaquetes + 1);
    }
}
