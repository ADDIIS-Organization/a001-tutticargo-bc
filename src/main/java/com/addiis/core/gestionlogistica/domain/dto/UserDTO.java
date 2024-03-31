package com.addiis.core.gestionlogistica.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @JsonIgnore
    private String user;

    @JsonIgnore
    private String pass;

    private String token;

}