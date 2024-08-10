package com.addiis.core.gestionlogistica.services.user;

import com.addiis.core.gestionlogistica.domain.dto.request.LoginRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.UserRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UserResponse;

public interface AuthService {
  
  public UserResponse login(LoginRequest request);
  public UserResponse register(UserRequest request);
}
