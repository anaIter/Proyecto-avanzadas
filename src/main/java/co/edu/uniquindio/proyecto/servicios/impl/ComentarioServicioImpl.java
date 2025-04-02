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
    public MensajeDTO<String> crearComentario(ComentarioDTO comentarioDTO) {
        Comentario comentario = new Comentario();
        comentario.setIdReporte(new ObjectId(comentarioDTO.getIdReporte()));
        comentario.setIdUsuario(new ObjectId(comentarioDTO.getIdUsuario()));
        comentario.setContenido(comentarioDTO.getContenido());
        comentario.setFechaCreacion(LocalDateTime.now());

        comentarioRepositorio.save(comentario);
        return new MensajeDTO<>(false, "Comentario agregado exitosamente");
    }

    @Override
    public List<ComentarioRespuestaDTO> obtenerComentariosPorReporte(String idReporte) {
        List<Comentario> comentarios = comentarioRepositorio.findByIdReporte(new ObjectId(idReporte));

        return comentarios.stream()
                .map(comentario -> new ComentarioRespuestaDTO(
                        comentario.getId().toString(),
                        comentario.getIdReporte().toString(),
                        comentario.getIdUsuario().toString(),
                        comentario.getContenido(),
                        comentario.getFechaCreacion().toString()
                ))
                .collect(Collectors.toList());
    }
}

