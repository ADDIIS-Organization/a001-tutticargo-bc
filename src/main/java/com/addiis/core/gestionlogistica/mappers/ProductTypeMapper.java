package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.ProductTypeRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductTypeResponse;
import com.addiis.core.gestionlogistica.persistence.entities.product.ProductType;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductTypeMapper {

 
  
  public ProductType toEntity(ProductTypeRequest request) {
    
    return ProductType.builder()
        .name(request.getName())
        .observation(request.getObservation())
        .build();
  }

  public ProductTypeResponse toResponse (ProductType entity) {
    
    return ProductTypeResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .observation(entity.getObservation())
        .build();
  }
}
