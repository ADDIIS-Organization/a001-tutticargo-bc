package com.addiis.core.gestionlogistica.services.product;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.addiis.core.gestionlogistica.domain.dto.request.ProductRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductResponse;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

public interface ProductService {
    void save(ProductRequest request);

    Page<ProductResponse> findAll(int page, int size);

    Optional<Product> findByEan(BigInteger ean);

    Optional<Product> findById(Long id);

    ProductLocationResponseDTO getByEan(BigInteger ean);

    ProductResponse update(ProductRequest request, Long id);

    void delete(Long id);

    ProductResponse disableProduct (Long id);


}
