/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author Adrian
 */
public class SeleccionarEventoTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); // Restaurar la salida original
    }

    // Caso Normal: Selección de evento dentro del rango
    @Test
    void testSeleccionarEvento_CasoNormal() {
        System.setOut(new PrintStream(outputStream)); // Capturar salida

        List<String> eventos = Arrays.asList("Concierto", "Teatro", "Feria");
        Scanner scanner = new Scanner("2\n");

        seleccionarEvento(scanner, eventos);

        String salida = outputStream.toString();
        assertTrue(salida.contains("Evento seleccionado: Teatro"));

        scanner.close();
    }

    // Falla 1: Número fuera del rango
    @Test
    void testSeleccionarEvento_NumeroFueraDeRango() {
        System.setOut(new PrintStream(outputStream)); 

        List<String> eventos = Arrays.asList("Concierto", "Teatro", "Feria");
        Scanner scanner = new Scanner("5\n"); 

        seleccionarEvento(scanner, eventos);

        String salida = outputStream.toString();
        assertFalse(salida.contains("Evento seleccionado:")); 

        scanner.close();
    }

    // Falla 2: Entrada no numérica antes del número válido
    @Test
    void testSeleccionarEvento_EntradaNoNumerica() {
        System.setOut(new PrintStream(outputStream));

        List<String> eventos = Arrays.asList("Concierto", "Teatro", "Feria");
        Scanner scanner = new Scanner("abc\n2\n"); 

        seleccionarEvento(scanner, eventos);

        String salida = outputStream.toString();
        assertTrue(salida.contains("Evento seleccionado: Teatro")); 

        scanner.close();
    }

    // Métodos auxiliares
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

    private int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next(); 
        }
        return scanner.nextInt();
    }
}