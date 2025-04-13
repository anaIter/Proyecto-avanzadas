package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.EmailServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notificaciones")
public class NotificacionControlador {
    private final EmailServicio emailServicio;

    @PostMapping("/correo")
    public ResponseEntity<MensajeDTO<String>> enviarCorreo(@Valid @RequestBody Map<String, String> datosCorreo) {
        try {
            String destinatario = datosCorreo.get("destinatario");
            String asunto = datosCorreo.get("asunto");
            String mensaje = datosCorreo.get("mensaje");

            EmailDTO emailDTO = new EmailDTO(destinatario, asunto, mensaje);
            emailServicio.enviarCorreo(emailDTO);

            return ResponseEntity.ok(new MensajeDTO<>(false, "Notificación enviada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, "Error al enviar notificación"));
        }
    }
}
