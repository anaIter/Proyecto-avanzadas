package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ReporteServicio {
    MensajeDTO<String> crearReporte(CrearReporteDTO dto);

    MensajeDTO<String> editarReporte(EditarReporteDTO dto) throws Exception;

    MensajeDTO<String> marcarComoImportante(MarcarImportanteDTO dto) throws Exception;

    List<Reporte> obtenerTodosLosReportes();

    Reporte obtenerReportePorId(String idReporte);

    List<Reporte> obtenerReportesPorUsuario(String idUsuario);

    MensajeDTO<String> eliminarReporte(String id);

    MensajeDTO<String> cambiarEstadoReporte(CambiarEstadoReporteDTO dto);

    MensajeDTO<String> agregarImagenAReporte(String url, String id);
}
