package PatronObserver;

public class AgregarCanal extends GestionCanalTemplate {
    @Override
    protected void operarCanal(GestorNotificaciones gestor, CanalNotificator canal) {
        gestor.agregarCanal(canal);
        System.out.println("Canal agregado con éxito.");
    }
}

