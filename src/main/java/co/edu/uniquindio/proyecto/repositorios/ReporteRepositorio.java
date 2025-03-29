package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.Reporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReporteRepositorio extends MongoRepository<Reporte, ObjectId> {

    List<Reporte> findByIdUsuario(ObjectId idUsuario);

    List<Reporte> findByTituloContainingIgnoreCase(String titulo);

    Optional<Reporte> findById(ObjectId Id);
}
