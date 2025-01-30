package PatronSingleton;

import java.util.Scanner;

import PatronFactoryMethod.Boleto;
import PatronObserver.Compra;

public class MetodoPago {
    private String nombre;

    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static MetodoPago parseOpcion(int opcion) {
        switch (opcion) {
            case 1: return new MetodoPago("Tarjeta");
            case 2: return new MetodoPago("PayPal");
            case 3: return new MetodoPago("Transferencia");
            default: return new MetodoPago("Otro");
        }
    }

    public void procesarPago(Scanner scanner, TicketManager manager, Compra compra, Boleto boleto) {
        double precio = boleto.calcularPrecio();
        System.out.println("Precio del Boleto: " + precio);
        System.out.print("Monto con el que pagas: ");
        double monto = scanner.nextDouble();

        manager.venderBoleto(boleto, monto);

        if (monto >= precio) {
            String estadoCompra = "Comprado con " + nombre;
            compra.actualizarEstado(estadoCompra);
            System.out.println("Compra exitosa.");
        } else {
            String estadoCompra = "Compra Fallida - Fondos Insuficientes";
            compra.actualizarEstado(estadoCompra);
            System.out.println("Compra fallida: Fondos insuficientes.");
        }
    }
}
