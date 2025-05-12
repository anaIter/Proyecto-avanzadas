package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    /**
     * Activación de cuenta mediante código de verificación
     */
    @PostMapping("/activar")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@RequestBody ActivarCuentaDTO datosActivacion) {
        try {
            usuarioServicio.activarCuenta(datosActivacion);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    /**
     * Edición de datos del usuario
     */
    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@PathVariable String id,
                                                            @Valid @RequestBody EditarUsuarioDTO usuarioDTO) {
        try {
            usuarioServicio.editar(id,usuarioDTO);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Datos actualizados correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    @PutMapping("/{id}/cambiar-contrasena")
    public ResponseEntity<MensajeDTO<String>> cambiarContrasena(@PathVariable String id,
                                                                @RequestBody @Valid CambiarContrasenaDTO dto) {
        try {
            usuarioServicio.cambiarContrasena(id, dto);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Contraseña actualizada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeDTO<>(true, e.getMessage()));
        }
    }


    /**
     * Eliminación (desactivación) de cuenta de usuario
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String id) {
        try {
            usuarioServicio.eliminar(id);
            return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada correctamente"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeDTO<>(true, e.getMessage()));
        }
    }

    /**
     * Obtención de un usuario por ID
     */
    @GetMapping("/{email}")
    public ResponseEntity<MensajeDTO<UsuarioDTO>> obtenerUsuario(@PathVariable String email) {
        try {
            UsuarioDTO usuario = usuarioServicio.obtener(email);
            return ResponseEntity.ok(new MensajeDTO<>(false, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MensajeDTO<UsuarioDTO>(true, null));

        }
    }

    /**
     * Listado de usuarios filtrado por nombre y ciudad
     */
    @GetMapping("/listar")
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String ciudad) {
        List<UsuarioDTO> usuarios = usuarioServicio.listarTodos(nombre, ciudad);
        return ResponseEntity.ok(new MensajeDTO<>(false, usuarios));
    }
}
