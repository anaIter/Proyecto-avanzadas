package co.edu.uniquindio.proyecto.servicios;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.servicios.impl.NotificacionServicioImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificacionServicioImplTest {

    @Mock
    private SimpMessagingTemplate template;

    @InjectMocks
    private NotificacionServicioImpl notificacionService;

    @Test
    void testEnviarNotificacion() {
        Reporte mensaje = new Reporte();
        mensaje.setTitulo("Alerta");
        mensaje.setDescripcion("Esto es una prueba");

        notificacionService.enviarNotificacion(mensaje);

        verify(template, times(1)).convertAndSend("/topic/reports", mensaje);
    }
}



