package co.edu.uniquindio.proyecto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EditarReporteDTO {
    private String id;             // ID del reporte a editar
    private String titulo;
    private String descripcion;
    private String categoria;
    private String estado;         // Puede ser PENDIENTE, RESUELTO, etc.
}
