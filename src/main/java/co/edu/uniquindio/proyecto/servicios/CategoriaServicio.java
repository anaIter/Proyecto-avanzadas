package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;

import java.util.List;

public interface CategoriaServicio {

    MensajeDTO<String> crearCategoria(CategoriaDTO dto);

    MensajeDTO<String> actualizarCategoria(CategoriaDTO dto);

    MensajeDTO<String> eliminarCategoria(String id);

    List<CategoriaDTO> listarCategorias();
}

