package com.addiis.core.gestionlogistica.services;

import com.addiis.core.gestionlogistica.domain.dto.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.ProductLocation;

import java.util.Optional;

/**
 * The ProductLocationService interface provides methods to retrieve product location information.
 */
public interface ProductLocationService {

    /**
     * Retrieves the product location information based on the SKU code.
     *
     * @param sku the SKU code of the product
     * @return the ProductLocationResponseDTO containing the product location information
     */
    public ProductLocationResponseDTO getByEan(Integer sku);

    /**
     * Retrieves the product location information based on the ID.
     *
     * @param id the ID of the product location
     * @return an Optional containing the ProductLocation if found, otherwise empty
     */
    public Optional<ProductLocation> findById(Long id);

}
