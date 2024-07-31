package com.addiis.core.gestionlogistica.services.vehicle;

import java.util.List;

import com.addiis.core.gestionlogistica.domain.dto.response.DriverResponse;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Driver;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface DriverService extends CRUDService<Driver, DriverResponse, Long> {
  
List<DriverResponse> listAll();
}
