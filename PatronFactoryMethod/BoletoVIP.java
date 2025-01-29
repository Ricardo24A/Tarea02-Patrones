package PatronFactoryMethod;
public class BoletoVIP extends BoletoBase {
    public BoletoVIP() {
        this.precio = 150.0;
        this.detalles = "Boleto VIP - Acceso exclusivo";
    }
}
