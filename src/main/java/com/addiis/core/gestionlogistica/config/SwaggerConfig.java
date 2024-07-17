package com.addiis.core.gestionlogistica.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.HttpAuthenticationBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Clase de configuración de Swagger
 *
 * @author Arenas Silva, Juan
 * @Empresa: Addiis S.A.S.
 */
//@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Value("${addiis.app.version}")
	private String version;
	
	@Value("${addiis.app.nombre}")
	private String nombreApp;
	
	@Value("${addiis.app.descripcion}")
	private String descripcionApp;
	
	@Value("${addiis.app.pagina}")
	private String paginaWeb;

    @Bean
    Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.addiis.core.gestionlogistica.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				;
	}
	
	private ApiInfo getApiInfo() {
				
		return new ApiInfo(
				nombreApp,
				descripcionApp,
				version,
				paginaWeb,
				new Contact("Arquitectura", paginaWeb, "juanfernandomx@gmail.com"),
				"LICENSE",
				paginaWeb,
				Collections.emptyList()
				);
	}


}