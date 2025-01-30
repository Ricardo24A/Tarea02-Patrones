package Refactoring;

import java.util.Scanner;

import PatronFactoryMethod.Boleto;
import PatronObserver.Compra;
import PatronSingleton.TicketManager;

public class ReservaDatos {
    private int metodoPago;
    private static Boleto boleto;
        private static Compra compra;
            
                public ReservaDatos(int metodoPago, Boleto boleto, Compra compra) {
                    this.metodoPago = metodoPago;
                    this.boleto = boleto;
                    this.compra = compra;
                }
            
                public int getMetodoPago() { return metodoPago; }
                public Boleto getBoleto() { return boleto; }
                public Compra getCompra() { return compra; }
            
                public static void reservarBoleto(Scanner scanner, TicketManager manager) {
                System.out.println("1. Tarjeta de Crédito");
                System.out.println("2. PayPal");
                System.out.println("3. Transferencia");
                System.out.print("Método de pago para reservar: ");
                
                int metodoPago = scanner.nextInt();
                scanner.nextLine();
                
                ReservaDatos datos = new ReservaDatos(metodoPago, boleto, compra);
    manager.reservarBoleto(datos);
}
}
