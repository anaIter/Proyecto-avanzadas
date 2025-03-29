package co.edu.uniquindio.proyecto.controladores;


import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/informes")
public class InformeControlador {

    @GetMapping
    public ResponseEntity<MensajeDTO<String>> generarInforme(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false, name = "date_range") String dateRange) {
        // TODO: Generar informe según filtros
        return ResponseEntity.ok(new MensajeDTO<>(false, "Informe generado correctamente"));
    }

    @GetMapping("/pdf")
    public ResponseEntity<MensajeDTO<String>> exportarPDF(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sector,
            @RequestParam(required = false, name = "date_range") String dateRange) {
        // TODO: Generar y exportar informe en PDF
        return ResponseEntity.ok(new MensajeDTO<>(false, "Informe PDF generado correctamente"));
    }
}
