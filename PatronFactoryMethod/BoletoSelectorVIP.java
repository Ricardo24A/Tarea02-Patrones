package PatronFactoryMethod;

public class BoletoSelectorVIP extends BoletoSelector {
    @Override
    public boolean matches(int opcion) {
        return opcion == 2;
    }

    @Override
    public Boleto crearBoleto() {
        return new BoletoVIP();
    }
}
