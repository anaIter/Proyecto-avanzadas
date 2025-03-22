package co.edu.uniquindio.proyecto.entidad;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CodigoValidacion {

    private LocalDateTime fecha;

    private String codigo;

}
