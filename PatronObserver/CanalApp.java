package PatronObserver;

import java.util.Objects;

public class CanalApp implements CanalNotificator{
    private String aplicacion;

    public CanalApp(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada a la aplicación " + aplicacion + ": El estado de la compra es " + compra.getEstado());
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;
        CanalApp that = (CanalApp) o;
        return Objects.equals(aplicacion, that.aplicacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aplicacion);
    }

}
