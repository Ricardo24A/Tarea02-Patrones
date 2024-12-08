package PatronObserver;
public class Compra {
    private String estado;
    private GestorNotificaciones gestorNotificaciones;

    public Compra(GestorNotificaciones gestorNotificaciones) {
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public String getEstado() {
        return estado;
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        notificar();
    }

    public void notificar() {
        gestorNotificaciones.notificarCanales(this);
    }
}


