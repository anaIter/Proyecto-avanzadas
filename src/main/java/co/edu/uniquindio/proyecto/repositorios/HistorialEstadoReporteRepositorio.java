package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.HistorialEstadoReporte;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HistorialEstadoReporteRepositorio extends MongoRepository<HistorialEstadoReporte, ObjectId> {

    List<HistorialEstadoReporte> findByIdReporteOrderByFechaCambioDesc(ObjectId idReporte);
}
