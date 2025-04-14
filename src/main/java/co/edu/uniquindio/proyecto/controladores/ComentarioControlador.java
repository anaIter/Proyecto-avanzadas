package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Crear comentario", description = "Permite agregar un comentario a un reporte.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos al crear el comentario")
    })
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody ComentarioDTO comentarioDTO) {
        return ResponseEntity.ok(comentarioServicio.crearComentario(comentarioDTO));
    }

    @GetMapping("/{idReporte}")
    @Operation(summary = "Obtener comentarios por reporte", description = "Devuelve todos los comentarios asociados a un reporte específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentarios obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado o sin comentarios")
    })
    public ResponseEntity<List<ComentarioRespuestaDTO>> obtenerComentariosPorReporte(@PathVariable String idReporte) {
        return ResponseEntity.ok(comentarioServicio.obtenerComentariosPorReporte(idReporte));
    }
}
