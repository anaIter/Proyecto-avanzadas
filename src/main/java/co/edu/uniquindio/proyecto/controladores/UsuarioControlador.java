package co.edu.uniquindio.proyecto.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
@SecurityRequirement(name = "BearerAuth")
public class UsuarioControlador {

    /*
    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO<String>> registrarUsuario(@Valid @RequestBody Map<String, String> usuarioDTO) {
        // TODO: Llamar al servicio para registrar al usuario
        return ResponseEntity.status(201).body(new MensajeDTO<>(false, "Usuario registrado correctamente"));
    }
     */

    @PostMapping("/activar")
    public ResponseEntity<MensajeDTO<String>> activarCuenta(@Valid @RequestBody Map<String, String> datosActivacion) {
        // TODO: Validar email y código de activación
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta activada correctamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> editarUsuario(@PathVariable String id,
                                                            @Valid @RequestBody Map<String, String> usuarioDTO) {
        // TODO: Lógica de actualización de datos personales
        return ResponseEntity.ok(new MensajeDTO<>(false, "Datos actualizados correctamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String id) {
        // TODO: Lógica para eliminar cuenta de usuario
        return ResponseEntity.ok(new MensajeDTO<>(false, "Cuenta eliminada correctamente"));
    }
}
