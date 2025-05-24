package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import co.edu.uniquindio.proyecto.servicios.AuthServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthControlador {

    private final AuthServicio authService;

    /**
     * Inicio de sesión
     */
    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDTO request) {
        try {
            String token = authService.login(request);
            return ResponseEntity.ok(new MensajeDTO<>(false, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MensajeDTO<>(true, "Error en inicio de sesión: " + e.getMessage()));
        }
    }

    /**
     * Registro de usuario
     */
    @PostMapping("/register")
    public ResponseEntity<MensajeDTO<String>> registrarSesion(@Valid @RequestBody RegistroDTO request) {
        try {
            ObjectId id = authService.register(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MensajeDTO<>(false, id.toString()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MensajeDTO<>(true, "Error en registro: " + e.getMessage()));
        }
    }
}
