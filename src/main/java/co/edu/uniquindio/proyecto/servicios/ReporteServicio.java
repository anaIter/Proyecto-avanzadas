package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.CambiarEstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReporteServicio {
    MensajeDTO<String> crearReporte(CrearReporteDTO dto);

    List<Reporte> obtenerTodosLosReportes();

    Reporte obtenerReportePorId(String idReporte);

    List<Reporte> obtenerReportesPorUsuario(String idUsuario);

    MensajeDTO<String> eliminarReporte(String id);

    MensajeDTO<String> cambiarEstadoReporte(CambiarEstadoReporteDTO dto);

    MensajeDTO<String> agregarImagenAReporte(String url, String id);
}
