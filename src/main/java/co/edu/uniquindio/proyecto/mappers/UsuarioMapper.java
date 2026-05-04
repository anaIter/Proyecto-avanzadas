package co.edu.uniquindio.proyecto.mappers;

import co.edu.uniquindio.proyecto.dto.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.Enum.Ciudad;
import lombok.experimental.UtilityClass;
import org.bson.types.ObjectId;

@UtilityClass // Indica que esta clase solo tiene métodos estáticos
public class UsuarioMapper {

    /**
     * Convierte un Usuario en UsuarioDTO.
     * @param usuario Entidad Usuario
     * @return DTO UsuarioDTO
     */
    public UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getEstado()
        );
    }

    /**
     * Convierte un CrearUsuarioDTO en Usuario.
     * @param dto DTO con los datos para crear un usuario
     * @return Entidad Usuario
     */
    public Usuario toEntity(CrearUsuarioDTO dto) {
        return new Usuario(
                new ObjectId(), // Se genera un nuevo ID
                dto.getEmail(),
                dto.getContrasena(),
                dto.getNombre(),
                dto.getTelefono(),
                dto.getDireccion(),
                dto.getRol(),
                dto.getEstado(),
                dto.getCiudad(),
                null // Código de validación (puede ser null o generarse aparte)
        );
    }

    /**
     * Actualiza un Usuario existente con datos de EditarUsuarioDTO.
     * @param usuario Usuario existente
     * @param dto DTO con los datos actualizados
     */
    public void actualizarUsuario(Usuario usuario, EditarUsuarioDTO dto) {
        usuario.setNombre(dto.getNombre());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDireccion(dto.getDireccion());
        if (dto.getCiudad() != null && !dto.getCiudad().isEmpty()) {
            try {
                usuario.setCiudad(Ciudad.valueOf(dto.getCiudad().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Si el valor no es válido, no se actualiza la ciudad
            }
        }
    }

}

