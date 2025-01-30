import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import PatronChainOfResponsability.*;
import PatronFactoryMethod.*;
import PatronObserver.*;
import PatronSingleton.*;

public class TicketBeatMenu {

    public void runMenu() {
        Scanner scanner = new Scanner(System.in);

        ManejadorAgente agenteSoporte = new AgenteSoporte(new AdministradorEvento());
        GestorNotificaciones gestor = new GestorNotificaciones();
        Compra compra = new Compra(gestor);
        TicketManager manager = TicketManager.getInstance();

        Boleto boletoSeleccionado = null;
        boolean salir = false;

        List<String> eventosDisponibles = new ArrayList<>();
        eventosDisponibles.add("1. Concierto Rock - 10/05/2025");
        eventosDisponibles.add("2. Festival Jazz - 15/06/2025");
        eventosDisponibles.add("3. Pop Tour - 20/07/2025");

        while (!salir) {
            mostrarMenu();
            int opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    configurarNotificaciones(scanner, gestor);
                    break;
                case 2:
                    seleccionarEvento(scanner, eventosDisponibles);
                    System.out.println("El evento ha sido seleccionado con exito.");
                    break;
                case 3:
                    boletoSeleccionado = seleccionarBoleto(scanner);
                    if (boletoSeleccionado != null) {
                        System.out.println("El boleto seleccionado es: " 
                            + boletoSeleccionado.obtenerDetalles() 
                            + " con un precio de: " 
                            + boletoSeleccionado.calcularPrecio() 
                            + " USD");
                    }
                    break;
                case 4:
                    if (boletoSeleccionado == null) {
                        System.out.println("No hay boleto seleccionado.");
                    } else {
                        reservarBoleto(scanner, manager, compra, boletoSeleccionado);
                    }
                    break;
                case 5:
                    if (boletoSeleccionado == null) {
                        System.out.println("No hay boleto seleccionado.");
                    } else {
                        pagarBoleto(scanner, manager, compra, boletoSeleccionado);
                    }
                    break;
                case 6:
                    reportarIncidente(scanner, agenteSoporte);
                    break;
                case 7:
                    System.out.println("Estado Actual de la Compra: " + compra.getEstado());
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n====== TICKETBEAT ======");
        System.out.println("1. Configurar Notificaciones");
        System.out.println("2. Buscar y Seleccionar Evento");
        System.out.println("3. Seleccionar Tipo de Boleto");
        System.out.println("4. Reservar Boleto");
        System.out.println("5. Pagar Boleto");
        System.out.println("6. Reportar Incidente");
        System.out.println("7. Ver Estado de la Compra");
        System.out.println("8. Salir");
        System.out.print("Opción: ");
    }

    private void configurarNotificaciones(Scanner scanner, GestorNotificaciones gestor) {
        boolean regresar = false;
        while (!regresar) {
            System.out.println("\n1. Agregar Canal");
            System.out.println("2. Eliminar Canal");
            System.out.println("3. Regresar");
            System.out.print("Opción: ");
            int op = leerEntero(scanner);
            switch (op) {
                case 1:
                    agregarCanal(scanner, gestor);
                    break;
                case 2:
                    eliminarCanal(scanner, gestor);
                    break;
                case 3:
                    regresar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void agregarCanal(Scanner scanner, GestorNotificaciones gestor) {
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. App");
        System.out.print("Canal: ");
        int tipo = leerEntero(scanner);
        scanner.nextLine();

        CanalNotificator canal = null;

        switch (tipo) {
            case 1:
                System.out.print("Por favor ingresa tu correo electronico: ");
                String email = scanner.nextLine();
                canal = new CanalEmail(email);
                break;
            case 2:
                System.out.print("Por favor ingresa tu numero de telefono: ");
                String telefono = scanner.nextLine();
                canal = new CanalSMS(telefono);
                break;
            case 3:
                System.out.print("Por favor ingresa tu usuario de la app: ");
                String user = scanner.nextLine();
                canal = new CanalApp(user);
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }

        if (canal != null) {
            gestor.agregarCanal(canal);
            System.out.println("Canal agregado con exito.");
        }
    }

    private void eliminarCanal(Scanner scanner, GestorNotificaciones gestor) {
        System.out.println("1. Email");
        System.out.println("2. SMS");
        System.out.println("3. App");
        System.out.print("Canal a Eliminar: ");
        int tipo = leerEntero(scanner);
        scanner.nextLine();

        CanalNotificator canalAEliminar = null;

        switch (tipo) {
            case 1:
                System.out.print("Por favor ingresa el correo electronico a eliminar: ");
                String email = scanner.nextLine();
                canalAEliminar = new CanalEmail(email);
                break;
            case 2:
                System.out.print("Por favor ingresa el numero de telefono a eliminar: ");
                String tel = scanner.nextLine();
                canalAEliminar = new CanalSMS(tel);
                break;
            case 3:
                System.out.print("Por favor ingresa el usuario de la app a eliminar: ");
                String user = scanner.nextLine();
                canalAEliminar = new CanalApp(user);
                break;
            default:
                System.out.println("Opcion no valida.");
                break;
        }

        if (canalAEliminar != null) {
            gestor.eliminarCanal(canalAEliminar);
            System.out.println("Canal eliminado.");
        }
    }

    private void seleccionarEvento(Scanner scanner, List<String> eventos) {
        System.out.println("\nEVENTOS DISPONIBLES:");
        for (String e : eventos) {
            System.out.println(e);
        }
        System.out.print("Selecciona un evento (ej: 1,2,3): ");
        int op = leerEntero(scanner);
        if (op >= 1 && op <= eventos.size()) {
            System.out.println("Evento seleccionado: " + eventos.get(op - 1));
        }
    }

    private Boleto seleccionarBoleto(Scanner scanner) {
        System.out.println("1. Boleto General");
        System.out.println("2. Boleto VIP");
        System.out.println("3. Boleto Reservado");
        System.out.print("Tipo de Boleto: ");
        int tipo = leerEntero(scanner);

        // Polimorfismo: en vez de switch, usamos una lista de "seleccionadores"
        List<BoletoSelector> selectors = Arrays.asList(
            new BoletoSelectorGeneral(),
            new BoletoSelectorVIP(),
            new BoletoSelectorReservado()
        );

        for (BoletoSelector sel : selectors) {
            if (sel.matches(tipo)) {
                return sel.crearBoleto();
            }
        }
        System.out.println("Opcion de boleto no valida.");
        return null;
    }

    private void reservarBoleto(Scanner scanner, TicketManager manager, Compra compra, Boleto boleto) {
        System.out.println("1. Tarjeta de Credito");
        System.out.println("2. PayPal");
        System.out.println("3. Transferencia");
        System.out.print("Metodo de pago para reservar: ");
        int mp = leerEntero(scanner);
        scanner.nextLine();

        MetodoPago metodo = MetodoPago.parseOpcion(mp);
        manager.reservarBoleto(boleto, metodo.getNombre());
        String estadoCompra = "Reservado con " + metodo.getNombre();
        compra.actualizarEstado(estadoCompra);
    }

    private void pagarBoleto(Scanner scanner, TicketManager manager, Compra compra, Boleto boleto) {
        System.out.println("Seleccione método de pago:");
        System.out.println("1. Tarjeta de Crédito");
        System.out.println("2. PayPal");
        System.out.println("3. Transferencia");
    
        int opcion = leerEntero(scanner);
        scanner.nextLine();
    
        MetodoPago metodo = MetodoPago.parseOpcion(opcion);
    
        if (metodo != null) {
            metodo.procesarPago(scanner, manager, compra, boleto);
        } else {
            System.out.println("Opción de pago no válida.");
        }
    }
    

    private void reportarIncidente(Scanner scanner, ManejadorAgente agenteSoporte) {
        System.out.print("Tipo de incidente (pago, boletos, otro): ");
        String tipo = scanner.nextLine();
        System.out.println("Descripción:");
        String desc = scanner.nextLine();
        Incidente incidente = new Incidente(tipo, desc);
        String resultado = agenteSoporte.manejar(incidente);
        System.out.println("Resolucion: " + resultado);
    }

    private int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }

    private double leerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.next();
        }
        return scanner.nextDouble();
    }
}