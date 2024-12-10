package PatronFactoryMethod;
public class BoletoReservadoFactory extends BoletoFactory {
    @Override
    public Boleto crearBoleto() {
        return new BoletoReservado();
    }
}
