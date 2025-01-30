package PruebasUnitarias;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import PatronFactoryMethod.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author usuario
 */
class BoletoSelectorTest {

    private BoletoSelector selectorGeneral;
    private BoletoSelector selectorVIP;
    private BoletoSelector selectorReservado;

    @BeforeEach
    void setUp() {
        selectorGeneral = new BoletoSelectorGeneral();
        selectorVIP = new BoletoSelectorVIP();
        selectorReservado = new BoletoSelectorReservado();
    }

    // Prueba para BoletoSelector - Caso Normal
    @Test
    void testBoletoSelectorMatchesValido() {
        assertTrue(selectorGeneral.matches(1));
        assertTrue(selectorVIP.matches(2));
        assertTrue(selectorReservado.matches(3));
    }

    // Prueba para BoletoSelector - Opción inválida (Fallo 1)
    @Test
    void testBoletoSelectorMatchesInvalido() {
        assertFalse(selectorGeneral.matches(99));
        assertFalse(selectorVIP.matches(99));
        assertFalse(selectorReservado.matches(99));
    }

    // Prueba para BoletoSelector - Creación de boleto sin coincidencia (Fallo 2)
    @Test
    void testBoletoSelectorCrearBoleto() {
        assertNotNull(selectorGeneral.crearBoleto());
        assertNotNull(selectorVIP.crearBoleto());
        assertNotNull(selectorReservado.crearBoleto());
    }
}

