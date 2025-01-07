package PatronChainOfResponsability;

public class Incidente {
    private final String tipo;
    private final String descripcion;

    public Incidente(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
