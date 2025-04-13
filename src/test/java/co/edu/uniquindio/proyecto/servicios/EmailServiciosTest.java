package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.impl.EmailServicioImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class EmailServiciosTest {

    private EmailServicioImpl emailServicio;

    @BeforeEach
    public void setUp() {
        emailServicio = new EmailServicioImpl();

        // Configurar valores simulados del application.properties
        ReflectionTestUtils.setField(emailServicio, "HOST", "smtp.gmail.com");
        ReflectionTestUtils.setField(emailServicio, "PUERTO", 587);
        ReflectionTestUtils.setField(emailServicio, "USUARIO", "emailservicio592@gmail.com");
        ReflectionTestUtils.setField(emailServicio, "PASSWORD", "rjew pbdp egpu ymsp");
    }

    @Test
    public void testEnviarCorreo() {
        EmailDTO emailDTO = new EmailDTO(
                "tatianamosqueraiter182@gmail.com",
                "Prueba #1 ",
                "Esto es un correo de prueba para ensayar el correo"
        );

        assertDoesNotThrow(() -> {
            emailServicio.enviarCorreo(emailDTO);
            System.out.println("✅ Correo enviado exitosamente.");
        });
    }
}
