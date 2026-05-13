package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.NotificacionUsuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificacionRepositorio extends MongoRepository<NotificacionUsuario, ObjectId> {
    List<NotificacionUsuario> findByIdUsuarioOrderByFechaDesc(ObjectId idUsuario);
}