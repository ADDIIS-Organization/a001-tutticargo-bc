package com.addiis.core.gestionlogistica.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginRequestDTO {
    private String user;
    private String password;
}
