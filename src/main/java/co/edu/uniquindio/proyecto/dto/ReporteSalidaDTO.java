package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidad.Ubicacion;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReporteSalidaDTO {
    private String id;
    private String titulo;
    private String descripcion;
    private String categoria;
    private Ubicacion ubicacion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private List<String> imagenes;
    private boolean eliminado;
    private String idUsuario;
    private boolean importante;
}
