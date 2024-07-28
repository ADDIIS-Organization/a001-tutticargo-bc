package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.ProductRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductResponse;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;

@Component
public class ProductMapper {
  
  public ProductResponse toResponse(Product entity) {
    return ProductResponse.builder()
            .id(entity.getId())
            .name(entity.getName())
            .code(entity.getCode())
            .ean(entity.getEan())
            .uxc(entity.getUxc())
            .cxp(entity.getCxp())
            .observation(entity.getObservation())
            .build();
  }
  public Product toEntity(ProductRequest request) {
    return Product.builder()
            .name(request.getName())
            .code(request.getCode())
            .ean(request.getEan())
            .uxc(request.getUxc())
            .cxp(request.getCxp())
            .observation(request.getObservation())
            .build();
  }
}

