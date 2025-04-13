package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class NotificacionDTO {
    @NotBlank
    String titulo;
    @NotBlank
    String descripcion;
    @NotBlank
    String topic;
}
