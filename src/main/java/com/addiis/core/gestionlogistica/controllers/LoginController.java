package com.addiis.core.gestionlogistica.controllers;

import com.addiis.core.gestionlogistica.config.JWTAuthtenticationConfig;
import com.addiis.core.gestionlogistica.domain.dto.LoginRequestDTO;
import com.addiis.core.gestionlogistica.domain.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("login")
    public UserDTO login(@RequestBody LoginRequestDTO request){

        /**
         * TODO: Validar credenciales
         */
        String token = jwtAuthtenticationConfig.getJWTToken(request.getUser());
        return new UserDTO(request.getUser(), request.getPassword(),token);

    }
}