package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;

import java.util.List;
import java.util.Optional;

public interface WarehouseLocationService {
    List<WarehouseLocation> findByProduct(Product product);
    Optional<WarehouseLocation> findById(Long id);
}
