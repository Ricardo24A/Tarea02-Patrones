package PatronObserver;

import java.util.Objects;

public class CanalEmail implements CanalNotificator{
    private String email;

    public CanalEmail(String email) {
        this.email = email;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada al correo " + email + ": El estado de la compra es " + compra.getEstado());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; 
        if (o == null || getClass() != o.getClass()) return false;
        CanalEmail that = (CanalEmail) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
