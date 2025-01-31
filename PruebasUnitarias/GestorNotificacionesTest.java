/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.ticketbeat.PatronObserver;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Adrian
 */
public class GestorNotificacionesTest {
    private GestorNotificaciones gestor;
    private CanalApp canalApp;
    private CanalEmail canalEmail;
    private CanalSMS canalSMS;
    private Compra compra;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        gestor = new GestorNotificaciones();
        canalApp = new CanalApp("MiAplicacion");
        canalEmail = new CanalEmail("correo@example.com");
        canalSMS = new CanalSMS("123456789");
        gestor.agregarCanal(canalApp);
        gestor.agregarCanal(canalEmail);
        gestor.agregarCanal(canalSMS);
        compra = new Compra(gestor); 

        // Capturar salida de consola para validación
        System.setOut(new PrintStream(outputStream));
    }

 
    // Caso Normal: Todos los canales reciben la notificación correctamente
    @Test
    void testNotificacionExitosa() {
        compra.actualizarEstado("Pendiente");

        // Normalizar la salida eliminando espacios extra y estandarizando los saltos de línea
        String salida = outputStream.toString().trim().replace("\r\n", "\n").replace("\r", "\n");

        String esperado = 
            "Notificación enviada a la aplicación MiAplicacion: El estado de la compra es Pendiente\n" +
            "Notificación enviada al correo correo@example.com: El estado de la compra es Pendiente\n" +
            "Notificación enviada por SMS al número 123456789: El estado de la compra es Pendiente";

        // También normalizamos la salida esperada por si acaso
        esperado = esperado.replace("\r\n", "\n").replace("\r", "\n");

        assertEquals(esperado, salida, "Las notificaciones no se enviaron correctamente.");
    }

    @Test
    void testSinCanalesRegistrados() {
        GestorNotificaciones gestorVacio = new GestorNotificaciones();
        Compra compraSinCanales = new Compra(gestorVacio);
        
        compraSinCanales.actualizarEstado("Pendiente");

        String salida = outputStream.toString().trim().replace("\r\n", "\n").replace("\r", "\n");
        assertEquals("No hay canales registrados para recibir notificaciones.", salida, 
            "Error: No debería haber notificaciones ya que no hay canales registrados.");
    }

    @Test
    void testCompraEstadoNulo() {
        compra.actualizarEstado(null);

        String salida = outputStream.toString().trim().replace("\r\n", "\n").replace("\r", "\n");
        assertTrue(salida.contains("El estado de la compra es null"), 
            "Error: No se manejó correctamente un estado nulo.");
    }
}