package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
@AllArgsConstructor
public class MensajeDTO<T> {
    private boolean error;
    private T mensaje;

}
