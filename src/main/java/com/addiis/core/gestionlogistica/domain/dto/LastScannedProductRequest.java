package com.addiis.core.gestionlogistica.domain.dto;

import lombok.Data;

@Data
public class LastScannedProductRequest {
    private Long productId;
    private String expirationDate;
}
