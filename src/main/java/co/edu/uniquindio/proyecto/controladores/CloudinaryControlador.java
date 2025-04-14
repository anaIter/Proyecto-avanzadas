package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.entidad.Reporte;
import co.edu.uniquindio.proyecto.repositorios.ReporteRepositorio;
import co.edu.uniquindio.proyecto.servicios.CloudinaryServicio;
import co.edu.uniquindio.proyecto.servicios.ReporteServicio;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/images")
@AllArgsConstructor
public class CloudinaryControlador {

    private CloudinaryServicio cloudinaryService;
    private final ReporteServicio reporteServicio;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Agrega las imagenes a un reporte")
    public ResponseEntity<?> uploadImageEnReporte(@RequestParam("file") MultipartFile file, @RequestParam String id) {
        try {
            String url = cloudinaryService.uploadFile(file);
            reporteServicio.agregarImagenAReporte(url, id);
            return ResponseEntity.ok().body("Imagen subida correctamente: " + url);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al subir la imagen: " + e.getMessage());
        }
    }
}

