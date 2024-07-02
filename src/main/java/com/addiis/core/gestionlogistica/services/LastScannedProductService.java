package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.domain.dto.LastScannedProductResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.product.LastScannedProduct;
import com.addiis.core.gestionlogistica.utils.enums.SortType;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

public interface LastScannedProductService {
    public LastScannedProduct save(LastScannedProduct lastScannedProduct);
    public Page<LastScannedProduct> getAll(Pageable pageable);

    public static final String FIELD_BY_SORT = "dateTime";
}
