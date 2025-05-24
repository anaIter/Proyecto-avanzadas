package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "historial_estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialEstadoReporte {

    @Id
    private ObjectId id;

    private ObjectId idReporte;

    private String estadoAnterior;

    private String estadoNuevo;

    private LocalDateTime fechaCambio;

    private String observacion;
}
