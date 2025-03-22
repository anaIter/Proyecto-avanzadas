package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private String nombre;
    private String email;
    private String contrasena; // solo al registrar
    private String telefono;
    private String direccion;

}
