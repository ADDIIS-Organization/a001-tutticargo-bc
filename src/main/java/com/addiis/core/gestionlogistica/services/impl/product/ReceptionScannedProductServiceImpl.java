package com.addiis.core.gestionlogistica.services.impl.product;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.response.ReceptionScannedProductResponseDTO;
import com.addiis.core.gestionlogistica.persistence.entities.product.ReceptionScannedProduct;
import com.addiis.core.gestionlogistica.persistence.repositories.products.ReceptionScannedProductRepository;
import com.addiis.core.gestionlogistica.services.ReceptionScannedProductService;
import com.addiis.core.gestionlogistica.utils.enums.SortType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ReceptionScannedProductServiceImpl implements ReceptionScannedProductService {

    private final ReceptionScannedProductRepository receptionScannedProductRepository;

    @Autowired
    public ReceptionScannedProductServiceImpl(ReceptionScannedProductRepository receptionScannedProductRepository) {
        this.receptionScannedProductRepository = receptionScannedProductRepository;
    }

    @Override
    public ReceptionScannedProduct save(ReceptionScannedProduct receptionScannedProduct) {
        try {
            return receptionScannedProductRepository.save(receptionScannedProduct);
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
    public Page<ReceptionScannedProductResponseDTO> getAll(Pageable pageable) {
        try {
            return receptionScannedProductRepository.findAll(pageable)
                    .map(this::toReceptionScannedProductResponseDTO);
        } catch (DataAccessException e) {
            // Handle database-specific exceptions
            AddiisLogger.error("data access occurred", e.getClass().getName(), "getAll", e.getStackTrace().toString());
            throw new RuntimeException("Database error occurred: " + e.getMessage(), e);
        } catch (Exception e) {
            // Handle other generic exceptions
            AddiisLogger.error("Database error occurred", e.getClass().getName(), "getAll", e.getStackTrace().toString());
            throw new RuntimeException(e.getMessage());
        }
    }

    private ReceptionScannedProductResponseDTO toReceptionScannedProductResponseDTO(ReceptionScannedProduct receptionScannedProduct) {
        return ReceptionScannedProductResponseDTO.builder()
                .id(receptionScannedProduct.getId())
                .expirationDate(receptionScannedProduct.getExpirationDate().toString())
                .manufactureDate(receptionScannedProduct.getManufactureDate().toString())
                .usefulLife(receptionScannedProduct.getUsefulLife())
                .receptionPercentage(receptionScannedProduct.getReceptionPercentage())
                .warehouseLocationId(receptionScannedProduct.getWarehouseLocation().getId())
                .lot(receptionScannedProduct.getLot())
                .amountReceived(receptionScannedProduct.getAmountReceived())
                .SKU(receptionScannedProduct.getSKU())
                .descriptionProduct(receptionScannedProduct.getDescriptionProduct())
                .build();
    }
}
