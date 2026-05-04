package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidad.HistorialEstadoReporte;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.mappers.ReporteMapper;
import co.edu.uniquindio.proyecto.repositorios.HistorialEstadoReporteRepositorio;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.servicios.CloudinaryServicio;
import co.edu.uniquindio.proyecto.servicios.impl.ReporteServicioImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteControlador {

    private final ReporteServicioImpl reporteServicio;
    private final CloudinaryServicio cloudinaryService;
    private final UsuarioRepositorio usuarioRepositorio;
    private final HistorialEstadoReporteRepositorio historialEstadoRepo;

    @PostMapping("/{email}")
    @Operation(summary = "Crear un nuevo reporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el reporte"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el reporte")
    })
    public ResponseEntity<MensajeDTO<String>> crearReporte(@PathVariable String email,
                                                           @RequestBody CrearReporteDTO reporte) {
        try {
            MensajeDTO<String> respuesta = reporteServicio.crearReporte(reporte, email);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MensajeDTO<>(true, "Error al crear el reporte: " + e.getMessage()));
        }
    }

    @PutMapping("/editar")
    @Operation(summary = "Editar un reporte existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MensajeDTO<String>> editarReporte(@RequestBody EditarReporteDTO dto) {
        try {
            MensajeDTO<String> respuesta = reporteServicio.editarReporte(dto);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeDTO<>(true, "Error al editar el reporte: " + e.getMessage()));
        }
    }

    @PutMapping("/marcar-importante")
    @Operation(summary = "Marcar un reporte como importante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado de importancia actualizado"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MensajeDTO<String>> marcarComoImportante(@RequestBody MarcarImportanteDTO dto) {
        try {
            MensajeDTO<String> respuesta = reporteServicio.marcarComoImportante(dto);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeDTO<>(true, "Error al actualizar importancia: " + e.getMessage()));
        }
    }

    /**
     * FIX: se movió /usuario/{idUsuario} ANTES de /{id} para evitar
     * conflicto de rutas donde Spring interpretaba "usuario" como un ID.
     */
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reporte>> obtenerReportesPorUsuario(@PathVariable String idUsuario) {
        List<Reporte> reportes = reporteServicio.obtenerReportesPorUsuario(idUsuario);
        return ResponseEntity.ok(reportes);
    }


    @GetMapping("/pendientes")
    @Operation(summary = "Obtener reportes pendientes para admin")
    public ResponseEntity<List<ReporteSalidaDTO>> obtenerReportesPendientes() {
        List<Reporte> reportes = reporteServicio.obtenerTodosLosReportes();

        List<ReporteSalidaDTO> reportesDTO = reportes.stream()
                .filter(r -> "PENDIENTE".equalsIgnoreCase(r.getEstado()) && !r.isEliminado())
                .map(r -> {
                    String nombre = usuarioRepositorio.findById(r.getIdUsuario().toString())
                            .map(u -> u.getNombre())
                            .orElse("Usuario desconocido");
                    return ReporteMapper.convertirADTO(r, nombre);
                })
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(reportesDTO);
    }



    @GetMapping
    @Operation(summary = "Obtener todos los reportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<ReporteSalidaDTO>> obtenerTodosLosReportes() {
        List<Reporte> reportes = reporteServicio.obtenerTodosLosReportes();

        List<ReporteSalidaDTO> reportesDTO = reportes.stream()
                .filter(r -> "VERIFICADO".equalsIgnoreCase(r.getEstado()) && !r.isEliminado())
                .map(r -> {
                    String nombre = usuarioRepositorio.findById(r.getIdUsuario().toString())
                            .map(u -> u.getNombre())
                            .orElse("Usuario desconocido");
                    return ReporteMapper.convertirADTO(r, nombre);
                })
                .collect(java.util.stream.Collectors.toList());

        return ResponseEntity.ok(reportesDTO);
    }


    public Reporte obtenerReportePorId(@PathVariable String id) {
        return reporteServicio.obtenerReportePorId(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un reporte por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    public MensajeDTO<String> eliminarReporte(@PathVariable String id) {
        return reporteServicio.eliminarReporte(id);
    }

    @PutMapping("/cambiar-estado")
    @Operation(summary = "Cambiar el estado de un reporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado del reporte actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para cambiar el estado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<MensajeDTO<String>> cambiarEstado(@RequestBody CambiarEstadoReporteDTO dto) {
        MensajeDTO<String> respuesta = reporteServicio.cambiarEstadoReporte(dto);
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/{idReporte}/seguir")
    @Operation(summary = "Seguir o dejar de seguir un reporte")
    public ResponseEntity<MensajeDTO<String>> seguirReporte(
            @PathVariable String idReporte,
            @RequestParam String idUsuario) {
        try {
            MensajeDTO<String> respuesta = reporteServicio.seguirReporte(idReporte, idUsuario);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MensajeDTO<>(true, "Error al seguir el reporte: " + e.getMessage()));
        }
    }

    @GetMapping("/{idReporte}/historial")
    @Operation(summary = "Obtener historial de estados de un reporte")
    public ResponseEntity<List<HistorialEstadoReporte>> obtenerHistorial(@PathVariable String idReporte) {
        try {
            org.bson.types.ObjectId objectId;
            // Verifica si es un ObjectId válido antes de convertir
            if (org.bson.types.ObjectId.isValid(idReporte)) {
                objectId = new org.bson.types.ObjectId(idReporte);
            } else {
                return ResponseEntity.badRequest().build();
            }
            List<HistorialEstadoReporte> historial = historialEstadoRepo
                    .findByIdReporteOrderByFechaCambioDesc(objectId);
            return ResponseEntity.ok(historial);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
