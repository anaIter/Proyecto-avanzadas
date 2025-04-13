package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.servicios.NotificacionServicio;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServicioImpl implements NotificacionServicio {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificacionServicioImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void enviarNotificacion(Reporte reporte) {
        messagingTemplate.convertAndSend("/topic/reports", reporte);
    }
}

