package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidad.HistorialEstadoReporte;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.mappers.UbicacionMapper;
import co.edu.uniquindio.proyecto.repositorios.HistorialEstadoReporteRepositorio;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
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
    private final UsuarioRepositorio usuarioRepositorio;
    private final NotificacionPersistenciaServicio notificacionPersistenciaServicio; // ✅

    public MensajeDTO<String> crearReporte(CrearReporteDTO dto, String email) {
        Usuario usuario = usuarioRepositorio.findByEmail(email).get();
        ObjectId idUsuario = usuario.getId();

        Reporte reporte = new Reporte();
        reporte.setTitulo(dto.getTitulo());
        reporte.setDescripcion(dto.getDescripcion());
        reporte.setUbicacion(UbicacionMapper.dtoToEntidad(dto.getUbicacion()));
        reporte.setEstado(dto.getEstadoReporte().name());
        reporte.setCategoria(dto.getCategoria());
        reporte.setFechaCreacion(LocalDateTime.now());
        reporte.setImagenes(dto.getImagenes());
        reporte.setIdUsuario(idUsuario);
        reporte.setSeguidores(new ArrayList<>());

        Reporte guardado = reporteRepositorio.save(reporte);
        return new MensajeDTO<>(false, guardado.getId().toString());
    }

    @Override
    public MensajeDTO<String> editarReporte(EditarReporteDTO dto) throws Exception {
        Optional<Reporte> optional = reporteRepositorio.findById(new ObjectId(dto.getId()));
        if (optional.isEmpty()) throw new Exception("No se encontró el reporte con ID: " + dto.getId());
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
        if (optional.isEmpty()) throw new Exception("No se encontró el reporte");
        Reporte reporte = optional.get();
        reporte.setImportante(dto.isImportante());
        reporteRepositorio.save(reporte);
        return new MensajeDTO<>(false, "Importancia del reporte actualizada");
    }

    public List<Reporte> obtenerTodosLosReportes() {
        return reporteRepositorio.findAll();
    }

    public Reporte obtenerReportePorId(String idReporte) {
        try {
            return reporteRepositorio.findById(new ObjectId(idReporte)).orElse(null);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public List<Reporte> obtenerReportesPorUsuario(String idUsuario) {
        try {
            return reporteRepositorio.findByIdUsuario(new ObjectId(idUsuario));
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    public MensajeDTO<String> eliminarReporte(String id) {
        Optional<Reporte> optionalReporte = reporteRepositorio.findById(new ObjectId(id));
        if (optionalReporte.isPresent()) {
            Reporte reporte = optionalReporte.get();
            reporte.setEliminado(true);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Reporte marcado como eliminado correctamente");
        }
        return new MensajeDTO<>(true, "El reporte no existe");
    }

    @Override
    public MensajeDTO<String> cambiarEstadoReporte(CambiarEstadoReporteDTO dto) {
        Optional<Reporte> opcional = reporteRepositorio.findById(new ObjectId(dto.getIdReporte()));
        if (opcional.isPresent()) {
            Reporte reporte = opcional.get();
            String estadoAnterior = reporte.getEstado();
            String nuevoEstado = dto.getNuevoEstado();
            reporte.setEstado(nuevoEstado);
            if (dto.getNivelImpacto() != null && !dto.getNivelImpacto().isEmpty()) {
                reporte.setNivelImpacto(dto.getNivelImpacto());
            }
            reporteRepositorio.save(reporte);

            HistorialEstadoReporte historial = HistorialEstadoReporte.builder()
                    .idReporte(reporte.getId())
                    .estadoAnterior(estadoAnterior)
                    .estadoNuevo(nuevoEstado)
                    .fechaCambio(LocalDateTime.now())
                    .observacion("Cambio realizado automáticamente")
                    .build();
            historialEstadoRepo.save(historial);

            // ✅ Notificar a cada seguidor — guardar en BD y enviar por WebSocket
            if (reporte.getSeguidores() != null) {
                for (ObjectId seguidorId : reporte.getSeguidores()) {
                    notificacionPersistenciaServicio.crearYEnviar(
                            seguidorId.toHexString(),
                            new NotificacionDTO(
                                    "Reporte actualizado: " + reporte.getTitulo(),
                                    "El estado cambió de " + estadoAnterior + " a " + nuevoEstado,
                                    "estado"
                            )
                    );
                }
            }

            return new MensajeDTO<>(false, "Estado del reporte actualizado y registrado en historial.");
        }
        return new MensajeDTO<>(true, "No se encontró el reporte con el ID especificado");
    }

    @Override
    public MensajeDTO<String> agregarImagenAReporte(String url, String id) {
        Optional<Reporte> optionalReporte = reporteRepositorio.findById(new ObjectId(id));
        if (optionalReporte.isPresent()) {
            Reporte reporte = optionalReporte.get();
            reporte.getImagenes().add(url);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Agregada imagen correctamente");
        }
        return new MensajeDTO<>(true, "El reporte no existe");
    }

    @Override
    public MensajeDTO<String> seguirReporte(String idReporte, String idUsuario) {
        Optional<Reporte> opcional = reporteRepositorio.findById(new ObjectId(idReporte));
        if (opcional.isEmpty()) {
            return new MensajeDTO<>(true, "No se encontró el reporte");
        }
        Reporte reporte = opcional.get();
        ObjectId userObjectId = new ObjectId(idUsuario);

        if (reporte.getSeguidores() == null) {
            reporte.setSeguidores(new ArrayList<>());
        }

        if (reporte.getSeguidores().contains(userObjectId)) {
            reporte.getSeguidores().remove(userObjectId);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Dejaste de seguir el reporte");
        } else {
            reporte.getSeguidores().add(userObjectId);
            reporteRepositorio.save(reporte);
            return new MensajeDTO<>(false, "Ahora sigues este reporte");
        }
    }
}