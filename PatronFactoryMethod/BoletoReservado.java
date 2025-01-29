package PatronFactoryMethod;
public class BoletoReservado extends BoletoBase {
    public BoletoReservado() {
        this.precio = 100.0;
        this.detalles = "Boleto Reservado - Asiento asignado";
    }
}
