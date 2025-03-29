package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EditarReporteDTO {
    private String titulo;
    private String descripcion;
    private String categoria;
}
