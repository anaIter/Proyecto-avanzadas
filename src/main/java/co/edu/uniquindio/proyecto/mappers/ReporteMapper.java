package co.edu.uniquindio.proyecto.mappers;

import co.edu.uniquindio.proyecto.dto.ReporteDTO;
import co.edu.uniquindio.proyecto.entidad.Reporte;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReporteMapper {

        public static ReporteDTO toDTO(Reporte reporte) {
            if (reporte == null) return null;

            ReporteDTO dto = new ReporteDTO();
            dto.setTipo(reporte.getCategoria());
            dto.setDescripcion(reporte.getDescripcion());
            dto.setUbicacion(reporte.getUbicacion() != null ? reporte.getUbicacion().toString() : null);
            return dto;
        }


}

