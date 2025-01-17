import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import PatronFactoryMethod.Boleto;
import PatronFactoryMethod.BoletoFactory;
import PatronFactoryMethod.BoletoGeneralFactory;
import PatronFactoryMethod.BoletoVIPFactory;
import PatronFactoryMethod.BoletoReservadoFactory;
import PatronChainOfResponsability.*;
import PatronObserver.*;
import PatronSingleton.*;

public class TicketBeatMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ManejadorAgente agenteSoporte = new AgenteSoporte(new AdministradorEvento());
        GestorNotificaciones gestor = new GestorNotificaciones();
        Compra compra = new Compra(gestor);
        TicketManager manager = TicketManager.getInstance();

        Boleto boletoSeleccionado = null;
        String estadoCompra = "Sin Boleto";

        boolean salir = false;
        List<String> eventosDisponibles = new ArrayList<>();
        eventosDisponibles.add("1. Concierto Rock - 10/05/2025");
        eventosDisponibles.add("2. Festival Jazz - 15/06/2025");
        eventosDisponibles.add("3. Pop Tour - 20/07/2025");

        while (!salir) {
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
                        System.out.println("1. Tarjeta de Crédito");
                        System.out.println("2. PayPal");
                        System.out.println("3. Transferencia");
                        System.out.print("Metodo de pago para reservar: ");
                        int mp = leerEntero(scanner);
                        String metodoPago = metodoPagoString(mp);
                        manager.reservarBoleto(boletoSeleccionado, metodoPago);
                        estadoCompra = "Reservado con " + metodoPago;
                        compra.actualizarEstado(estadoCompra);
                    }
                    break;

                    case 5:
                    if (boletoSeleccionado == null) {
                        System.out.println("No hay boleto seleccionado.");
                    } else {
                        double precio = boletoSeleccionado.calcularPrecio();
                        System.out.println("Precio del Boleto: " + precio);
                        System.out.println("1. Tarjeta de Credito");
                        System.out.println("2. PayPal");
                        System.out.println("3. Transferencia");
                        System.out.print("Método de pago para comprar: ");
                        int mp2 = leerEntero(scanner);
                        String metodoPago2 = metodoPagoString(mp2);
                
                        if (mp2 == 1) {
                            System.out.print("Ingrese el número de tarjeta: ");
                            String numeroTarjeta = scanner.nextLine();
                            System.out.print("Ingrese el nombre del titular: ");
                            String titular = scanner.nextLine();
                            System.out.print("Ingrese la fecha de expiración (MM/AA): ");
                            String fechaExpiracion = scanner.nextLine();
                            System.out.print("Ingrese el CVV: ");
                            String cvv = scanner.nextLine();
                        } else if (mp2 == 2) {
                            System.out.print("Ingrese el correo electrónico asociado a PayPal: ");
                            String correoPayPal = scanner.nextLine();
                        } else if (mp2 == 3) {
                            System.out.print("Ingrese el número de cuenta bancaria: ");
                            String numeroCuenta = scanner.nextLine();
                            System.out.print("Ingrese el nombre del banco: ");
                            String nombreBanco = scanner.nextLine();
                        }
                
                        System.out.print("Monto con el que pagas: ");
                        double monto = leerDouble(scanner);
                        manager.venderBoleto(boletoSeleccionado, monto);
                        
                        if (monto >= precio) {
                            estadoCompra = "Comprado con " + metodoPago2;
                            compra.actualizarEstado(estadoCompra);
                            System.out.println("Compra exitosa.");
                        } else {
                            estadoCompra = "Compra Fallida - Fondos Insuficientes";
                            compra.actualizarEstado(estadoCompra);
                            System.out.println("Compra fallida: Fondos insuficientes.");
                        }
                    }
                    break;
                

                case 6:
                    System.out.print("Tipo de incidente (pago, boletos, otro): ");
                    scanner.nextLine();
                    String tipo = scanner.nextLine();
                    System.out.println("Descripción:");
                    String desc = scanner.nextLine();
                    Incidente incidente = new Incidente(tipo, desc);
                    String resultado = agenteSoporte.manejar(incidente);
                    System.out.println("Resolucion: " + resultado);
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

    private static void configurarNotificaciones(Scanner scanner, GestorNotificaciones gestor) {
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

    private static void agregarCanal(Scanner scanner, GestorNotificaciones gestor) {
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
                System.out.print("Por favor ingresa tu número de telefono: ");
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
    
    private static void eliminarCanal(Scanner scanner, GestorNotificaciones gestor) {
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
                System.out.print("Por favor ingresa el número de telefono a eliminar: ");
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
    
    private static void seleccionarEvento(Scanner scanner, List<String> eventos) {
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

    private static Boleto seleccionarBoleto(Scanner scanner) {
        System.out.println("1. Boleto General");
        System.out.println("2. Boleto VIP");
        System.out.println("3. Boleto Reservado");
        System.out.print("Tipo de Boleto: ");
        int tipo = leerEntero(scanner);
        BoletoFactory factory = null;
        if (tipo == 1) factory = new BoletoGeneralFactory();
        if (tipo == 2) factory = new BoletoVIPFactory();
        if (tipo == 3) factory = new BoletoReservadoFactory();

        if (factory != null) {
            return factory.crearBoleto();
        }
        return null;
    }

    private static String metodoPagoString(int opcion) {
        if (opcion == 1) return "Tarjeta";
        if (opcion == 2) return "PayPal";
        if (opcion == 3) return "Transferencia";
        return "Otro";
    }

    private static int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double leerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.next();
        }
        return scanner.nextDouble();
    }
}

