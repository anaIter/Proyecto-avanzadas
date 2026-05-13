package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.NotificacionRespuestaDTO;
import co.edu.uniquindio.proyecto.servicios.impl.NotificacionPersistenciaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@RequiredArgsConstructor
public class NotificacionUsuarioControlador {

    private final NotificacionPersistenciaServicio notificacionPersistenciaServicio;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<NotificacionRespuestaDTO>> obtener(@PathVariable String idUsuario) {
        return ResponseEntity.ok(notificacionPersistenciaServicio.obtenerPorUsuario(idUsuario));
    }

    @PutMapping("/{idNotificacion}/leer")
    public ResponseEntity<Void> marcarLeida(@PathVariable String idNotificacion) {
        notificacionPersistenciaServicio.marcarLeida(idNotificacion);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/usuario/{idUsuario}/leer-todas")
    public ResponseEntity<Void> marcarTodasLeidas(@PathVariable String idUsuario) {
        notificacionPersistenciaServicio.marcarTodasLeidas(idUsuario);
        return ResponseEntity.ok().build();
    }
}