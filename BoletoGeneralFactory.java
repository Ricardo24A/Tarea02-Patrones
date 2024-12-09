public class BoletoGeneralFactory extends BoletoFactory {
    @Override
    public Boleto crearBoleto() {
        return new BoletoGeneral();
    }
}
