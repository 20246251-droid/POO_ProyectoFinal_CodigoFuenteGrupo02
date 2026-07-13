package gestion;

import clases.DestinoTuristico;

public class GestionDestino {

    private DestinoTuristico[] destinos;
    private int totalDestinos;

    public GestionDestino() {
        destinos = new DestinoTuristico[50];
        totalDestinos = 0;

        registrar(new DestinoTuristico("Cusco", "Peru", "Cusco", "Ciudad imperial y Machu Picchu", "Templado seco", "Espanol", "imagenes/cusco.jpg"));
        registrar(new DestinoTuristico("Paracas", "Peru", "Pisco", "Reserva nacional e Islas Ballestas", "Desertico", "Espanol", "imagenes/paracas.jpg"));
        registrar(new DestinoTuristico("Cancun", "Mexico", "Cancun", "Playas del Caribe mexicano", "Tropical", "Espanol", "imagenes/cancun.jpg"));
        registrar(new DestinoTuristico("Iquitos", "Peru", "Iquitos", "Selva amazonica y rio Amazonas", "Tropical humedo", "Espanol", "imagenes/iquitos.jpg"));
    }

    public DestinoTuristico buscar(String nombre) {
        for (int i = 0; i < totalDestinos; i++) {
            if (destinos[i].getNombre().equalsIgnoreCase(nombre)) {
                return destinos[i];
            }
        }
        return null;
    }

    public boolean registrar(DestinoTuristico destino) {
        if (totalDestinos == destinos.length) {
            return false;
        }
        if (buscar(destino.getNombre()) != null) {
            return false;
        }
        destinos[totalDestinos] = destino;
        totalDestinos++;
        return true;
    }

    public boolean actualizar(DestinoTuristico destino) {
        DestinoTuristico encontrado = buscar(destino.getNombre());
        if (encontrado == null) {
            return false;
        }
        encontrado.setPais(destino.getPais());
        encontrado.setCiudad(destino.getCiudad());
        encontrado.setDescripcion(destino.getDescripcion());
        encontrado.setClima(destino.getClima());
        encontrado.setIdioma(destino.getIdioma());
        encontrado.setRutaImagen(destino.getRutaImagen());
        return true;
    }

    public boolean eliminar(String nombre) {
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

    public DestinoTuristico[] obtenerDestinos() {
        return destinos;
    }

    public int obtenerTotalDestinos() {
        return totalDestinos;
    }
}
