package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.persistence.entities.LastScannedProduct;
import com.addiis.core.gestionlogistica.persistence.repositories.LastScannedProductRepository;
import com.addiis.core.gestionlogistica.services.LastScannedProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class LastScannedProductServiceImpl implements LastScannedProductService {

    private final LastScannedProductRepository lastScannedProductRepository;

    @Autowired
    public LastScannedProductServiceImpl(LastScannedProductRepository lastScannedProductRepository) {
        this.lastScannedProductRepository = lastScannedProductRepository;
    }

    @Override
    public LastScannedProduct save(LastScannedProduct lastScannedProduct) {
        try {
            return lastScannedProductRepository.save(lastScannedProduct);
        } catch (DataAccessException e) {
            // Handle database-specific exceptions
            AddiisLogger.error("data access occurred", e.getClass().getName(), "save", e.getStackTrace().toString());
            throw new RuntimeException("Database error occurred: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other generic exceptions
            AddiisLogger.error("Database error occurred", e.getClass().getName(), "save", e.getStackTrace().toString());
            throw new RuntimeException(e.getMessage());
        }
    }

}
