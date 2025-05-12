package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "comentarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comentario {
    @Id
    private ObjectId id;
    private String contenido;
    private LocalDateTime fecha;
    private ObjectId idUsuario;
    private ObjectId idReporte;
}

