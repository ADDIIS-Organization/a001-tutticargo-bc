package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;

import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;

public interface ReceptionScannedProductService {
    public ReceptionScannedProduct save(ReceptionScannedProduct ReceptionScannedProduct);
    public Page<ReceptionScannedProduct> getAll(Pageable pageable);

    public static final String FIELD_BY_SORT = "dateTime";
}
