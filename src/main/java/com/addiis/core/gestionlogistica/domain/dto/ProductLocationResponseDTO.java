package com.addiis.core.gestionlogistica.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductLocationResponseDTO{
    private String productCode;
    private String productDescription;
    @JsonIgnore
    private float quantity;
    private String locationCode;
    private String sector;
    private String space;
}
