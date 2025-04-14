package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    @PostMapping
    @Operation(summary = "Crear categoría", description = "Permite registrar una nueva categoría en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    public ResponseEntity<MensajeDTO<String>> crearCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaServicio.crearCategoria(dto));
    }

    @GetMapping
    @Operation(summary = "Listar categorías", description = "Obtiene la lista de todas las categorías registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de categorías obtenido correctamente")
    })
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaServicio.listarCategorias());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@PathVariable String id) {
        return ResponseEntity.ok(categoriaServicio.eliminarCategoria(id));
    }

    @PutMapping
    @Operation(summary = "Actualizar categoría", description = "Actualiza la información de una categoría existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada")
    })
    public ResponseEntity<MensajeDTO<String>> actualizarCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaServicio.actualizarCategoria(dto));
    }
}
