package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.LastScannedProduct;

public interface LastScannedProductService {
    LastScannedProduct save(LastScannedProduct lastScannedProduct);
}
