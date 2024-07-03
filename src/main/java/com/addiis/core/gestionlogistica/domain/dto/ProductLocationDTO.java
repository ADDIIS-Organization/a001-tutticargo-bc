package com.addiis.core.gestionlogistica.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductLocationDTO {
    private String locationCode;
    private String sector;
    private String space;
}
