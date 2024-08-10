package com.addiis.core.gestionlogistica.config;



import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(contact = @Contact(name = "ADDIIS", url = "api.service.tutti.addiis.co", email = "contacto@addiis.com"), title = "Documentation: TUTI-CARGO", version = "1.0.0", description = "API REST para gestion de Logistica de TUTI CARGO"),
 servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8081"),
                @Server(
                description = "PROD ENV",
                url = "https://api.service.tutti.addiis.co/swagger-ui/index.html#")

        },
		security = {
				@SecurityRequirement(name = "bearerAuth") }

)

@SecurityScheme(name = "bearerAuth", description = "JWT token from nest authentication service", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

}