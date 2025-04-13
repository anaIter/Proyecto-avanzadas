package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepositorio;
import co.edu.uniquindio.proyecto.servicios.impl.CategoriaServicioImpl;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServicioImplTest {

    @Mock
    private CategoriaRepositorio categoriaRepositorio;

    @InjectMocks
    private CategoriaServicioImpl categoriaServicio;

    @Test
    public void testCrearCategoria() {
        CategoriaDTO dto = CategoriaDTO.builder()
                .nombre("Tecnología")
                .descripcion("Categoría de artículos tecnológicos")
                .build();

        MensajeDTO<String> resultado = categoriaServicio.crearCategoria(dto);

        assertNotNull(resultado);
        assertFalse(resultado.isError());
        assertEquals("Categoría creada correctamente", resultado.getMensaje());

        verify(categoriaRepositorio, times(1)).save(any(Categoria.class));
    }

    @Test
    public void testEliminarCategoria() {
        String id = new ObjectId().toString();

        MensajeDTO<String> resultado = categoriaServicio.eliminarCategoria(id);

        assertNotNull(resultado);
        assertFalse(resultado.isError());
        assertEquals("Categoría eliminada correctamente", resultado.getMensaje());

        verify(categoriaRepositorio, times(1)).deleteById(id);
    }

    @Test
    public void testListarCategorias() {
        List<Categoria> categorias = List.of(
                Categoria.builder().nombre("Tecnología").descripcion("Artículos tech").build(),
                Categoria.builder().nombre("Libros").descripcion("Literatura y más").build()
        );

        when(categoriaRepositorio.findAll()).thenReturn(categorias);

        List<CategoriaDTO> resultado = categoriaServicio.listarCategorias();

        assertEquals(2, resultado.size());
        assertEquals("Tecnología", resultado.get(0).getNombre());
        assertEquals("Libros", resultado.get(1).getNombre());

        verify(categoriaRepositorio, times(1)).findAll();
    }
}

