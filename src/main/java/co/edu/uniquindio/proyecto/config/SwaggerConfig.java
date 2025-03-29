package co.edu.uniquindio.proyecto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aplicación de Reportes")
                        .description("Aplicación que permite generar reportes basados en ubicación")
                        .version("1.0")
                        .termsOfService("Términos del servicio")
                        .contact(new Contact()
                                .name("Nombre")
                                .url("https://tu-url.com")
                                .email("correo@ejemplo.com"))
                        .license(new License()
                                .name("Licencia")
                                .url("https://tu-url.com/licencia"))
                )
                // 🔹 Aplicar seguridad globalmente, excepto en rutas específicas
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .schemaRequirement("Bearer Authentication",
                        new SecurityScheme()
                                .name("Authorization")
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));
    }
}
