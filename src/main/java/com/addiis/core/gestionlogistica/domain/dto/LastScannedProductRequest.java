package com.addiis.core.gestionlogistica.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LastScannedProductRequest {
    private Long productLocationId;
    private String expirationDate;
    private LocalDate manufactureDate;
}
