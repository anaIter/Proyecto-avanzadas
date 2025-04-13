package co.edu.uniquindio.proyecto.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CambiarEstadoReporteDTO {

    @NotNull(message = "El id del reporte es obligatorio")
    private String idReporte;

    @NotBlank(message = "El nuevo estado es obligatorio")
    private String nuevoEstado;

}

