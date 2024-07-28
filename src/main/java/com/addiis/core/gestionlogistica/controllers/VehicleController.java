package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.response.VehicleResponse;
import com.addiis.core.gestionlogistica.services.vehicle.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
  @Autowired
  private VehicleService vehicleService;

  @GetMapping
  public ResponseEntity<Page<VehicleResponse>> findAll(int page, int size) { 
    return ResponseEntity.ok(vehicleService.findAll(page, size));
  }
}
