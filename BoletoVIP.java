public class BoletoVIP implements Boleto {
    private double precio;
    private String detalles;

    public BoletoVIP() {
        this.precio = 150.0;
        this.detalles = "Boleto VIP - Acceso exclusivo";
    }

    @Override
    public void generarBoleto() {
        System.out.println("Generando boleto VIP...");
    }

    @Override
    public double calcularPrecio() {
        return precio;
    }

    @Override
    public String obtenerDetalles() {
        return detalles;
    }
}
