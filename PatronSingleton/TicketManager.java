package PatronSingleton;

import PatronFactoryMethod.Boleto;

public class TicketManager {
    private static TicketManager instance;

    private TicketManager() {
    }

    public static TicketManager getInstance() {
        if (instance == null) {
            instance = new TicketManager();
        }
        return instance;
    }

    public void reservarBoleto(Boleto boleto, String metodoPago) {
        System.out.println("Boleto reservado con " + metodoPago);
        System.out.println("Detalles del boleto: " + boleto.obtenerDetalles());
    }

    public void venderBoleto(Boleto boleto, double montoPagado) {
        if (montoPagado >= boleto.calcularPrecio()) {
            System.out.println("Boleto vendido exitosamente.");
            double cambio = montoPagado - boleto.calcularPrecio();
            if (cambio > 0) {
                System.out.println("Cambio: " + cambio);
            }
        } else {
            System.out.println("Fondos insuficientes para comprar el boleto.");
        }
    }
}
