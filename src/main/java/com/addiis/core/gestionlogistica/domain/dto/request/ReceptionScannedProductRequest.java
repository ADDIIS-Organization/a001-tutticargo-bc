package com.addiis.core.gestionlogistica.domain.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReceptionScannedProductRequest {
    private String expirationDate; // Fecha de vencimiento
    private String manufactureDate; // Fecha de fabricación
    private Integer usefulLife; // Vida útil
    private Integer receptionPercentage; // Porcentaje de recepción
    private Long warehouseLocationId; // ID de la ubicación del producto en la bodega.
    private String lot; // Lote
}
