package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.CodigoValidacion;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CodigoValidacionRepositorio;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.servicios.CodigoValidacionServicio;
import co.edu.uniquindio.proyecto.servicios.EmailServicio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
 public class CodigoValidacionImpl implements CodigoValidacionServicio {

    @Autowired
    private EmailServicio emailServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio; // para obtener el correo del usuario

    @Autowired
    private CodigoValidacionRepositorio codigoValidacionRepositorio;

    @Override
    public MensajeDTO<String> generarCodigo(ObjectId idUsuario) {
        CodigoValidacion codigo = CodigoValidacion.generarCodigo(idUsuario);
        codigoValidacionRepositorio.save(codigo);

        // Obtener el correo del usuario
        Usuario usuario = usuarioRepositorio.findById(idUsuario.toHexString()).orElse(null);
        if (usuario != null) {
            EmailDTO email = new EmailDTO();
            email.setDestinatario(usuario.getEmail());
            email.setAsunto("Tu código de verificación");
            email.setCuerpo("Hola " + usuario.getNombre() + ", tu código de verificación es: " + codigo.getCodigo());

            try {
                emailServicio.enviarCorreo(email);
            } catch (Exception e) {
                e.printStackTrace(); // o log.error(...) si usas Logger
                return new MensajeDTO<>(true, "Código generado pero no se pudo enviar el correo.");
            }
        }

        return new MensajeDTO<>(false, "Código generado y enviado: " + codigo.getCodigo());
    }


    @Override
    public boolean validarCodigo(ObjectId idUsuario, String codigoIngresado) {
        CodigoValidacion ultimo = codigoValidacionRepositorio.findTopByIdUsuarioOrderByFechaCreacionDesc(idUsuario);

        if (ultimo != null && ultimo.getCodigo().equals(codigoIngresado) && ultimo.estaVigente()) {
            return true;
        }
        return false;
    }
}
