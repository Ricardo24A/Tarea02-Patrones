package PatronObserver;

public class CanalSMS implements CanalNotificator {
    private String telefono;

    public CanalSMS(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada por SMS al número " + telefono + ": El estado de la compra es " + compra.getEstado());
    }

}
