package PatronChainOfResponsability;

public class AgenteSoporte extends ManejadorAgente {
    protected ManejadorAgente administrador;

    public AgenteSoporte(ManejadorAgente administrador) {
        super("AgenteSoporte");
        this.administrador = administrador;
    }

    @Override
    public String manejar(Incidente incidente) {
        if ("pago".equals(incidente.getTipo())) {
            return "Problema de pago resuelto por " + tipoAgente + ": " + incidente.getDescripcion();
        } else if ("boletos".equals(incidente.getTipo())) {
            return "Problema de acceso a boletos resuelto por " + tipoAgente + ": " + incidente.getDescripcion();
        } else if (administrador != null) {
            return administrador.manejar(incidente);
        } else {
            return "El incidente no pudo ser resuelto.";
        }
    }
}

