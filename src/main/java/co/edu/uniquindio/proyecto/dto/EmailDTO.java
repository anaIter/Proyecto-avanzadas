package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO {

    @NotBlank(message = "El destinatario es obligatorio")
    @Email(message = "Debe ser un correo valido ")
    private String destinatario;

    @NotBlank(message = "El asunto es obligatorio")
    private String asunto;

    @NotBlank(message ="El contenido del correo es obligatorio")
    private String cuerpo;

}
