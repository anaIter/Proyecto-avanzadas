package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensajeDTO<T> {
    private boolean error;  // true si hubo un error, false si fue exitoso
    private T respuesta;    // el contenido de la respuesta (mensaje, objeto, lista, etc.)
}