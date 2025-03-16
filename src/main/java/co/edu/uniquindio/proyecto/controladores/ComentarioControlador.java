package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reportes")
public class ComentarioControlador {

    @PostMapping("/{id}/comentarios")
    public ResponseEntity<MensajeDTO<String>> agregarComentario(@PathVariable String id,
                                                                @Valid @RequestBody Map<String, String> comentario) {
        // TODO: Agregar comentario al reporte
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Comentario agregado correctamente"));
    }
}