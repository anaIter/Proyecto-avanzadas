package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.uniquindio.proyecto.servicios.AuthServicio;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/auth")
public class AuthControlador {


    private final AuthServicio authService;

    public AuthControlador(AuthServicio authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<String>> iniciarSesion(@Valid @RequestBody LoginDTO request) {
        // TODO: Validar credenciales de usuario
        String token = authService.login(request);
        return ResponseEntity.ok(new MensajeDTO<>(false, "Inicio de sesión exitoso: "+token));
    }

    @PostMapping("/register")
    public ResponseEntity<MensajeDTO<String>> registrarSesion(@Valid @RequestBody RegistroDTO request) {
        authService.register(request);
        return ResponseEntity.ok(new MensajeDTO<>(true, "Registro exitoso"));
    }
}