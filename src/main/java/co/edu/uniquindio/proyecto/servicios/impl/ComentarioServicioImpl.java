package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ComentarioRespuestaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Comentario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepositorio;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepositorio comentarioRepositorio;

    @Override
    public MensajeDTO<String> crearComentario(ComentarioDTO dto) {
        Comentario comentario = Comentario.builder()
                .contenido(dto.getContenido())
                .fecha(LocalDateTime.now())
                .idUsuario(new ObjectId(dto.getIdUsuario()))
                .idReporte(new ObjectId(dto.getIdReporte()))
                .build();

        comentarioRepositorio.save(comentario);
        return new MensajeDTO<>(false, "Comentario creado correctamente");
    }

    @Override
    public List<ComentarioRespuestaDTO> obtenerComentariosPorReporte(String idReporte) {
        List<Comentario> comentarios = comentarioRepositorio.findByIdReporte(new ObjectId(idReporte));
        return comentarios.stream().map(c ->
                ComentarioRespuestaDTO.builder()
                        .id(c.getId().toHexString())
                        .contenido(c.getContenido())
                        .fecha(c.getFecha())
                        .idUsuario(c.getIdUsuario().toHexString())
                        .build()
        ).toList();
    }
}
