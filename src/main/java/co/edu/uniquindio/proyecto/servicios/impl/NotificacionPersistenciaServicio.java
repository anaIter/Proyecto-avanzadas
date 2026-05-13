package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.dto.NotificacionRespuestaDTO;
import co.edu.uniquindio.proyecto.entidad.NotificacionUsuario;
import co.edu.uniquindio.proyecto.repositorios.NotificacionRepositorio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionPersistenciaServicio {

    private final NotificacionRepositorio notificacionRepositorio;
    private final WebSocketNotificationService wsNotificationService;

    // ✅ Guarda en BD Y envía por WebSocket
    public void crearYEnviar(String idUsuario, NotificacionDTO dto) {
        NotificacionUsuario notif = NotificacionUsuario.builder()
                .idUsuario(new ObjectId(idUsuario))
                .titulo(dto.getTitulo())
                .mensaje(dto.getDescripcion())
                .tipo(dto.getTopic())
                .leido(false)
                .fecha(LocalDateTime.now())
                .build();
        notificacionRepositorio.save(notif);
        wsNotificationService.notificarClienteEspecifico(idUsuario, dto);
    }

    public List<NotificacionRespuestaDTO> obtenerPorUsuario(String idUsuario) {
        return notificacionRepositorio
                .findByIdUsuarioOrderByFechaDesc(new ObjectId(idUsuario))
                .stream()
                .map(n -> NotificacionRespuestaDTO.builder()
                        .id(n.getId().toHexString())
                        .titulo(n.getTitulo())
                        .mensaje(n.getMensaje())
                        .tipo(n.getTipo())
                        .leido(n.isLeido())
                        .fecha(n.getFecha())
                        .build())
                .toList();
    }

    public void marcarLeida(String idNotificacion) {
        notificacionRepositorio.findById(new ObjectId(idNotificacion)).ifPresent(n -> {
            n.setLeido(true);
            notificacionRepositorio.save(n);
        });
    }

    public void marcarTodasLeidas(String idUsuario) {
        List<NotificacionUsuario> notifs = notificacionRepositorio
                .findByIdUsuarioOrderByFechaDesc(new ObjectId(idUsuario));
        notifs.forEach(n -> n.setLeido(true));
        notificacionRepositorio.saveAll(notifs);
    }
}