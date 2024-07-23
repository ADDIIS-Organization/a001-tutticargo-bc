package com.addiis.core.gestionlogistica.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

import com.addiis.core.gestionlogistica.domain.dto.ProductLocationDTO;

@AllArgsConstructor
@Data
public class ProductLocationResponseDTO{
    private String productId;
    private String productCode;
    private BigInteger ean;
    private String productDescription;
    private List<ProductLocationDTO> locations; // Lista de ubicaciones
    private String cxp;
    private String uxc;
    private Long warehouseLocationId;
}