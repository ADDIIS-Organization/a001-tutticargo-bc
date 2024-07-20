package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.services.warehouse.StoreService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/stores")
@AllArgsConstructor
public class StoreController {
  
  @Autowired
  private final StoreService storeService;

  @GetMapping
  public ResponseEntity<Page<StoreResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    Page<StoreResponse> response = storeService.findAll(page - 1, size);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/custom")
  public ResponseEntity<Page<StoreResponse>> findAllCustom(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    Page<StoreResponse> response = storeService.findAllCustom(page - 1, size);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<StoreResponse> create(@Validated @RequestBody StoreRequest request) {

    StoreResponse response = storeService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @GetMapping("/{id}")
  public ResponseEntity<StoreResponse> findById(@PathVariable Long id) {
    StoreResponse response = storeService.findById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<StoreResponse> update(@Validated @RequestBody StoreRequest request,
            @PathVariable Long id) {
    StoreResponse response = storeService.update(request, id);
    return ResponseEntity.ok(response);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    storeService.delete(id);
    return ResponseEntity.noContent().build();
  }
  @PatchMapping("/{id}/disable")
  public ResponseEntity<StoreResponse> disable(
   @Validated  @RequestBody StoreRequest request , @PathVariable Long id) {
    StoreResponse response = storeService.patch( request ,id);
    return ResponseEntity.ok(response);
  }
}
