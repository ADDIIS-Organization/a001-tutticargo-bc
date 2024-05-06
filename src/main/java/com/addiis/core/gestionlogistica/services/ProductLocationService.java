package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.ProductLocation;
import com.addiis.core.gestionlogistica.persistence.entities.WarehouseLocation;

import java.util.List;
import java.util.Optional;

public interface ProductLocationService {
    // void save(ProductLocation productLocationEntity);

    // List<ProductLocation> findAll();

    ProductLocationResponseDTO getByCode(String sku);


    // Optional<ProductLocation> findByUbicacion(WarehouseLocation ubicacionesEntity);

}
