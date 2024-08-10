package com.addiis.core.gestionlogistica.services.impl.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.config.JWTAuthtenticationConfig;
import com.addiis.core.gestionlogistica.domain.dto.request.LoginRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.UserRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UserResponse;
import com.addiis.core.gestionlogistica.persistence.entities.user.User;
import com.addiis.core.gestionlogistica.persistence.repositories.user.UserRepository;
import com.addiis.core.gestionlogistica.services.user.AuthService;
import com.addiis.core.gestionlogistica.utils.exceptions.BadRequestException;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthServiceImpl  implements AuthService {
 @Autowired
 private final AuthenticationManager authenticationManager;

 @Autowired
 private final UserRepository userRepository;

 @Autowired
  private final JWTAuthtenticationConfig jwtAuthtenticationConfig;

 @Autowired
 private final PasswordEncoder passwordEncoder;

  @Override
  public UserResponse login(LoginRequest request) {

   try{
     authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword())
     );
   }catch(Exception e){
     e.printStackTrace();
     throw new BadRequestException("Credenciales Incorrectas" + e.getMessage());
   }

   User user = this.findUser(request.getUser());
   String token = jwtAuthtenticationConfig.getJWTToken(user.getUsername(), user.getRole());
   return UserResponse.builder()
    .user(user.getUsername())
    .message("Usuario logueado")
    .token(token)
    .build();
  }

  @Override
  public UserResponse register(UserRequest request) {
      /* 1. Validar que el usuario no existe */
    User exist = this.findUser(request.getUserName());

    if (exist != null) {
      throw new BadRequestException("El usuario ya existe");
    }

    User user = User.builder()
        .userName(request.getUserName())
        .password(this.passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    user = this.userRepository.save(user); 

    return UserResponse.builder()
        .user(user.getUsername())
        .message("Usuario registrado")
        .token(jwtAuthtenticationConfig.getJWTToken(user.getUsername(), user.getRole()))
        .build();
      

  }
  
  private User findUser(String userName) {
    return userRepository.findByUserName(userName).orElse(null);
  }
}
