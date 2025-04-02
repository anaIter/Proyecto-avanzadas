package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ComentarioRespuestaDTO;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioServicio.crearComentario(comentarioDTO));
    }

    @GetMapping("/{idReporte}")
    public ResponseEntity<List<ComentarioRespuestaDTO>> obtenerComentariosPorReporte(@PathVariable String idReporte) {
        return ResponseEntity.ok(comentarioServicio.obtenerComentariosPorReporte(idReporte));
    }
}
