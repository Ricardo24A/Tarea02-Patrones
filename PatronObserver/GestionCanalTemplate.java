package PatronObserver;

import java.util.Scanner;

public abstract class GestionCanalTemplate {
    protected abstract void operarCanal(GestorNotificaciones gestor, CanalNotificator canal);

    public void gestionarCanal(Scanner scanner, GestorNotificaciones gestor) {
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. App");
        System.out.print("Seleccione el tipo de canal: ");
        
        int tipo = scanner.nextInt();
        scanner.nextLine();

        CanalNotificator canal = null;

        switch (tipo) {
            case 1:
                System.out.print("Por favor ingresa tu correo electrónico: ");
                canal = new CanalEmail(scanner.nextLine());
                break;
            case 2:
                System.out.print("Por favor ingresa tu número de teléfono: ");
                canal = new CanalSMS(scanner.nextLine());
                break;
            case 3:
                System.out.print("Por favor ingresa tu usuario de la app: ");
                canal = new CanalApp(scanner.nextLine());
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        if (canal != null) {
            operarCanal(gestor, canal);
        }
    }
}
