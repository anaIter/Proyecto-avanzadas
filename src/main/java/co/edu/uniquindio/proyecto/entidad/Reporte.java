package co.edu.uniquindio.proyecto.entidad;



import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Reporte {
    @Id
    private ObjectId id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private Ubicacion ubicacion;
    private String estado; // Ej: "Pendiente", "Resuelto"
    private LocalDateTime fechaCreacion;
    private List<String> imagenes;
    private boolean eliminado;
    private ObjectId idUsuario; // Referencia al usuario creador
    private boolean importante;

}