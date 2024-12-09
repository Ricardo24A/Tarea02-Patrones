import java.util.Scanner;

import PatronFactoryMethod.*;
import PatronObserver.*;
import PatronSingleton.TicketManager;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorNotificaciones gestor = new GestorNotificaciones();
        Compra compra = new Compra(gestor);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Agregar Canal (Email, SMS, App)");
            System.out.println("2. Eliminar Canal");
            System.out.println("3. Actualizar estado de la compra");
            System.out.println("4. Crear y gestionar Boleto");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    // Agregar canal
                    System.out.println("Elige un tipo de canal:");
                    System.out.println("1. Email");
                    System.out.println("2. SMS");
                    System.out.println("3. App");
                    System.out.print("Selecciona el canal: ");
                    int canalTipo = scanner.nextInt();
                    scanner.nextLine();  
                    System.out.print("Ingresa los datos del canal: ");
                    String dato = scanner.nextLine();

                    switch (canalTipo) {
                        case 1:
                            gestor.agregarCanal(new CanalEmail(dato));
                            break;
                        case 2:
                            gestor.agregarCanal(new CanalSMS(dato));
                            break;
                        case 3:
                            gestor.agregarCanal(new CanalApp(dato));
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;

                case 2:
                    // Eliminar canal
                    System.out.println("Selecciona el tipo de canal a eliminar:");
                    System.out.println("1. Email");
                    System.out.println("2. SMS");
                    System.out.println("3. App");
                    System.out.print("Selecciona el canal a eliminar: ");
                    int canalEliminar = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.print("Ingresa el dato del canal para eliminar: ");
                    String datoEliminar = scanner.nextLine();

                    CanalNotificator canalAEliminar = null;
                    switch (canalEliminar) {
                        case 1:
                            canalAEliminar = new CanalEmail(datoEliminar);
                            break;
                        case 2:
                            canalAEliminar = new CanalSMS(datoEliminar);
                            break;
                        case 3:
                            canalAEliminar = new CanalApp(datoEliminar);
                            break;
                        default:
                            System.out.println("Opcion no valida.");
                            break;
                    }

                    if (canalAEliminar != null) {
                        gestor.eliminarCanal(canalAEliminar);
                    }
                    break;

                case 3:
                    // Actualizar estado de la compra
                    System.out.print("Ingresa el nuevo estado de la compra: ");
                    String estado = scanner.nextLine();
                    compra.actualizarEstado(estado);
                    break;

                case 4:
                    System.out.println("Selecciona el tipo de boleto a crear:");
                    System.out.println("1. Boleto General");
                    System.out.println("2. Boleto VIP");
                    System.out.println("3. Boleto Reservado");
                    System.out.print("Selecciona una opción: ");
                    int tipoBoleto = scanner.nextInt();
                    scanner.nextLine();

                    BoletoFactory factory = null;
                    Boleto boleto = null;

                    switch (tipoBoleto) {
                        case 1:
                            factory = new BoletoGeneralFactory();
                            boleto = factory.crearBoleto();
                            break;
                        case 2:
                            factory = new BoletoVIPFactory();
                            boleto = factory.crearBoleto();
                            break;
                        case 3:
                            factory = new BoletoReservadoFactory();
                            boleto = factory.crearBoleto();
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }

                    if (factory != null) {
                        factory.procesarBoleto();
                        TicketManager manager = TicketManager.getInstance();
                        System.out.println("___________________________________________________________________________");
                        System.out.println("Eliga una opción: \n1. Reservar Boleto \n2. Comprar boleto");
                        int opcion2 = scanner.nextInt();
                        scanner.nextLine();
                        if(opcion2 == 1) manager.reservarBoleto(boleto);

                        if(opcion2 == 2) {
                            System.out.println("Precio: "+boleto.calcularPrecio());
                            System.out.println("Ingrese el monto a cancelar:");
                            int monto = scanner.nextInt();
                            scanner.nextLine();
                            manager.venderBoleto(boleto, monto);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Salió del programa");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

}
