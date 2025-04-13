package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Data
public class MensajeDTO<T> {
    private boolean error;
    private T mensaje;

    public MensajeDTO(boolean error, T mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public boolean isError() {
        return error;
    }

    public T getMensaje() {
        return mensaje;
    }
}
