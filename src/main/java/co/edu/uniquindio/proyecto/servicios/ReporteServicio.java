package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReporteServicio {
    MensajeDTO<String> crearReporte(Reporte dto);

    List<Reporte> obtenerTodosLosReportes();

    ResponseEntity<?> obtenerReportePorId(String idReporte);

    List<Reporte> obtenerReportesPorUsuario(String idUsuario);

    MensajeDTO<String> eliminarReporte(String id);
}
