1. Chain of Responsibility
Descripción: Permite procesar solicitudes de forma encadenada, pasando por varios manejadores hasta encontrar quién los resuelva.
Aplicación en el código: Se implementó con las clases ManejadorAgente, AgenteSoporte, AdministradorEvento e Incidente. Primero el AgenteSoporte intenta resolver el incidente (pago o boletos), y si no puede, lo “escala” al AdministradorEvento.
Justificación: Se facilita el manejo de distintos tipos de incidentes sin usar if/else extensos; cada agente tiene su responsabilidad clara y se delega o escala según el tipo.

2. Observer
Descripción: Define una relación uno a muchos en la que, cuando un objeto cambia su estado, notifica automáticamente a sus observadores.
Aplicación en el código: Se usa en las clases Compra, GestorNotificaciones y los canales CanalEmail, CanalSMS, CanalApp. Cuando Compra se actualiza, el GestorNotificaciones llama al método actualizar de todos los canales para informar al usuario.
Justificación: Permite enviar notificaciones (email, SMS, app) de forma automática sin acoplar la clase Compra a cada canal específico.

3. Factory Method
Descripción: Proporciona una interfaz para crear objetos en una superclase, dejando que las subclases decidan qué clase concreta instanciar.
Aplicación en el código: Se ve en las clases BoletoFactory y sus derivadas (BoletoGeneralFactory, BoletoVIPFactory, BoletoReservadoFactory). Cada fábrica concreta se encarga de instanciar un tipo de boleto distinto.
Justificación: Evita usar múltiples new o switch al crear diferentes tipos de boletos (general, VIP, reservado). Permite extender fácilmente el sistema con nuevos tipos de boleto sin modificar la lógica principal.

4. Singleton
Descripción: Garantiza que una clase tenga una única instancia y proporciona un punto de acceso global a la misma.
Aplicación en el código: La clase TicketManager se define con un constructor privado y un método estático getInstance(), asegurando una sola instancia.
Justificación: Centraliza la lógica de venta y reservas, garantizando que todas las operaciones (reservar, vender) se realicen sobre la misma instancia, evitando inconsistencias al manejar boletos o transacciones.
