package PruebasUnitarias;


import PatronFactoryMethod.Boleto;
import PatronFactoryMethod.BoletoGeneral;
import PatronObserver.Compra;
import PatronObserver.GestorNotificaciones;
import PatronSingleton.MetodoPago;
import PatronSingleton.TicketManager;
import Refactoring.ReservaDatos;

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
class TicketManagerTest {

    private TicketManager manager;
    private Compra compra;

    @BeforeEach
    void setUp() {
        manager = TicketManager.getInstance();
        compra = new Compra(new GestorNotificaciones());
    }

    // Pruebas para el método reservarBoleto
    
    @Test
    void testReservarBoletoExitoso() {
        ReservaDatos datos = new ReservaDatos(1, new BoletoGeneral(), compra);
        manager.reservarBoleto(datos);
        assertEquals("Reservado con Tarjeta", compra.getEstado());
    }

    @Test
    void testReservarBoletoMetodoPagoInvalido() {
        ReservaDatos datos = new ReservaDatos(99, new BoletoGeneral(), compra);
        manager.reservarBoleto(datos);
        assertEquals("Reservado con Otro", compra.getEstado());
    }

    @Test
    void testReservarBoletoSinDatos() {
        ReservaDatos datos = new ReservaDatos(-1, null, compra);
        manager.reservarBoleto(datos);
        assertEquals("Reservado con Otro", compra.getEstado());
    }
    
    // Pruebas para el método venderBoleto
    
    @Test
    void testVenderBoletoExitoso() {
        Boleto boleto = new BoletoGeneral();
        MetodoPago metodo = MetodoPago.parseOpcion(1);
        manager.venderBoleto(boleto, 50.0, compra, metodo);
        assertEquals("Comprado con Tarjeta", compra.getEstado());
    }

    @Test
    void testVenderBoletoFondosInsuficientes() {
        Boleto boleto = new BoletoGeneral();
        MetodoPago metodo = MetodoPago.parseOpcion(1);
        manager.venderBoleto(boleto, 5.0, compra, metodo);
        assertNotEquals("Comprado con Tarjeta", compra.getEstado());
    }
    
    @Test
    void testVenderBoletoSinMetodoPago() {
        Boleto boleto = new BoletoGeneral();
        manager.venderBoleto(boleto, 50.0, compra, null);
        assertNotEquals("Comprado con ", compra.getEstado()); // Asegura que no cambia el estado
    }

}

