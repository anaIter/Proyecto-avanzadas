package co.edu.uniquindio.proyecto.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComentarioRespuestaDTO {
    private String id;
    private String contenido;
    private LocalDateTime fecha;
    private String idUsuario;
}
