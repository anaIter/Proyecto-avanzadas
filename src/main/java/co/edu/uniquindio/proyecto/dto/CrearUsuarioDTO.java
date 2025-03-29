package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.Enum.Ciudad;
import co.edu.uniquindio.proyecto.Enum.EstadoUsuario;
import co.edu.uniquindio.proyecto.Enum.Rol;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrearUsuarioDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasena;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 10, max = 10, message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;

    private Ciudad ciudad;
    private Rol rol;
    private EstadoUsuario estado;
}
