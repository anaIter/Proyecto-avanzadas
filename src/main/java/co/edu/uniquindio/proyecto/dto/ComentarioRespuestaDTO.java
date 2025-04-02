package co.edu.uniquindio.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ComentarioRespuestaDTO {

    private String id;

    private String idReporte;

    private String idUsuario;

    private String contenido;

    private String fechaCreacion;
}
