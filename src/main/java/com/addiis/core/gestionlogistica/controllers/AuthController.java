package com.addiis.core.gestionlogistica.controllers;

import com.addiis.core.gestionlogistica.domain.dto.request.LoginRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.UserRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UserResponse;
import com.addiis.core.gestionlogistica.services.user.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponse> login(@RequestBody LoginRequest request){

      return ResponseEntity.ok(this.authService.login(request));

    }

    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request){

      return ResponseEntity.ok(this.authService.register(request));
    }
}