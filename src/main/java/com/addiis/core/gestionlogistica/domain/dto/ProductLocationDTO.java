package com.addiis.core.gestionlogistica.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class ProductLocationDTO {
    private Long id;
    private String ubicacion;
    private String dispo;
    // private String space;
}
