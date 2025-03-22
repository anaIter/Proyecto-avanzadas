package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import co.edu.uniquindio.proyecto.entidad.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepositorio;
import co.edu.uniquindio.proyecto.config.JwtService;
import co.edu.uniquindio.proyecto.servicios.AuthServicio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServicioImpl implements AuthServicio {

    private final UsuarioRepositorio usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServicioImpl(UsuarioRepositorio usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public String login(LoginDTO request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());

        if (usuarioOpt.isPresent() && passwordEncoder.matches(request.getContrasena(), usuarioOpt.get().getPassword())) {
            return jwtService.generateToken(usuarioOpt.get().getEmail());
        }
        throw new RuntimeException("Credenciales inválidas");
    }

    @Override
    public void register(RegistroDTO request) {
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getContrasena()));

        usuarioRepository.save(usuario);
    }
}
