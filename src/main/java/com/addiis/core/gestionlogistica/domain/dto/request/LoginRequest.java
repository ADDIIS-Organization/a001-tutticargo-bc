package com.addiis.core.gestionlogistica.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequest {
    private String user;
    private String password;
}
