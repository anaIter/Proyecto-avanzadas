package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CambiarEstadoReporteDTO;
import co.edu.uniquindio.proyecto.dto.CrearReporteDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

private final ReporteRepositorio reporteRepositorio;


public MensajeDTO<String> crearReporte(CrearReporteDTO dto) {
    Reporte reporte = new Reporte();
    reporte.setTitulo(dto.getTitulo());
    reporte.setDescripcion(dto.getDescripcion());
    reporte.getIdUsuario();
    reporte.setFechaCreacion(dto.getFechaCreacion());
    reporte.setImagenes(new ArrayList<>());

    reporteRepositorio.save(reporte);
    return new MensajeDTO<>(false, "Reporte creado exitosamente");
}

public List<Reporte> obtenerTodosLosReportes() {
    return reporteRepositorio.findAll();
}

public Reporte obtenerReportePorId(String idReporte) {
    try {
        ObjectId objectId = new ObjectId(idReporte);
        Optional<Reporte> reporte = reporteRepositorio.findById(objectId);

        return reporte.orElse(null);

    } catch (IllegalArgumentException e) {
        return null;
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
        Optional<Reporte> optionalReporte = reporteRepositorio.findById(new ObjectId(id));

        if (optionalReporte.isPresent()) {
            Reporte reporte = optionalReporte.get();
            reporte.setEliminado(true);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Reporte marcado como eliminado correctamente");
        } else {
            return new MensajeDTO<>(true, "El reporte no existe");
        }
    }


    @Override
    public MensajeDTO<String> cambiarEstadoReporte(CambiarEstadoReporteDTO dto) {
        Optional<Reporte> opcional = reporteRepositorio.findById(new ObjectId(dto.getIdReporte()));

        if (opcional.isPresent()) {
            Reporte reporte = opcional.get();
            reporte.setEstado(dto.getNuevoEstado());
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Estado del reporte actualizado correctamente");
        } else {
            return new MensajeDTO<>(true, "No se encontró el reporte con el ID especificado");
        }
    }

    @Override
    public MensajeDTO<String> agregarImagenAReporte(String url, String id) {
        Optional<Reporte> optionalReporte = reporteRepositorio.findById(new ObjectId(id));

        if (optionalReporte.isPresent()) {
            Reporte reporte = optionalReporte.get();
            reporte.getImagenes().add(url);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Agregada imagen correctamente");
        } else {
            return new MensajeDTO<>(true, "El reporte no existe");
        }
    }

}

