package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private String name;
    private String email;
    private String password; // solo al registrar
    private String phone;
    private String address;
}
