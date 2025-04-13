package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.Enum.EstadoUsuario;
import co.edu.uniquindio.proyecto.Enum.Rol;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.config.JwtService;
import co.edu.uniquindio.proyecto.servicios.AuthServicio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServicioImpl implements AuthServicio {

    private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(AuthServicioImpl.class);

    @Override
    public String login(LoginDTO request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isEmpty()) {
            logger.warn("Intento de login fallido: usuario no encontrado ({})", request.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.getEstado() != EstadoUsuario.ACTIVO) {
            logger.warn("Intento de login fallido: usuario inactivo/eliminado ({})", request.getEmail());
            throw new RuntimeException("Usuario inactivo o eliminado");
        }

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getPassword())) {
            logger.warn("Intento de login fallido: contraseña incorrecta ({})", request.getEmail());
            throw new RuntimeException("Credenciales inválidas");
        }

        logger.info("Usuario autenticado correctamente: {}", request.getEmail());
        return jwtService.generateToken(usuario.getEmail(),usuario.getRol());
    }

    @Override
    @Transactional
    public void register(RegistroDTO request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            logger.warn("Intento de registro fallido: email ya registrado ({})", request.getEmail());
            throw new RuntimeException("El email ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getContrasena())); // Encriptar la contraseña
        usuario.setEstado(EstadoUsuario.INACTIVO); // Estado inicial: ACTIVO
        usuario.setRol(Rol.CLIENTE); // Rol por defecto: CLIENTE

        usuarioRepository.save(usuario);
        logger.info("Usuario registrado exitosamente: {}", request.getEmail());
    }
}
