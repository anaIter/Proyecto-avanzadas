package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.LoginDTO;
import co.edu.uniquindio.proyecto.dto.RegistroDTO;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public interface AuthServicio {

    String login(LoginDTO request) throws Exception;

    ObjectId register(RegistroDTO request) throws Exception;
}
