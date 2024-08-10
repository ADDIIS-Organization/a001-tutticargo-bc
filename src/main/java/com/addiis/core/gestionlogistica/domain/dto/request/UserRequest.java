package com.addiis.core.gestionlogistica.domain.dto.request;

import com.addiis.core.gestionlogistica.utils.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserRequest {
    private String userName;
    private String password;
    private Role role;
}