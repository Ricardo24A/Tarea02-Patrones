package PatronFactoryMethod;

public abstract class BoletoSelector {
    public abstract boolean matches(int opcion);
    public abstract Boleto crearBoleto();
}
