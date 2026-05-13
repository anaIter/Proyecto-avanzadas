package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificacionRespuestaDTO {
    private String id;
    private String titulo;
    private String mensaje;
    private String tipo;
    private boolean leido;
    private LocalDateTime fecha;
}