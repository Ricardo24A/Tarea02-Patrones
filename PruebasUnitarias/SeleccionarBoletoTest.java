/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.test;

import ec.edu.espol.ticketbeat.PatronFactoryMethod.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Adrian
 */
public class SeleccionarBoletoTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @AfterEach
    void tearDown() {
        System.setOut(originalOut); 
    }

    // Caso Normal: Se selecciona un boleto válido
    @Test
    void testSeleccionarBoleto_CasoNormal() {
        Scanner scanner = new Scanner("1\n"); 
        Boleto resultado = seleccionarBoleto(scanner);
        assertNotNull(resultado);
        assertTrue(resultado instanceof BoletoGeneral);
    }

    // Falla 1: Se ingresa un número inválido
    @Test
    void testSeleccionarBoleto_OpcionInvalida() {
        Scanner scanner = new Scanner("4\n"); 
        Boleto resultado = seleccionarBoleto(scanner);
        assertNull(resultado);
    }

    // Falla 2: Se ingresa un valor no numérico
    @Test
    void testSeleccionarBoleto_EntradaNoNumerica() {
        Scanner scanner = new Scanner("abc\n1\n");
        Boleto resultado = seleccionarBoleto(scanner);
        assertNotNull(resultado);
        assertTrue(resultado instanceof BoletoGeneral); 
    }

    // Método a probar 
    private Boleto seleccionarBoleto(Scanner scanner) {
        System.setOut(new PrintStream(outputStream));
        System.out.println("1. Boleto General");
        System.out.println("2. Boleto VIP");
        System.out.println("3. Boleto Reservado");
        System.out.print("Tipo de Boleto: ");
        int tipo = leerEntero(scanner);

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

    // Metodo para leer el entero
    private int leerEntero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.next(); 
        }
        return scanner.nextInt();
    }
}

