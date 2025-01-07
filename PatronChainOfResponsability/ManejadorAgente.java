package PatronChainOfResponsability;

public abstract class ManejadorAgente {
    protected String tipoAgente;

    public ManejadorAgente(String tipoAgente) {
        this.tipoAgente = tipoAgente;
    }

    public abstract String manejar(Incidente incidente);
}