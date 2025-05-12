package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ComentarioRespuestaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;

import java.util.List;

public interface ComentarioServicio {
    MensajeDTO<String> crearComentario(ComentarioDTO dto);
    List<ComentarioRespuestaDTO> obtenerComentariosPorReporte(String idReporte);
}

