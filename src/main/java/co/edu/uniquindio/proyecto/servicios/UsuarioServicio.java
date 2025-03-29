package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;


public interface UsuarioServicio {
    void crear(CrearUsuarioDTO dto) throws Exception;
    void editar(EditarUsuarioDTO dto) throws Exception;
    void eliminar(String id) throws Exception;
    UsuarioDTO obtener(String id) throws Exception;
    List<UsuarioDTO> listarTodos(String nombre, String ciudad);
}

