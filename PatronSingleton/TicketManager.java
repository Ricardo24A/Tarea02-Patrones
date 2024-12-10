package PatronSingleton;

import PatronFactoryMethod.Boleto;

public class TicketManager {
    private static TicketManager instance = new TicketManager();

    private TicketManager(){};

    public static TicketManager getInstance(){
        return instance;
    }

    public void reservarBoleto(Boleto b){
        System.out.println("Boleto reservado. \nPrecio:"+b.calcularPrecio()+"\nDetalles:"+b.obtenerDetalles());
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
