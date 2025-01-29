package PatronSingleton;

public class MetodoPago {
    private String nombre;

    public MetodoPago(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static MetodoPago parseOpcion(int opcion) {
        switch (opcion) {
            case 1: return new MetodoPago("Tarjeta");
            case 2: return new MetodoPago("PayPal");
            case 3: return new MetodoPago("Transferencia");
            default: return new MetodoPago("Otro");
        }
    }
}
