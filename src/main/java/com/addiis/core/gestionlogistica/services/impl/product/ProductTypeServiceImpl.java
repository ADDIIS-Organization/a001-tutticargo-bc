package com.addiis.core.gestionlogistica.services.impl.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.ProductTypeRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductTypeResponse;
import com.addiis.core.gestionlogistica.mappers.ProductTypeMapper;
import com.addiis.core.gestionlogistica.persistence.entities.product.ProductType;
import com.addiis.core.gestionlogistica.persistence.repositories.products.ProductTypeRepository;
import com.addiis.core.gestionlogistica.services.product.ProductTypeService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {
  @Autowired
  private ProductTypeRepository productTypeRepository;
  @Autowired
  private ProductTypeMapper productTypeMapper;
  
  @Override
  public ProductTypeResponse create(ProductTypeRequest request) {
     ProductType entity = productTypeMapper.toEntity(request);
      
    return productTypeMapper.toResponse(this.productTypeRepository.save(entity));
  }

  @Override
  public void delete(Long id) {
    ProductType entity = find(id);
    
    
    
    this.productTypeRepository.delete(entity);
    
  }

  @Override
  public Page<ProductTypeResponse> findAll( int page, int size) {
    if (page < 0) {
      page = 0;
    }
    Pageable pageable = PageRequest.of(page, size);
    
    return this.productTypeRepository.findAll(pageable).map(productTypeMapper::toResponse);
  }

  @Override
  public ProductTypeResponse findById(Long id) {
    
    return this.productTypeMapper.toResponse(this.find(id));
  }

  @Override
  public ProductTypeResponse update(ProductTypeRequest request ,Long id) {
    ProductType entity = this.find(id);
    ProductType updateEntity = this.productTypeMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.productTypeMapper.toResponse(this.productTypeRepository.save(updateEntity));
  }
  @Override
  public ProductTypeResponse patch (ProductTypeRequest request , Long id) {
    ProductType entity = find(id);
    entity.setActive(!entity.isActive());
    return this.productTypeMapper.toResponse(this.productTypeRepository.save(entity));
  }

  private ProductType find(Long id) {
    ProductType entity = productTypeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("ProductType", id));
    return entity;
  }
  
}
