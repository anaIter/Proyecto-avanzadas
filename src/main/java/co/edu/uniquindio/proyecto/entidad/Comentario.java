package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comentarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comentario {

    @Id
    private ObjectId id;

    private ObjectId idReporte;

    private ObjectId idUsuario;

    private String contenido;

    private LocalDateTime fechaCreacion;
}

