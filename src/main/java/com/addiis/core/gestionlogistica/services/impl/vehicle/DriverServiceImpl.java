package com.addiis.core.gestionlogistica.services.impl.vehicle;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.response.DriverResponse;
import com.addiis.core.gestionlogistica.mappers.DriverMapper;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.persistence.repositories.vehicles.DriverRepository;
import com.addiis.core.gestionlogistica.services.vehicle.DriverService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl  implements DriverService {

  @Autowired
  private final DriverRepository driverRepository;

  @Autowired
  private final DriverMapper driverMapper;

  @Override
  public DriverResponse create(Driver request) {

    return null;
  }

  @Override
  public void delete(Long id) {

    
  }

  @Override
  public Page<DriverResponse> findAll(int page, int size) {

    return null;
  }
@Override
  public List<DriverResponse> listAll(){
    return driverRepository.findAll().stream().map(driverMapper::toResponse).collect(Collectors.toList());
  }

  @Override
  public DriverResponse findById(Long id) {

    return null;
  }

  @Override
  public DriverResponse patch(Driver request, Long id) {

    return null;
  }

  @Override
  public DriverResponse update(Driver request, Long id) {

    return null;
  }
  
}
