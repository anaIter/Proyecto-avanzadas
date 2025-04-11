package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import org.bson.types.ObjectId;

public interface CodigoValidacionServicio {
    MensajeDTO<String> generarCodigo(ObjectId idUsuario);
    boolean validarCodigo(ObjectId idUsuario, String codigo);
}

