/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.test;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Adrian
 */
public class LeerEnteroTest {
    private Scanner scanner;

    @AfterEach
    void tearDown() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // Caso Normal: Entrada con número entero válido
    @Test
    void testLeerEntero_CasoNormal() {
        scanner = new Scanner("15");
        int resultado = leerEntero(scanner);
        assertEquals(15, resultado);
    }

    // Falla 1: Entrada con texto antes del número
    @Test
    void testLeerEntero_EntradaConTexto() {
        scanner = new Scanner("abc 10");
        int resultado = leerEntero(scanner);
        assertEquals(10, resultado);
    }

    // Falla 2: Entrada sin números (debe lanzar excepción correctamente)
    @Test
    void testLeerEntero_SoloTexto() {
        scanner = new Scanner("palabra palabra otraPalabra");
        assertThrows(NoSuchElementException.class, () -> leerEntero(scanner));
    }

    // Método corregido para evitar errores
    private int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            if (!scanner.hasNext()) {  // Si no hay más tokens, evitamos bucle infinito
                throw new NoSuchElementException("No se encontró un número en la entrada.");
            }
            scanner.next(); // Descarta el valor no válido
        }
        return scanner.nextInt();
    }
}
