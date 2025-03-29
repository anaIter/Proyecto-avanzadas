package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

private final ReporteRepositorio reporteRepositorio;


public MensajeDTO<String> crearReporte(Reporte dto) {
    Reporte reporte = new Reporte();
    reporte.setTitulo(dto.getTitulo());
    reporte.setDescripcion(dto.getDescripcion());
    reporte.getIdUsuario();
    reporte.setFechaCreacion(dto.getFechaCreacion());

    reporteRepositorio.save(reporte);
    return new MensajeDTO<>(false, "Reporte creado exitosamente");
}

public List<Reporte> obtenerTodosLosReportes() {
    return reporteRepositorio.findAll();
}

public ResponseEntity<?> obtenerReportePorId(String idReporte) {
    try {
        ObjectId objectId = new ObjectId(idReporte);
        Optional<Reporte> reporte = reporteRepositorio.findById(objectId);

        if (reporte.isPresent()) {
            return ResponseEntity.ok(reporte.get());
        } else {
            return ResponseEntity.badRequest()
                    .body(new MensajeDTO<>(true, "Reporte no encontrado"));
        }

    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest()
                .body(new MensajeDTO<>(true, "ID de reporte inválido"));
    }
}

public List<Reporte> obtenerReportesPorUsuario(String idUsuario) {
    try {
        return reporteRepositorio.findByIdUsuario(new ObjectId(idUsuario));
    } catch (IllegalArgumentException e) {
        return List.of(); // Retorna una lista vacía si el ID es inválido
    }
}

public MensajeDTO<String> eliminarReporte(String id) {
    if (reporteRepositorio.existsById(new ObjectId(id))) {
        reporteRepositorio.deleteById(new ObjectId(id));
        return new MensajeDTO<>(false, "Reporte eliminado correctamente");
    } else {
        return new MensajeDTO<>(true, "El reporte no existe");
    }
}
}

