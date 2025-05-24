package co.edu.uniquindio.proyecto.mappers;

import co.edu.uniquindio.proyecto.dto.UbicacionDTO;
import co.edu.uniquindio.proyecto.entidad.Ubicacion;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class UbicacionMapper {

    public static Ubicacion dtoToEntidad(UbicacionDTO dto) {
        if (dto == null) return null;

        return Ubicacion.builder()
                .direccion(dto.getDireccion())
                .latitud(dto.getLatitud())
                .longitud(dto.getLongitud())
                .coordenadas(new GeoJsonPoint(dto.getLongitud(), dto.getLatitud()))
                .build();
    }

    public static UbicacionDTO entidadToDTO(Ubicacion ubicacion) {
        if (ubicacion == null) return null;

        return UbicacionDTO.builder()
                .direccion(ubicacion.getDireccion())
                .latitud(ubicacion.getLatitud())
                .longitud(ubicacion.getLongitud())
                .build();
    }
}
