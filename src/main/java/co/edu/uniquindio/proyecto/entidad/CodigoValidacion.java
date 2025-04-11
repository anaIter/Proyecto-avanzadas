package co.edu.uniquindio.proyecto.entidad;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Random;

@Document("codigos_validacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodigoValidacion {

    @Id
    private ObjectId id;

    private String codigo; // Código de 4 dígitos

    private ObjectId idUsuario;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaExpiracion;

    public static CodigoValidacion generarCodigo(ObjectId idUsuario) {
        String codigoGenerado = String.format("%04d", new Random().nextInt(10000)); // 4 dígitos
        LocalDateTime ahora = LocalDateTime.now();
        return CodigoValidacion.builder()
                .idUsuario(idUsuario)
                .codigo(codigoGenerado)
                .fechaCreacion(ahora)
                .fechaExpiracion(ahora.plusMinutes(15)) // dura 15 minutos
                .build();
    }

    public boolean estaVigente() {
        return LocalDateTime.now().isBefore(fechaExpiracion);
    }
}


