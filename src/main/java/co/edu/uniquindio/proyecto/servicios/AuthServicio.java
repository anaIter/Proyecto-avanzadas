package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthServicio {

    String login(LoginDTO request) throws Exception;

    void register(RegistroDTO request) throws Exception;
}
