package PatronFactoryMethod;

public abstract class BoletoBase implements Boleto {
    protected double precio;
    protected String detalles;

    @Override
    public void generarBoleto() {
        System.out.println("Generando " + detalles + "...");
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