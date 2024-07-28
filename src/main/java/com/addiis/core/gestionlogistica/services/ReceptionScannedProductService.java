package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.domain.dto.request.ReceptionScannedProductRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ReceptionScannedProductResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

public interface ReceptionScannedProductService {
    public ReceptionScannedProductResponseDTO save(ReceptionScannedProductRequest ReceptionScannedProduct);
    public Page<ReceptionScannedProductResponseDTO> getAll(Pageable pageable);

    public static final String FIELD_BY_SORT = "dateTime";
}
