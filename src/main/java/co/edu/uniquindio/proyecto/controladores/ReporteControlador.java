package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.CambiarEstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.servicios.impl.ReporteServicioImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteControlador {

    private final ReporteServicioImpl reporteServicio;

    @PostMapping
    @Operation(summary = "Crear un nuevo reporte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el reporte"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor al crear el reporte")
    })
    public ResponseEntity<MensajeDTO<String>> crearReporte(@RequestBody CrearReporteDTO reporte) {
        try {
            MensajeDTO<String> respuesta = reporteServicio.crearReporte(reporte);
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new MensajeDTO<>(true, "Error al crear el reporte: " + e.getMessage()));
        }
    }


    // Obtener todos los reportes
    @GetMapping
    @Operation(summary = "Obtener todos los reportes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reportes obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public ResponseEntity<List<Reporte>> obtenerTodosLosReportes() {
        List<Reporte> reportes = reporteServicio.obtenerTodosLosReportes();
        return ResponseEntity.ok(reportes);
    }

    // Obtener un reporte por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un reporte por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reporte encontrado"),
            @ApiResponse(responseCode = "404", description = "Reporte no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    public ResponseEntity<?> obtenerReportePorId(@PathVariable String id) {
        return reporteServicio.obtenerReportePorId(id);
    }

    // Obtener todos los reportes de un usuario por su ID

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Reporte>> obtenerReportesPorUsuario(@PathVariable String idUsuario) {
        List<Reporte> reportes = reporteServicio.obtenerReportesPorUsuario(idUsuario);
        return ResponseEntity.ok(reportes);
    }

    // Eliminar un reporte por su ID
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



}
