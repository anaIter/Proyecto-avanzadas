package co.edu.uniquindio.proyecto.mappers;

import co.edu.uniquindio.proyecto.dto.ReporteDTO;
import co.edu.uniquindio.proyecto.dto.ReporteSalidaDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ReporteMapper {

    public static ReporteDTO toDTO(Reporte reporte) {
        if (reporte == null) return null;
        ReporteDTO dto = new ReporteDTO();
        dto.setTipo(reporte.getCategoria());
        dto.setDescripcion(reporte.getDescripcion());
        dto.setUbicacion(reporte.getUbicacion() != null ? reporte.getUbicacion().toString() : null);
        return dto;
    }

    public static ReporteSalidaDTO convertirADTO(Reporte reporte, String nombreUsuario) {
        return ReporteSalidaDTO.builder()
                .id(reporte.getId().toString())
                .titulo(reporte.getTitulo())
                .descripcion(reporte.getDescripcion())
                .categoria(reporte.getCategoria())
                .ubicacion(reporte.getUbicacion())
                .estado(reporte.getEstado())
                .fechaCreacion(reporte.getFechaCreacion())
                .imagenes(reporte.getImagenes())
                .eliminado(reporte.isEliminado())
                .idUsuario(reporte.getIdUsuario().toString())
                .nombreUsuario(nombreUsuario)
                .importante(reporte.isImportante())
                // FIX: mapear seguidores — contar la lista si no es null
                .seguidores(reporte.getSeguidores() != null ? reporte.getSeguidores().size() : 0)
                .build();
    }

    public static ReporteSalidaDTO convertirADTO(Reporte reporte) {
        return convertirADTO(reporte, "Usuario desconocido");
    }

    public static List<ReporteSalidaDTO> convertirListaADTO(List<Reporte> reportes) {
        return reportes.stream()
                .map(ReporteMapper::convertirADTO)
                .collect(Collectors.toList());
    }
}