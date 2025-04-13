package co.edu.uniquindio.proyecto.mappers;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.entidad.Comentario;
import org.bson.types.ObjectId;

public class ComentarioMapper {

    public static ComentarioDTO toDTO(Comentario comentario) {
        if (comentario == null) return null;

        ComentarioDTO dto = new ComentarioDTO();
        dto.setIdReporte(comentario.getId().toHexString());
        dto.setContenido(comentario.getContenido());
        dto.setIdUsuario(comentario.getIdUsuario().toHexString());
        return dto;
    }

    public static Comentario toEntity(ComentarioDTO dto) {
        if (dto == null) return null;

        return Comentario.builder()
                .id(dto.getIdReporte() != null ? new ObjectId(dto.getIdReporte()) : null)
                .contenido(dto.getContenido())
                .idUsuario(dto.getIdUsuario() != null ? new ObjectId(dto.getIdUsuario()) : null)
                .build();
    }
}
