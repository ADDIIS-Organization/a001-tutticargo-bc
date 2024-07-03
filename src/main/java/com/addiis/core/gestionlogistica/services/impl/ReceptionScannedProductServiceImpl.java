package com.addiis.core.gestionlogistica.services.impl;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.ReceptionScannedProductResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;
import com.addiis.core.gestionlogistica.persistence.repositories.ReceptionScannedProductRepository;
import com.addiis.core.gestionlogistica.services.ReceptionScannedProductService;
import com.addiis.core.gestionlogistica.utils.enums.SortType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ReceptionScannedProductServiceImpl implements ReceptionScannedProductService {

    private final ReceptionScannedProductRepository ReceptionScannedProductRepository;

    @Autowired
    public ReceptionScannedProductServiceImpl(ReceptionScannedProductRepository ReceptionScannedProductRepository) {
        this.ReceptionScannedProductRepository = ReceptionScannedProductRepository;
    }

    @Override
    public ReceptionScannedProduct save(ReceptionScannedProduct ReceptionScannedProduct) {
        try {
            return ReceptionScannedProductRepository.save(ReceptionScannedProduct);
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

    @Override
    public Page<ReceptionScannedProduct> getAll(Pageable pageable) {
        return ReceptionScannedProductRepository.findAll(pageable);
    }
}
