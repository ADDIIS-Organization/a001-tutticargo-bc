package com.addiis.core.gestionlogistica.mappers;

import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.UnitOfMeasureRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UnitOfMeasureResponse;
import com.addiis.core.gestionlogistica.persistence.entities.product.UnitOfMeasure;

@Component
public class UnitOfMeasureMapper { 

   public UnitOfMeasure toEntity(UnitOfMeasureRequest request) {
    
    return UnitOfMeasure.builder()
        .name(request.getName())
        .code(request.getCode())
        .observation(request.getObservation())
        .build();
  }

  public UnitOfMeasureResponse toResponse(UnitOfMeasure entity) {
    
    return UnitOfMeasureResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .code(entity.getCode())
        .observation(entity.getObservation())
        .build();
  }

  
}