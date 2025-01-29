package PatronFactoryMethod;

public class BoletoSelectorReservado extends BoletoSelector {
    @Override
    public boolean matches(int opcion) {
        return opcion == 3;
    }

    @Override
    public Boleto crearBoleto() {
        return new BoletoReservado();
    }
}
