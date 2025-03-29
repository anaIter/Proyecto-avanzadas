package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrearReporteDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
    private ObjectId idusuario;
    private LocalDateTime fechaCreacion;
}
