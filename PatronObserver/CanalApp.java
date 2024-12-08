package PatronObserver;

public class CanalApp implements CanalNotificator{
    private String aplicacion;

    public CanalApp(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    @Override
    public void actualizar(Compra compra) {
        System.out.println("Notificación enviada a la aplicación " + aplicacion + ": El estado de la compra es " + compra.getEstado());
    }

}
