package co.edu.uniquindio.proyecto.servicios.impl;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.EmailServicio;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServicioImpl implements EmailServicio {
    @Value("${spring.mail.host}")
    private String HOST;

    @Value("${spring.mail.port}")
    private int PUERTO;

    @Value("${spring.mail.username}")
    private String USUARIO;

    @Value("${spring.mail.password}")
    private String PASSWORD;

    @Override
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {
        Email email = EmailBuilder.startingBlank()
                .from(USUARIO)
                .to(emailDTO.getDestinatario())
                .withSubject(emailDTO.getAsunto())
                .withPlainText(emailDTO.getCuerpo())
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer(HOST, PUERTO, USUARIO, PASSWORD)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);
            System.out.println("Correo enviado correctamente");

        } catch (Exception e) {
            System.err.println("Error al enviar correo:");
            e.printStackTrace();
        }

    }
}
