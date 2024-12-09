public class BoletoGeneral implements Boleto {
    private double precio;
    private String detalles;

    public BoletoGeneral() {
        this.precio = 50.0;
        this.detalles = "Boleto General";
    }

    @Override
    public void generarBoleto() {
        System.out.println("Generando boleto general...");
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
