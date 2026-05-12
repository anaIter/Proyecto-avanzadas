package co.edu.uniquindio.proyecto.repositorios;


/*
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
*/

//Implementacion del repositorio de MongoDB

/*
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}*/

// UsuarioRepositorio.java
import co.edu.uniquindio.proyecto.entidad.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UsuarioRepositorio extends MongoRepository<Usuario, ObjectId> {
    // En UsuarioRepositorio.java, agrega:
    @Query("{ '_id': { $oid: ?0 } }")
    Optional<Usuario> findByObjectIdHex(String hexId);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByNombre(String nombre);
}