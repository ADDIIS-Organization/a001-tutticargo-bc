package com.addiis.core.gestionlogistica.services.impl.dispatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.DispatchRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchResponse;
import com.addiis.core.gestionlogistica.mappers.DispatchMapper;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.persistence.repositories.dispatch.DispatchRepository;
import com.addiis.core.gestionlogistica.services.dispatch.DispatchService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DispatchServiceImpl implements DispatchService {

  @Autowired
  private final DispatchRepository dispatchRepository;

  @Autowired
  private final DispatchMapper dispatchMapper;

  @Override
  public DispatchResponse create(DispatchRequest request) {
    Dispatch entity = dispatchMapper.toEntity(request);
    entity.setDate(LocalDate.now());
    Dispatch dispatchCreated = dispatchRepository.save(entity);
    return dispatchMapper.toResponse(dispatchCreated);

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public Page<DispatchResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;

    }

    Pageable pageable = PageRequest.of(page, size);
    return this.dispatchRepository.findAll(pageable).map(dispatchMapper::toResponse);
  }

  @Override
  public Page<DispatchResponse> findDispatchesByDate(LocalDate dispatchDate , int page, int size) {
    if (page < 0) {
      page = 0;

    }

    Pageable pageable = PageRequest.of(page - 1, size);
    return this.dispatchRepository.findDispatchesByDate(dispatchDate, pageable).map(dispatchMapper::toResponse);
  
   
  }

  @Override
  public DispatchResponse findById(Long id) {

    return null;
  }

  @Override
  public DispatchResponse patch(DispatchRequest request, Long id) {

    return null;
  }

  @Override
  public DispatchResponse update(DispatchRequest request, Long id) {

    return null;
  }

}
