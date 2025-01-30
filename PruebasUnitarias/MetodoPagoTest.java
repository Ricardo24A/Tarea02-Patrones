package PruebasUnitarias;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import PatronSingleton.MetodoPago;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author usuario
 */
class MetodoPagoTest {

    // Prueba para MetodoPago - Caso Normal
    @Test
    void testMetodoPagoParseOpcionValida() {
        assertEquals("Tarjeta", MetodoPago.parseOpcion(1).getNombre());
        assertEquals("PayPal", MetodoPago.parseOpcion(2).getNombre());
        assertEquals("Transferencia", MetodoPago.parseOpcion(3).getNombre());
    }

    // Prueba para MetodoPago - Opción inválida (Fallo 1)
    @Test
    void testMetodoPagoParseOpcionInvalida() {
        assertEquals("Otro", MetodoPago.parseOpcion(99).getNombre());
    }

    // Prueba para MetodoPago - Opción límite (Fallo 2)
    @Test
    void testMetodoPagoParseOpcionNegativa() {
        assertEquals("Otro", MetodoPago.parseOpcion(-1).getNombre());
    }
}
