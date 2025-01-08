package PatronObserver;

import java.util.Objects;

public class CanalSMS implements CanalNotificator {
    private String telefono;

    public CanalSMS(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada por SMS al número " + telefono + ": El estado de la compra es " + compra.getEstado());
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;
        CanalSMS that = (CanalSMS) o;
        return Objects.equals(telefono, that.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono);
    }

}
