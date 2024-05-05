package com.addiis.core.gestionlogistica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductLocationResponseDTO{
    private String sku;
    private String productDescription;
    private float quantity;
    private String locationCode;
    private String sector;
    private String space;
}
