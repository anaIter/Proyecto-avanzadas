package co.edu.uniquindio.proyecto.servicios;



import co.edu.uniquindio.proyecto.config.JwtService;
import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {
    void crear(CrearUsuarioDTO dto) throws Exception;
    void editar(EditarUsuarioDTO dto) throws Exception;
    void eliminar(String id) throws Exception;
    UsuarioDTO obtener(String id) throws Exception;
    List<UsuarioDTO> listarTodos(String nombre, String ciudad);
}

