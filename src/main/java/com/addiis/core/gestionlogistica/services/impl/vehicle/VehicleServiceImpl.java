package com.addiis.core.gestionlogistica.services.impl.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.response.VehicleResponse;
import com.addiis.core.gestionlogistica.mappers.VehicleMapper;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;
import com.addiis.core.gestionlogistica.persistence.repositories.vehicles.VehicleRepository;
import com.addiis.core.gestionlogistica.services.vehicle.VehicleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
 @Autowired
 private final VehicleRepository vehicleRepository;
 @Autowired
 private final VehicleMapper vehicleMapper;

 @Override
  public VehicleResponse create(Vehicle request) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Page<VehicleResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;
    }
    Pageable pageable = PageRequest.of(page, size);
    return this.vehicleRepository.findAll(pageable).map(vehicleMapper::toResponse);
    
  }

  @Override
  public VehicleResponse findById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VehicleResponse patch(Vehicle request, Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VehicleResponse update(Vehicle request, Long id) {
    // TODO Auto-generated method stub
    return null;
  }
  
}
