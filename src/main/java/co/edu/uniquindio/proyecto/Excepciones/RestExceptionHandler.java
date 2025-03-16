package co.edu.uniquindio.proyecto.Excepciones;




import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO<String>> manejarExcepcion(Exception e) {
        return ResponseEntity.internalServerError().body(new MensajeDTO<>(true, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDTO<String>> manejarValidacion(MethodArgumentNotValidException ex) {
        // Puedes procesar 'ex' para obtener detalles de los errores de validación y construir una respuesta más detallada
        return ResponseEntity.badRequest().body(new MensajeDTO<>(true, "Error en validación"));
    }
}
