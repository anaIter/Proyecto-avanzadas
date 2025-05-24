package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {
    private double latitud;
    private double longitud;
    private String direccion;

    private GeoJsonPoint coordenadas; // ✅ Agrega esta línea
}
