public class BoletoReservado implements Boleto {
    private double precio;
    private String detalles;

    public BoletoReservado() {
        this.precio = 100.0;  // Ejemplo de precio
        this.detalles = "Boleto Reservado - Asiento asignado";
    }

    @Override
    public void generarBoleto() {
        System.out.println("Generando boleto reservado...");
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
