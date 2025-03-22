package co.edu.uniquindio.proyecto.entidad;

//import jakarta.persistence.*;
import co.edu.uniquindio.proyecto.Enum.Ciudad;
import co.edu.uniquindio.proyecto.Enum.EstadoUsuario;
import co.edu.uniquindio.proyecto.Enum.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    private String id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
    private boolean activo; // Indica si la cuenta está activada
}
*/


/*import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;  // En MongoDB, los IDs suelen ser String (ObjectId)

    private String nombre;
    private String email;
}
*/



//@Entity
//@Table(name = "usuarios")
@Document(collection = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    private ObjectId id;

    private String email;

    private String password;

    private String nombre;

    private String telefono;

    private String direccion;

    private Rol rol;

    private EstadoUsuario estado;

    private Ciudad ciudad;

    private CodigoValidacion codigoValidacion;


}