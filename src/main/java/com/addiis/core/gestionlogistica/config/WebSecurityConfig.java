package com.addiis.core.gestionlogistica.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.addiis.core.gestionlogistica.utils.enums.Role;

@EnableWebSecurity
@Configuration
class WebSecurityConfig {

        @Autowired
        private AuthenticationProvider authenticationProvider;

        @Autowired
        private JWTAuthorizationFilter jwtAuthorizationFilter;

        @Autowired

        private static final String[] AUTH_WHITELIST = {

                        // -- Swagger UI v3 (OpenAPI)
                        "/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/v3/api-docs.yaml",
                        "/swagger-ui.html",
                        // other public endpoints of your API may be appended to this array
        };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                return http
                                .csrf(csrf -> csrf.disable()) // Desabilitar protección csrf -> Statelest
                                .authorizeHttpRequests(authRequest -> authRequest

                                                .requestMatchers(AUTH_WHITELIST).permitAll() // Configurar rutas
                                                                                             // publicas
                                                .anyRequest().authenticated())
                                .sessionManagement(sessionManager -> sessionManager
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // Agregarmos el proveedor de autenticación
                                .authenticationProvider(authenticationProvider)

                                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                                .build();

        }

        @Bean
        public WebMvcConfigurer corsConfigurer() {
                return new WebMvcConfigurer() {
                        @Override
                        public void addCorsMappings(CorsRegistry registry) {
                                registry.addMapping("/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
                        }
                };
        }
}