/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.PatronFactoryMethod;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Adrian
 */
public class BoletoTest {
    private Boleto boletoGeneral;
    private Boleto boletoReservado;
    private Boleto boletoVIP;

    @BeforeEach
    void setUp() {
        boletoGeneral = new BoletoGeneral();
        boletoReservado = new BoletoReservado();
        boletoVIP = new BoletoVIP();
    }

    @AfterEach
    void tearDown() {
        boletoGeneral = null;
        boletoReservado = null;
        boletoVIP = null;
    }

    //Caso normal
    @Test
    void testGenerarBoleto() {
        assertEquals("Boleto General", boletoGeneral.obtenerDetalles());
        assertEquals(50.0, boletoGeneral.calcularPrecio());

        assertEquals("Boleto Reservado - Asiento asignado", boletoReservado.obtenerDetalles());
        assertEquals(100.0, boletoReservado.calcularPrecio());

        assertEquals("Boleto VIP - Acceso exclusivo", boletoVIP.obtenerDetalles());
        assertEquals(150.0, boletoVIP.calcularPrecio());
    }

    //Falla 1: Precio incorrecto
    @Test
    void testFallaPrecioIncorrecto() {
        assertNotEquals(60.0, boletoGeneral, "Error: El precio no debería ser 60.0, sino 50.0");
    }

    //Falla 2: Detalles incorrectos
    @Test
    void testFallaDetallesIncorrectos() {
        Boleto boletoErroneo = new BoletoVIP();
        assertNotEquals("Boleto VIP - Acceso regular", boletoErroneo.obtenerDetalles(), "Error: Los detalles del boleto VIP son incorrectos.");
    }
}
