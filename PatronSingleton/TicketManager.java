package PatronSingleton;

import PatronFactoryMethod.Boleto;

public class TicketManager {
    private static TicketManager instance = new TicketManager();

    private TicketManager(){};

    public static TicketManager getInstance(){
        return instance;
    }

    public void reservarBoleto(Boleto b,String metodoPago){
        if(metodoPago.equalsIgnoreCase("Credit Cart") || metodoPago.equalsIgnoreCase("Debit Cart")){
            System.out.println("Boleto reservado. \nPrecio:"+b.calcularPrecio()+"\nDetalles:"+b.obtenerDetalles());
        }
        else{
            System.out.println("No se pudo reservar el boleto. Error.");
        }
    }

    public void venderBoleto(Boleto b,double valor){
        b.generarBoleto();
        if(valor >= b.calcularPrecio()){
            System.out.println("Boleto vendido.");
        }
        else{
            System.out.println("Venta no realizada. Valor insuficiente.");
        }
    }
}
