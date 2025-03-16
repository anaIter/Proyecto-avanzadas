package co.edu.uniquindio.proyecto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aplicacion de reportes ")
                        .description("Aplicacion que permite generar reportes basados en ubicacion")
                        .version("1.0")
                        .termsOfService("Términos del servicio")
                        .contact(new Contact()
                                .name("Nombre")
                                .url("URL")
                                .email("Correo"))
                        .license(new License()
                                .name("Licencia")
                                .url("URL de licencia"))
                );
    }
}
