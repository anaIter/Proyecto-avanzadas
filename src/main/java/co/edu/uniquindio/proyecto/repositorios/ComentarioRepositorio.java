package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.Comentario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepositorio extends MongoRepository<Comentario, ObjectId> {
    List<Comentario> findByIdReporte(ObjectId idReporte);
}
