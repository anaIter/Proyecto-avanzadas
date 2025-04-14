package co.edu.uniquindio.proyecto.controladores;

import co.edu.uniquindio.proyecto.dto.GenerarCodigoDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.servicios.CodigoValidacionServicio;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/codigo-validacion")
@RequiredArgsConstructor
public class CodigoValidacionControlador {

    private final CodigoValidacionServicio codigoValidacionServicio;

    @PostMapping("/generar")
    public ResponseEntity<MensajeDTO<String>> generarCodigo(@RequestBody GenerarCodigoDTO dto) {
        return ResponseEntity.ok(
                codigoValidacionServicio.generarCodigo(new ObjectId(dto.getIdUsuario()))
        );
    }
}

