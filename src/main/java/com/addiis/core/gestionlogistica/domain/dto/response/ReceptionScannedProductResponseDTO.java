package com.addiis.core.gestionlogistica.domain.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;


import lombok.Data;

@Data
@Builder
public class ReceptionScannedProductResponseDTO {
    private Long id;
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
