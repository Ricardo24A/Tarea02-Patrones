public class BoletoVIPFactory extends BoletoFactory {
    @Override
    public Boleto crearBoleto() {
        return new BoletoVIP();
    }
}
