package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.MensajeDTO;
import co.edu.uniquindio.proyecto.entidad.CodigoValidacion;
import co.edu.uniquindio.proyecto.repositorios.CodigoValidacionRepositorio;
import co.edu.uniquindio.proyecto.servicios.CodigoValidacionServicio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
 public class CodigoValidacionImpl implements CodigoValidacionServicio {

    @Autowired
    private CodigoValidacionRepositorio codigoValidacionRepositorio;

    @Override
    public MensajeDTO<String> generarCodigo(ObjectId idUsuario) {
        CodigoValidacion codigo = CodigoValidacion.generarCodigo(idUsuario);
        codigoValidacionRepositorio.save(codigo);

        // Aquí podrías enviar el código por correo/SMS o devolverlo (solo en pruebas)
        return new MensajeDTO<>(false, "Código generado: " + codigo.getCodigo());
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
