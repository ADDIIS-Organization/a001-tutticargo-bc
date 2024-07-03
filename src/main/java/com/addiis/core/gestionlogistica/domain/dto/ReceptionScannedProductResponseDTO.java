package com.addiis.core.gestionlogistica.domain.dto;

import lombok.Data;
import java.util.List;


import lombok.Data;

@Data
public class ReceptionScannedProductResponseDTO<T> {
    private List<T> content;
    private int totalElements;
    private int totalPages;
}
