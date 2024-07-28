package com.addiis.core.gestionlogistica.services.impl.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.UnitOfMeasureRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UnitOfMeasureResponse;
import com.addiis.core.gestionlogistica.mappers.UnitOfMeasureMapper;
import com.addiis.core.gestionlogistica.persistence.entities.product.UnitOfMeasure;
import com.addiis.core.gestionlogistica.persistence.repositories.products.UnitOfMeasureRepository;
import com.addiis.core.gestionlogistica.services.product.UnitOfMeasureService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
  @Autowired
  private final UnitOfMeasureMapper unitOfMeasureMapper;
  @Autowired
  private final UnitOfMeasureRepository unitOfMeasureRepository;
  
  @Override
  public UnitOfMeasureResponse create(UnitOfMeasureRequest request) {
    UnitOfMeasure entity = unitOfMeasureMapper.toEntity(request);
    return this.unitOfMeasureMapper.toResponse(this.unitOfMeasureRepository.save(entity));
  }

  @Override
  public void delete(Long id) {
    UnitOfMeasure entity = find(id);
    this.unitOfMeasureRepository.delete(entity);
    
  }

  @Override
  public Page<UnitOfMeasureResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;
    }
    Pageable pageable = PageRequest.of(page, size);
    return this.unitOfMeasureRepository.findAll(pageable).map(unitOfMeasureMapper::toResponse); 
  }

  @Override
  public UnitOfMeasureResponse findById(Long id) {
    
    return this.unitOfMeasureMapper.toResponse(this.find(id));
  }

  @Override
  public UnitOfMeasureResponse update(UnitOfMeasureRequest request, Long id) {
    UnitOfMeasure entity = this.find(id);
    UnitOfMeasure updateEntity = this.unitOfMeasureMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.unitOfMeasureMapper.toResponse(this.unitOfMeasureRepository.save(updateEntity));

  }
  @Override
  public UnitOfMeasureResponse patch (UnitOfMeasureRequest request , Long id) {
    UnitOfMeasure entity = find(id);
    entity.setActive(!entity.isActive());
    return this.unitOfMeasureMapper.toResponse(this.unitOfMeasureRepository.save(entity));
  }

  private UnitOfMeasure find(Long id) {
    UnitOfMeasure entity = unitOfMeasureRepository.findById(id).orElseThrow(() -> new IdNotFoundException("UnitOfMeasure", id));
    return entity;
  }

  
}
