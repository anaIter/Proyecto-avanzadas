package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.Enum.EstadoReporte;
import co.edu.uniquindio.proyecto.Enum.EstadoUsuario;
import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrearReporteDTO {
    private String titulo;
    private String descripcion;
    private UbicacionDTO ubicacion;
    private EstadoReporte estadoReporte;
    private List<String> imagenes;
    private String categoria;
    private LocalDateTime fechaCreacion;
}
