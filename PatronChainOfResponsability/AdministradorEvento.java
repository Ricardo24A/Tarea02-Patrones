package PatronChainOfResponsability;

public class AdministradorEvento extends ManejadorAgente {
    
    public AdministradorEvento() {
        super("AdministradorEvento");
    }

    @Override
    public String manejar(Incidente incidente) {
        return "Incidente escalado y manejado por " + tipoAgente + ": " + incidente.getDescripcion();
    }
}