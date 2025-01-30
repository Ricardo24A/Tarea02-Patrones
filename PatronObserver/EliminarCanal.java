package PatronObserver;

public class EliminarCanal extends GestionCanalTemplate {
    @Override
    protected void operarCanal(GestorNotificaciones gestor, CanalNotificator canal) {
        gestor.eliminarCanal(canal);
        System.out.println("Canal eliminado.");
    }
}

