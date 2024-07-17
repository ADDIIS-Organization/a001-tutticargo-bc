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

import com.addiis.core.gestionlogistica.domain.dto.request.UnitOfMeasureRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.UnitOfMeasureResponse;
import com.addiis.core.gestionlogistica.services.product.UnitOfMeasureService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/unit-of-measure")
@RequiredArgsConstructor
public class UnitOfMeasureController {
  
  @Autowired
  private final UnitOfMeasureService unitOfMeasureService;

  @GetMapping
  public ResponseEntity<Page<UnitOfMeasureResponse>> findAll( @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(this.unitOfMeasureService.findAll(page, size));
  }
  @PostMapping
  public ResponseEntity<UnitOfMeasureResponse> create(@Validated @RequestBody UnitOfMeasureRequest request) {
    UnitOfMeasureResponse response = unitOfMeasureService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UnitOfMeasureResponse> findById(@PathVariable Long id) {
    UnitOfMeasureResponse response = unitOfMeasureService.findById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UnitOfMeasureResponse> update(@Validated @RequestBody UnitOfMeasureRequest request,
            @PathVariable Long id) {
    UnitOfMeasureResponse response = unitOfMeasureService.update(request, id);
    return ResponseEntity.ok(response);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    unitOfMeasureService.delete(id);
    return ResponseEntity.noContent().build();
  }
  @PatchMapping("/{id}/disable")
  public ResponseEntity<UnitOfMeasureResponse> patch(@Validated @RequestBody UnitOfMeasureRequest request , @PathVariable Long id) {
    UnitOfMeasureResponse response = unitOfMeasureService.patch(request, id);
    return ResponseEntity.ok(response);
  }


}
