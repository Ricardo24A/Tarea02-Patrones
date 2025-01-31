/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.test;

import ec.edu.espol.ticketbeat.PatronChainOfResponsability.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author Adrian
 */
public class ReportarIncidenteTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // Captura la salida de consola
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restaura la salida de consola original
    }

    // Caso Normal: Se reporta un incidente de pago
    @Test
    void testReportarIncidente_CasoNormal() {
        Scanner scanner = new Scanner("pago\nError al procesar el pago con tarjeta.\n");
        ManejadorAgente administrador = new AdministradorEvento();
        ManejadorAgente agenteSoporte = new AgenteSoporte(administrador);

        reportarIncidente(scanner, agenteSoporte);

        String salida = outputStream.toString();
        assertTrue(salida.contains("Resolucion: Problema de pago resuelto por AgenteSoporte: Error al procesar el pago con tarjeta."));
    }

    // Falla 1: Se reporta un tipo de incidente desconocido
    @Test
    void testReportarIncidente_TipoDesconocido() {
        Scanner scanner = new Scanner("seguridad\nIntento de acceso no autorizado.\n");
        ManejadorAgente administrador = new AdministradorEvento();
        ManejadorAgente agenteSoporte = new AgenteSoporte(administrador);

        reportarIncidente(scanner, agenteSoporte);

        String salida = outputStream.toString();
        assertTrue(salida.contains("Resolucion: Incidente escalado y manejado por AdministradorEvento: Intento de acceso no autorizado."));
    }

    // Falla 2: Se deja la descripción vacía
    @Test
    void testReportarIncidente_DescripcionVacia() {
        Scanner scanner = new Scanner("boletos\n\n"); // Descripción vacía
        ManejadorAgente administrador = new AdministradorEvento();
        ManejadorAgente agenteSoporte = new AgenteSoporte(administrador);

        reportarIncidente(scanner, agenteSoporte);

        String salida = outputStream.toString();
        assertTrue(salida.contains("Resolucion: Problema de acceso a boletos resuelto por AgenteSoporte: "));
    }

  
    // Metodo 
    private void reportarIncidente(Scanner scanner, ManejadorAgente agenteSoporte) {
        System.out.print("Tipo de incidente (pago, boletos, otro): ");
        String tipo = scanner.nextLine();
        System.out.println("Descripción:");
        String desc = scanner.nextLine();
        Incidente incidente = new Incidente(tipo, desc);
        String resultado = agenteSoporte.manejar(incidente);
        System.out.println("Resolucion: " + resultado);
    }
}
