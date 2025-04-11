package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidad.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends MongoRepository<Categoria, String> {
}



