package PatronObserver;
import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones {
     private List<CanalNotificator> observadores;

    public GestorNotificaciones() {
        this.observadores = new ArrayList<>();
    }

    public void agregarCanal(CanalNotificator canal) {
        observadores.add(canal);
    }

    public void eliminarCanal(CanalNotificator canal) {
        observadores.remove(canal);
    }

    public void notificarCanales(Compra compra) {
        for (CanalNotificator canal : observadores) {
            canal.actualizar(compra);
        }
    }

}
