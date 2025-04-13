package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ReporteDTO {
    private String tipo;
    private String descripcion;
    private String ubicacion;
}
