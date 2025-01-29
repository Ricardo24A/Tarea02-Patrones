package PatronFactoryMethod;

public class BoletoSelectorGeneral extends BoletoSelector {
    @Override
    public boolean matches(int opcion) {
        return opcion == 1;
    }

    @Override
    public Boleto crearBoleto() {
        return new BoletoGeneral();
    }
}
