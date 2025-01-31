/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.test;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author Adrian
 */
public class LeerDoubleTest {
    private Scanner scanner;

    @AfterEach
    void tearDown() {
        if (scanner != null) {
            scanner.close();
        }
    }

    // Caso Normal: Entrada con número decimal válido
    @Test
    void testLeerDouble_CasoNormal() {
        scanner = new Scanner("15.75");
        double resultado = leerDouble(scanner);
        assertEquals(15.75, resultado, 0.0001);
    }

    // Falla 1: Entrada con texto antes del número
    @Test
    void testLeerDouble_EntradaConTexto() {
        scanner = new Scanner("abc 10.5");
        double resultado = leerDouble(scanner);
        assertEquals(10.5, resultado, 0.0001);
    }

    // Falla 2: Entrada sin números (debe lanzar excepción correctamente)
    @Test
    void testLeerDouble_SoloTexto() {
        scanner = new Scanner("palabra palabra otraPalabra");
        assertThrows(NoSuchElementException.class, () -> leerDouble(scanner));
    }

    // Método 
    private double leerDouble(Scanner scanner) {
        scanner.useLocale(Locale.US); // Solución para evitar conflictos con punto o coma decimal
        while (!scanner.hasNextDouble()) {
            scanner.next(); 
        }
        return scanner.nextDouble();
    }
}
