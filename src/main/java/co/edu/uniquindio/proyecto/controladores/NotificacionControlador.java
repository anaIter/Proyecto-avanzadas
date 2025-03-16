package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {

    @PostMapping("/correo")
    public ResponseEntity<MensajeDTO<String>> enviarCorreo(@Valid @RequestBody Map<String, String> datosCorreo) {
        // TODO: Enviar notificación por correo electrónico
        return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación enviada correctamente"));
    }
}
