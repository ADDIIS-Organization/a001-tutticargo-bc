package com.addiis.core.gestionlogistica.services.vehicle;

import java.util.List;

import com.addiis.core.gestionlogistica.domain.dto.response.VehicleResponse;
import com.addiis.core.gestionlogistica.persistence.entities.vehicle.Vehicle;
import com.addiis.core.gestionlogistica.services.CRUDService;

public interface VehicleService extends CRUDService<Vehicle, VehicleResponse, Long> {
  List<VehicleResponse> listAll();
}
