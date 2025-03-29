package co.edu.uniquindio.proyecto.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/saludo") // Prefijo común para todas las rutas del controlador
public class SaludoControlador {

    @GetMapping
    public String saludar(){
        return "Hola, bienvenido a la aplicación";
    }

    @GetMapping("/{nombre}")
    public String saludarNombre(@PathVariable String nombre){
        return "Hola %s, bienvenido a la aplicación".formatted(nombre);
    }
}
