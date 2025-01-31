/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.PatronChainOfResponsability;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 *
 * @author Adrian
 */
class ManejadorAgenteTest {
    private AdministradorEvento administrador;
    private AgenteSoporte agenteSoporte;

    @BeforeEach
    void setUp() {
        administrador = new AdministradorEvento();
        agenteSoporte = new AgenteSoporte(administrador);
    }

    // Caso Normal: Un AgenteSoporte maneja un incidente de pago correctamente
    @Test
    void testManejoIncidentePago() {
        Incidente incidente = new Incidente("pago", "Error al procesar el pago con tarjeta.");
        String resultado = agenteSoporte.manejar(incidente);

        assertEquals("Problema de pago resuelto por AgenteSoporte: Error al procesar el pago con tarjeta.", resultado);
    }

    // Falla 1: Incidente desconocido se escala correctamente al AdministradorEvento
    @Test
    void testIncidenteEscaladoAdministrador() {
        Incidente incidente = new Incidente("seguridad", "Intento de acceso no autorizado.");
        String resultado = agenteSoporte.manejar(incidente);

        assertEquals("Incidente escalado y manejado por AdministradorEvento: Intento de acceso no autorizado.", resultado);
    }

    // Falla 2: No hay administrador disponible y el incidente no puede resolverse
    @Test
    void testIncidenteNoResueltoSinAdministrador() {
        AgenteSoporte agenteSinAdmin = new AgenteSoporte(null);
        Incidente incidente = new Incidente("seguridad", "Intento de acceso no autorizado.");
        String resultado = agenteSinAdmin.manejar(incidente);

        assertEquals("El incidente no pudo ser resuelto.", resultado);
    }
}