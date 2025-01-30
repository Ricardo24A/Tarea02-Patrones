package PruebasUnitarias;


import PatronFactoryMethod.Boleto;
import PatronFactoryMethod.BoletoGeneral;
import PatronObserver.Compra;
import PatronObserver.GestorNotificaciones;
import PatronSingleton.TicketManager;
import Refactoring.ReservaDatos;

import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author usuario
 */
class ReservaDatosTest {

    private ReservaDatos reserva;
    private Compra compra;

    @BeforeEach
    void setUp() {
        compra = new Compra(new GestorNotificaciones());
    }

    // Caso normal: Crear una reserva con datos válidos
    @Test
    void testReservaDatosValido() {
        reserva = new ReservaDatos(1, new BoletoGeneral(), compra);
        assertEquals(1, reserva.getMetodoPago());
        assertNotNull(reserva.getBoleto());
        assertNotNull(reserva.getCompra());
    }

    // Falla 1: Método de pago inválido
    @Test
    void testReservaDatosMetodoPagoInvalido() {
        reserva = new ReservaDatos(99, new BoletoGeneral(), compra);
        assertEquals(99, reserva.getMetodoPago());
    }

    // Falla 2: Boleto nulo
    @Test
    void testReservaDatosBoletoNulo() {
        reserva = new ReservaDatos(1, null, compra);
        assertNull(reserva.getBoleto());
    }
}

