package PatronSingleton;

import PatronFactoryMethod.Boleto;
import Refactoring.ReservaDatos;

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

    public void reservarBoleto(ReservaDatos datos) {
    MetodoPago metodo = MetodoPago.parseOpcion(datos.getMetodoPago());
    String estadoCompra = "Reservado con " + metodo.getNombre();
    
    // Lógica de reserva
    datos.getCompra().actualizarEstado(estadoCompra);
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
