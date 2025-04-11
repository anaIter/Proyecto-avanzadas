package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.CodigoValidacion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodigoValidacionRepositorio extends MongoRepository<CodigoValidacion, ObjectId> {
    CodigoValidacion findTopByIdUsuarioOrderByFechaCreacionDesc(ObjectId idUsuario);
}

