package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import java.util.List;

public class UsuarioServicioImpl implements UsuarioServicio {


    @Override
    public void crear(CrearUsuarioDTO dto) throws Exception {

    }

    @Override
    public void editar(EditarUsuarioDTO dto) throws Exception {

    }

    @Override
    public void eliminar(String id) throws Exception {

    }

    @Override
    public UsuarioDTO obtener(String id) throws Exception {
        return null;
    }

    @Override
    public List<UsuarioDTO> listarTodos(String nombre, String ciudad) {
        return List.of();
    }
}
