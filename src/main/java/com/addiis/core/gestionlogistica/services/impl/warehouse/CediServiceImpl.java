package com.addiis.core.gestionlogistica.services.impl.warehouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.addiis.core.gestionlogistica.domain.dto.request.CediRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.CediResponse;
import com.addiis.core.gestionlogistica.mappers.CediMapper;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Cedi;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.CediRepository;
import com.addiis.core.gestionlogistica.services.warehouse.CediService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;
@Component
public class CediServiceImpl  implements CediService {
  @Autowired
  private CediRepository cediRepository;
  @Autowired
  private CediMapper cediMapper;

  @Override
  public CediResponse create(CediRequest request) {
    Cedi entity = cediMapper.toEntity(request);
    Cedi cediCreated = cediRepository.save(entity);
    return cediMapper.toResponse(cediCreated);

  }

  @Override
  public void delete(Long id) {
    Cedi entity = find(id);
    cediRepository.delete(entity);
    
  }

  @Override
  public Page<CediResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;
      
    }
    Pageable pageable = PageRequest.of(page, size);
    return this.cediRepository.findAll(pageable).map(cediMapper::toResponse);
  }

  @Override
  public CediResponse findById(Long id) {
    
    return this.cediMapper.toResponse(this.find(id));
  }

  @Override
  public CediResponse update(CediRequest request, Long id) {
    Cedi entity = this.find(id);
    Cedi updateEntity = this.cediMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.cediMapper.toResponse(this.cediRepository.save(updateEntity));

  }
  @Override
  public CediResponse patch (CediRequest request ,Long id) {
    Cedi entity = find(id);
    
    entity.setStatus(0);
    return this.cediMapper.toResponse(this.cediRepository.save(entity));
  }


  
  private Cedi find(Long id) {
    Cedi entity = cediRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Cedi", id));
    return entity;
  }
  
}
