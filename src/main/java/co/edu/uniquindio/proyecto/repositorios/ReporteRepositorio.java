package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.Reporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepositorio extends MongoRepository<Reporte, ObjectId> {

    List<Reporte> findByUsuarioId(ObjectId usuarioId);

    List<Reporte> findByTituloContainingIgnoreCase(String titulo);
}
