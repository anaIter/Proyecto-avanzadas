package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.CategoriaDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categorias")
@RequiredArgsConstructor
public class CategoriaControlador {

    private final CategoriaServicio categoriaServicio;

    @PostMapping
    public ResponseEntity<MensajeDTO<String>> crearCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaServicio.crearCategoria(dto));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        return ResponseEntity.ok(categoriaServicio.listarCategorias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCategoria(@PathVariable String id) {
        return ResponseEntity.ok(categoriaServicio.eliminarCategoria(id));
    }

    @PutMapping
    public ResponseEntity<MensajeDTO<String>> actualizarCategoria(@RequestBody @Valid CategoriaDTO dto) {
        return ResponseEntity.ok(categoriaServicio.actualizarCategoria(dto));
    }
}

