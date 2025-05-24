package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidad.HistorialEstadoReporte;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.mappers.UbicacionMapper;
import co.edu.uniquindio.proyecto.repositorios.HistorialEstadoReporteRepositorio;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReporteServicioImpl implements ReporteServicio {

private final ReporteRepositorio reporteRepositorio;
private final HistorialEstadoReporteRepositorio historialEstadoRepo;


    public MensajeDTO<String>crearReporte(CrearReporteDTO dto, String idUsuario) {
        Reporte reporte = new Reporte();
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setUbicacion(UbicacionMapper.dtoToEntidad(dto.getUbicacion())); // ✅
        reporte.setEstado(dto.getEstadoReporte().name()); // ✅ o usa directamente el enum si aplica
        reporte.setCategoria(dto.getCategoria());
        reporte.setFechaCreacion(dto.getFechaCreacion() != null ? dto.getFechaCreacion() : LocalDateTime.now());
        reporte.setImagenes(dto.getImagenes());
        reporte.setIdUsuario(new ObjectId(idUsuario)); // ✅

        reporteRepositorio.save(reporte);
        return new MensajeDTO<>(false, "Reporte creado exitosamente");
    }

    @Override
    public MensajeDTO<String> editarReporte(EditarReporteDTO dto) throws Exception {
        Optional<Reporte> optional = reporteRepositorio.findById(new ObjectId(dto.getId()));
        if (optional.isEmpty()) {
            throw new Exception("No se encontró el reporte con ID: " + dto.getId());
        }

        Reporte reporte = optional.get();
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setCategoria(dto.getCategoria());
        reporte.setEstado(dto.getEstado());

        reporteRepositorio.save(reporte);
        return new MensajeDTO<>(false, "Reporte actualizado correctamente");
    }

    @Override
    public MensajeDTO<String> marcarComoImportante(MarcarImportanteDTO dto) throws Exception {
        Optional<Reporte> optional = reporteRepositorio.findById(new ObjectId(dto.getIdReporte()));
        if (optional.isEmpty()) {
            throw new Exception("No se encontró el reporte");
        }

        Reporte reporte = optional.get();
        reporte.setImportante(dto.isImportante());  // Asegúrate que el campo exista en la entidad

        reporteRepositorio.save(reporte);
        return new MensajeDTO<>(false, "Importancia del reporte actualizada");
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
            String estadoAnterior = reporte.getEstado();
            String nuevoEstado = dto.getNuevoEstado();

            // Cambiar el estado
            reporte.setEstado(nuevoEstado);
            reporteRepositorio.save(reporte);

            // Guardar en historial
            HistorialEstadoReporte historial = HistorialEstadoReporte.builder()
                    .idReporte(reporte.getId())
                    .estadoAnterior(estadoAnterior)
                    .estadoNuevo(nuevoEstado)
                    .fechaCambio(LocalDateTime.now())
                    .observacion("Cambio realizado automáticamente")
                    .build();

            historialEstadoRepo.save(historial);

            return new MensajeDTO<>(false, "Estado del reporte actualizado y registrado en historial.");
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

