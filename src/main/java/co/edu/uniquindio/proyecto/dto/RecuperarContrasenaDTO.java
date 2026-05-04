package co.edu.uniquindio.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecuperarContrasenaDTO {

    @NotBlank(message = "El email es obligatorio")
    private String email;

    @NotBlank(message = "El código de verificación es obligatorio")
    private String codigoVerificacion;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    private String nuevaContrasena;
}












