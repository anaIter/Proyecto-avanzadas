package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "notificaciones")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificacionUsuario {
    @Id
    private ObjectId id;
    private ObjectId idUsuario;     // destinatario
    private String titulo;
    private String mensaje;
    private String tipo;            // "comentario" | "estado"
    private boolean leido;
    private LocalDateTime fecha;
}