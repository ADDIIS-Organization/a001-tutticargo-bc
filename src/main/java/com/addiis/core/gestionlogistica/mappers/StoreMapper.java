package com.addiis.core.gestionlogistica.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Zone;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.ZoneRepository;

@Component
public class StoreMapper {
  

  public Store toEntity(StoreRequest request) {

    
    return Store.builder()
        .name(request.getName())
        .code(request.getCode())
        .address(request.getAddress())
        .city(request.getCity())
        .observation(request.getObservation())
        .priority(request.getPriority())
        .ruc(request.getRuc().toString())

        .build();
  }

  public StoreResponse toResponse(Store entity) {
    return StoreResponse.builder()
        .id(entity.getId())
        .name(entity.getName())
        .code(entity.getCode())
        .address(entity.getAddress())
        .city(entity.getCity())
        .observation(entity.getObservation())
        .priority(entity.getPriority())
        .ruc(entity.getRuc())

        .build();
  }
}
