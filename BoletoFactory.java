public abstract class BoletoFactory {
    
    public abstract Boleto crearBoleto();

    public void procesarBoleto() {
        Boleto boleto = crearBoleto();
        boleto.generarBoleto();
        System.out.println("Detalles: " + boleto.obtenerDetalles());
        System.out.println("Precio: " + boleto.calcularPrecio() + " USD");
    }
}
