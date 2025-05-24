package co.edu.uniquindio.proyecto.servicios;



import co.edu.uniquindio.proyecto.dto.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioServicio {
    void crear(CrearUsuarioDTO dto) throws Exception;
    void cambiarContrasena(String email, CambiarContrasenaDTO dto) throws Exception;
    void editar(String id,EditarUsuarioDTO dto) throws Exception;
    void eliminar(String id) throws Exception;
    UsuarioDTO obtener(String email) throws Exception;
    List<UsuarioDTO> listarTodos(String nombre, String ciudad);
    void activarCuenta(ActivarCuentaDTO dto) throws Exception;
}

