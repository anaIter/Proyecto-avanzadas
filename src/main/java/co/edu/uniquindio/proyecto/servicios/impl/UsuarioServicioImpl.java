package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.ActivarCuentaDTO;
import co.edu.uniquindio.proyecto.dto.CrearUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.EditarUsuarioDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.entidad.CodigoValidacion;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.Enum.EstadoUsuario;
import co.edu.uniquindio.proyecto.repositorios.CodigoValidacionRepositorio;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import co.edu.uniquindio.proyecto.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private CodigoValidacionRepositorio codigoValidacionRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServicioImpl.class);

    @Override
    @Transactional
    public void crear(CrearUsuarioDTO dto) throws Exception {
        if (usuarioRepositorio.findByEmail(dto.getEmail()).isPresent()) {
            throw new Exception("El email ya está registrado");
        }

        Usuario usuario = UsuarioMapper.toEntity(dto);
        usuario.setPassword(passwordEncoder.encode(dto.getContrasena())); // Encriptar contraseña
        usuario.setEstado(EstadoUsuario.ACTIVO); // Estado por defecto

        usuarioRepositorio.save(usuario);
        logger.info("Usuario creado: {}", usuario.getEmail());
    }

    @Override
    @Transactional
    public void editar(String id,EditarUsuarioDTO dto) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.getEstado() == EstadoUsuario.ELIMINADO) {
            throw new Exception("No se puede editar un usuario eliminado");
        }

        UsuarioMapper.actualizarUsuario(usuario, dto);
        usuarioRepositorio.save(usuario);
        logger.info("Usuario editado: {}", usuario.getEmail());
    }

    @Override
    @Transactional
    public void eliminar(String id) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new Exception("El usuario no existe");
        }

        Usuario usuario = usuarioOpt.get();
        usuario.setEstado(EstadoUsuario.ELIMINADO); // Cambio de estado

        usuarioRepositorio.save(usuario);
        logger.info("Usuario eliminado: {}", usuario.getEmail());
    }

    @Override
    public UsuarioDTO obtener(String email) throws Exception {
        Usuario usuario = usuarioRepositorio.findByEmail(email)
                .orElseThrow(() -> new Exception("El usuario no existe"));

        if (usuario.getEstado() == EstadoUsuario.ELIMINADO) {
            throw new Exception("El usuario ha sido eliminado");
        }

        return UsuarioMapper.toDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> listarTodos(String nombre, String ciudad) {
        return usuarioRepositorio.findAll().stream()
                .filter(usuario -> usuario.getEstado() != EstadoUsuario.ELIMINADO) // No incluir eliminados
                .filter(usuario -> (nombre == null || usuario.getNombre().toLowerCase().contains(nombre.toLowerCase())))
                .filter(usuario -> (ciudad == null || usuario.getCiudad().name().equalsIgnoreCase(ciudad)))
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void activarCuenta(ActivarCuentaDTO dto) throws Exception {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.findByEmail(dto.getEmail());

        if (usuarioOpt.isEmpty()) {
            throw new Exception("El usuario con el email proporcionado no existe");
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.getEstado() == EstadoUsuario.ACTIVO) {
            throw new Exception("La cuenta ya está activada");
        }

        // Simulación de validación del código (deberías guardarlo en la BD)
        CodigoValidacion codigoValidacion = codigoValidacionRepositorio
                .findTopByIdUsuarioOrderByFechaCreacionDesc(usuario.getId());

        if (codigoValidacion == null || !codigoValidacion.getCodigo().equals(dto.getCodigoActivacion())) {
            throw new Exception("El código de activación es incorrecto");
        }

        if (!codigoValidacion.estaVigente()) {
            throw new Exception("El código ha expirado, solicita uno nuevo");
        }

        usuario.setEstado(EstadoUsuario.ACTIVO);
        usuarioRepositorio.save(usuario);
        logger.info("Cuenta activada para el usuario: {}", usuario.getEmail());
    }
}
