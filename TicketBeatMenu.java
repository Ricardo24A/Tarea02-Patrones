import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import PatronChainOfResponsability.*;
import PatronFactoryMethod.*;
import PatronObserver.*;
import PatronSingleton.*;
import Refactoring.ReservaDatos;

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
                        ReservaDatos.reservarBoleto(scanner, manager);
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
        new AgregarCanal().gestionarCanal(scanner, gestor);
    }
    
    private void eliminarCanal(Scanner scanner, GestorNotificaciones gestor) {
        new EliminarCanal().gestionarCanal(scanner, gestor);
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

    


    private void pagarBoleto(Scanner scanner, TicketManager manager, Compra compra, Boleto boleto) {
        System.out.println("Seleccione método de pago:");
        System.out.println("1. Tarjeta de Crédito");
        System.out.println("2. PayPal");
        System.out.println("3. Transferencia");
    
        int opcion = leerEntero(scanner);
        scanner.nextLine();
    
        MetodoPago metodo = MetodoPago.parseOpcion(opcion);
    
        if (metodo != null) {
            System.out.print("Monto con el que pagas: ");
            double monto = leerDouble(scanner);
    
            manager.venderBoleto(boleto, monto, compra, metodo);
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