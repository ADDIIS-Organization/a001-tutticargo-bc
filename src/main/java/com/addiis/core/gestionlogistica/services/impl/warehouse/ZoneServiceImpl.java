package com.addiis.core.gestionlogistica.services.impl.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.ZoneRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ZoneResponse;
import com.addiis.core.gestionlogistica.mappers.ZoneMapper;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Zone;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.ZoneRepository;
import com.addiis.core.gestionlogistica.services.warehouse.ZoneService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ZoneServiceImpl implements ZoneService {
  @Autowired
  private final ZoneMapper zoneMapper;

  @Autowired
  private final ZoneRepository zoneRepository;
  
  @Override
  public ZoneResponse create(ZoneRequest request) {
    Zone entity = zoneMapper.toEntity(request);

     return this.zoneMapper.toResponse(this.zoneRepository.save(entity));
  }

  @Override
  public void delete(Long id) {
    Zone entity = find(id);
    this.zoneRepository.delete(entity); 
    
  }

  @Override
  public Page<ZoneResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;
      
    }

    Pageable pageable = PageRequest.of(page, size);
    return this.zoneRepository.findAll(pageable).map(zoneMapper::toResponse);
  }

  @Override
  public ZoneResponse findById(Long id) {
    
    return this.zoneMapper.toResponse(this.find(id));
  }

  @Override
  public ZoneResponse update(ZoneRequest request, Long id) {
    Zone entity = this.find(id);
    Zone updateEntity = this.zoneMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.zoneMapper.toResponse(this.zoneRepository.save(updateEntity));

  }
  @Override
  public ZoneResponse patch (ZoneRequest request , Long id) {
    Zone entity = find(id);
    entity.setActive(!entity.isActive());
    return this.zoneMapper.toResponse(this.zoneRepository.save(entity));
  }

  private Zone find(Long id) {
    Zone entity = zoneRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Zone", id));
    return entity;
  }
}
