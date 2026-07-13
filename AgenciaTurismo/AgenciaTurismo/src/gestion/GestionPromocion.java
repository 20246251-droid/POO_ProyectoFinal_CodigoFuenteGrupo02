package gestion;

import clases.Promocion;
import clases.PromocionMontoFijo;
import clases.PromocionPorcentaje;
import clases.PromocionTemporada;

public class GestionPromocion {

    private Promocion[] promociones;
    private int totalPromociones;

    public GestionPromocion() {
        promociones = new Promocion[50];
        totalPromociones = 0;

        registrar(new PromocionPorcentaje("PR01", "Descuento lanzamiento Cusco", "P001",
                "01/07/2026", "31/08/2026", 10));
        registrar(new PromocionMontoFijo("PR02", "Descuento fijo Paracas", "P002",
                "01/07/2026", "31/07/2026", 50));
        registrar(new PromocionTemporada("PR03", "Temporada de verano Cancun", "P003",
                "01/09/2026", "30/09/2026", "Verano", 15));
    }

    public Promocion buscar(String codigo) {
        for (int i = 0; i < totalPromociones; i++) {
            if (promociones[i].getCodigo().equalsIgnoreCase(codigo)) {
                return promociones[i];
            }
        }
        return null;
    }

    public Promocion buscarVigentePorPaquete(String codigoPaquete, String fecha) {
        for (int i = 0; i < totalPromociones; i++) {
            if (promociones[i].getCodigoPaquete().equalsIgnoreCase(codigoPaquete)
                    && promociones[i].estaVigente(fecha)) {
                return promociones[i];
            }
        }
        return null;
    }

    public boolean registrar(Promocion promocion) {
        if (totalPromociones == promociones.length) {
            return false;
        }
        if (buscar(promocion.getCodigo()) != null) {
            return false;
        }
        promociones[totalPromociones] = promocion;
        totalPromociones++;
        return true;
    }

    public boolean eliminar(String codigo) {
        for (int i = 0; i < totalPromociones; i++) {
            if (promociones[i].getCodigo().equalsIgnoreCase(codigo)) {
                for (int j = i; j < totalPromociones - 1; j++) {
                    promociones[j] = promociones[j + 1];
                }
                promociones[totalPromociones - 1] = null;
                totalPromociones--;
                return true;
            }
        }
        return false;
    }

    public Promocion[] obtenerPromociones() {
        return promociones;
    }

    public int obtenerTotalPromociones() {
        return totalPromociones;
    }
}
