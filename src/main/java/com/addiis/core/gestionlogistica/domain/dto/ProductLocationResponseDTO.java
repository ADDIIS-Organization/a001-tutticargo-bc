package com.addiis.core.gestionlogistica.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductLocationResponseDTO{
    private Double quantity;
    private String id;
    private String productId;
    private String productCode;
    private Integer ean;
    private String productDescription;
    private String locationCode;
    private String sector;
    private String space;
}
