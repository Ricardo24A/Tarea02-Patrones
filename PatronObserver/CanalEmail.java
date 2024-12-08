package PatronObserver;

public class CanalEmail implements CanalNotificator{
    private String email;

    public CanalEmail(String email) {
        this.email = email;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada al correo " + email + ": El estado de la compra es " + compra.getEstado());
    }

}
