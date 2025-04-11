package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;

import co.edu.uniquindio.proyecto.entidad.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepositorio;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepositorio categoriaRepositorio;

    @Override
    public MensajeDTO<String> crearCategoria(CategoriaDTO dto) {
        Categoria categoria = Categoria.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();

        categoriaRepositorio.save(categoria);
        return new MensajeDTO<>(false, "Categoría creada correctamente");
    }

    @Override
    public MensajeDTO<String> actualizarCategoria(CategoriaDTO dto) {
        // Suponiendo que se reciba también un id para actualizar. Aquí es solo demostrativo.
        return new MensajeDTO<>(false, "Funcionalidad de actualización aún no implementada");
    }

    @Override
    public MensajeDTO<String> eliminarCategoria(String id) {
        ObjectId objectId = new ObjectId(id);
        categoriaRepositorio.deleteById(id);
        return new MensajeDTO<>(false, "Categoría eliminada correctamente");
    }

    @Override
    public List<CategoriaDTO> listarCategorias() {
        return categoriaRepositorio.findAll()
                .stream()
                .map(cat -> CategoriaDTO.builder()
                        .nombre(cat.getNombre())
                        .descripcion(cat.getDescripcion())
                        .build())
                .collect(Collectors.toList());
    }
}

