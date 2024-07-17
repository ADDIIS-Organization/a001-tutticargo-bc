package com.addiis.core.gestionlogistica.services.impl.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.mappers.StoreMapper;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Zone;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.StoreRepository;
import com.addiis.core.gestionlogistica.services.warehouse.StoreService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {
  @Autowired
  private final StoreMapper storeMapper;

  @Autowired
  private final StoreRepository storeRepository;

  @Override
  public StoreResponse create(StoreRequest request) {
    Store entity = storeMapper.toEntity(request);
    Store storeCreated = storeRepository.save(entity);
    return storeMapper.toResponse(storeCreated);


  }

  @Override
  public void delete(Long id) {
    Store entity = find(id);
    storeRepository.delete(entity);
    
  }

  @Override
  public Page<StoreResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0; 
      
    }
    Pageable pageable = PageRequest.of(page, size);
    return this.storeRepository.findAll(pageable).map(storeMapper::toResponse);
  }

  @Override
  public StoreResponse findById(Long id) {
    Store entity = find(id);
    return storeMapper.toResponse(entity);
    
  }

  @Override
  public StoreResponse update(StoreRequest request, Long id) {
    Store entity = this.find(id);
    Store updateEntity = this.storeMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.storeMapper.toResponse(this.storeRepository.save(updateEntity));
    
  }
  @Override
  public StoreResponse patch ( StoreRequest request,Long id) {
    Store entity = find(id);
    entity.setStatus(0);
    return this.storeMapper.toResponse(this.storeRepository.save(entity));
  }

  private Store find(Long id) { 
    Store entity = storeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Store", id));
    return entity;

  }
  
}
