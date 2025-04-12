package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.servicios.impl.ReporteServicioImpl;
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
    public ResponseEntity<List<Reporte>> obtenerTodosLosReportes() {
        List<Reporte> reportes = reporteServicio.obtenerTodosLosReportes();
        return ResponseEntity.ok(reportes);
    }

    // Obtener un reporte por su ID
    @GetMapping("/{id}")
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
    public MensajeDTO<String> eliminarReporte(@PathVariable String id) {
        return reporteServicio.eliminarReporte(id);
    }
}
