package co.edu.uniquindio.proyecto.entidad;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {
    private double latitud;
    private double longitud;
    private String direccion;
}
