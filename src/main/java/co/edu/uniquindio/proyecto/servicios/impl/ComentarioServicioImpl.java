package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ComentarioDTO;
import co.edu.uniquindio.proyecto.dto.ComentarioRespuestaDTO;
import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.NotificacionDTO;
import co.edu.uniquindio.proyecto.entidad.Comentario;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepositorio;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.servicios.ComentarioServicio;
import co.edu.uniquindio.proyecto.servicios.EmailServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepositorio comentarioRepositorio;
    private final ReporteRepositorio reporteRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final EmailServicio emailServicio;
    private final NotificacionPersistenciaServicio notificacionPersistenciaServicio; // ✅

    @Override
    public MensajeDTO<String> crearComentario(ComentarioDTO dto) {
        Comentario comentario = Comentario.builder()
                .contenido(dto.getContenido())
                .fecha(LocalDateTime.now())
                .idUsuario(new ObjectId(dto.getIdUsuario()))
                .idReporte(new ObjectId(dto.getIdReporte()))
                .build();

        comentarioRepositorio.save(comentario);

        reporteRepositorio.findById(new ObjectId(dto.getIdReporte())).ifPresent(reporte -> {
            ObjectId idAutorReporte = reporte.getIdUsuario();

            usuarioRepositorio.findById(idAutorReporte).ifPresent(autor -> {
                // ✅ Enviar email
                EmailDTO email = EmailDTO.builder()
                        .destinatario(autor.getEmail())
                        .asunto("Nuevo comentario en tu reporte")
                        .cuerpo("Hola " + autor.getNombre() + ",\n\n" +
                                "Alguien comentó en tu reporte:\n\n" +
                                "\"" + dto.getContenido() + "\"\n\n" +
                                "Gracias por confiar en nuestro sistema.")
                        .build();
                try {
                    emailServicio.enviarCorreo(email);
                } catch (Exception e) {
                    System.err.println("Error al enviar notificación de comentario:");
                    e.printStackTrace();
                }

                // ✅ Guardar en BD y enviar por WebSocket
                notificacionPersistenciaServicio.crearYEnviar(
                        idAutorReporte.toHexString(),
                        new NotificacionDTO(
                                "Nuevo comentario en tu reporte",
                                "Alguien comentó en: \"" + reporte.getTitulo() + "\"",
                                "comentario"
                        )
                );
            });
        });

        return new MensajeDTO<>(false, "Comentario creado correctamente");
    }

    @Override
    public List<ComentarioRespuestaDTO> obtenerComentariosPorReporte(String idReporte) {
        List<Comentario> comentarios = comentarioRepositorio.findByIdReporte(new ObjectId(idReporte));

        return comentarios.stream().map(c -> {
            ObjectId userId = c.getIdUsuario();

            String nombreUsuario = usuarioRepositorio
                    .findByObjectIdHex(c.getIdUsuario().toHexString())
                    .map(u -> u.getNombre())
                    .orElse("Desconocido");

            return ComentarioRespuestaDTO.builder()
                    .id(c.getId().toHexString())
                    .contenido(c.getContenido())
                    .fecha(c.getFecha())
                    .idUsuario(userId.toHexString())
                    .nombreUsuario(nombreUsuario)
                    .build();
        }).toList();
    }
}