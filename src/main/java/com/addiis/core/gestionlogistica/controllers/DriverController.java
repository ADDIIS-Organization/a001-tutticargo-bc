package com.addiis.core.gestionlogistica.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.response.DriverResponse;
import com.addiis.core.gestionlogistica.services.vehicle.DriverService;

@RestController
@RequestMapping("/drivers")
public class DriverController {
  @Autowired
  private DriverService driverService;

  @GetMapping
  public ResponseEntity<List<DriverResponse>> listAll() {
    return ResponseEntity.ok(driverService.listAll());
  }
}
