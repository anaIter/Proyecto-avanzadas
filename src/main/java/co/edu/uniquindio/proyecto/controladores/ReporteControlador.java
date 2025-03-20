package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
@SecurityRequirement(name = "BearerAuth")
public class ReporteControlador {

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> editarReporte(@PathVariable String id,
                                                            @Valid @RequestBody Map<String, Object> datosReporte) {
        // TODO: Actualizar los datos del reporte
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte actualizado correctamente"));
    }

    @PutMapping("/{id}/priorizar")
    public ResponseEntity<MensajeDTO<String>> priorizarReporte(@PathVariable String id) {
        // TODO: Marcar el reporte como prioritario
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte priorizado correctamente"));
    }

    @PutMapping("/{id}/moderar")
    public ResponseEntity<MensajeDTO<String>> moderarReporte(@PathVariable String id,
                                                             @Valid @RequestBody Map<String, String> estado) {
        // TODO: Cambiar estado a moderado
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte moderado correctamente"));
    }

    @PutMapping("/{id}/rechazar")
    public ResponseEntity<MensajeDTO<String>> rechazarReporte(@PathVariable String id,
                                                              @Valid @RequestBody Map<String, String> motivo) {
        // TODO: Rechazar el reporte con razón específica
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte rechazado correctamente"));
    }

    @PutMapping("/{id}/resuelto")
    public ResponseEntity<MensajeDTO<String>> marcarComoResuelto(@PathVariable String id) {
        // TODO: Cambiar estado a resuelto
        return ResponseEntity.ok(new MensajeDTO<>(false, "Reporte marcado como resuelto"));
    }
}
