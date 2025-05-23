package com.addiis.core.gestionlogistica.domain.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReceptionScannedProductRequest {
    private String expirationDate; // Fecha de vencimiento
    private String manufactureDate; // Fecha de fabricación
    private Integer usefulLife; // Vida útil
    private Integer receptionPercentage; // Porcentaje de recepción
    private Long warehouseLocationId; // ID de la ubicación del producto en la bodega.
    private String lot; // Lote
    private Integer amountReceived;
    private String SKU;
    private String descriptionProduct;
}
