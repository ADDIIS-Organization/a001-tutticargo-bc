package com.addiis.core.gestionlogistica.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseConsultaMaterialDTO {
    private String sku;
    private String descripcionProducto;
    private float cantidad;
    private String codigoUbicacion;
    private String sector;
    private String espacio;
}
