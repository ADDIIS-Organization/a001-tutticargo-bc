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

import com.addiis.core.gestionlogistica.domain.dto.request.CediRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.CediResponse;
import com.addiis.core.gestionlogistica.services.warehouse.CediService;

@RestController
@RequestMapping("/cedis")
public class CediController {
  
  @Autowired
  private CediService cediService;

  @GetMapping
  public ResponseEntity<Page<CediResponse>> findAll( @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(cediService.findAll(page, size));
  }
  @PostMapping
  public ResponseEntity<CediResponse> create(@Validated @RequestBody CediRequest request) {
    CediResponse response = cediService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CediResponse> findById(@PathVariable Long id) {
    CediResponse response = cediService.findById(id);
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CediResponse> update(@Validated @RequestBody CediRequest request,
            @PathVariable Long id) {
    CediResponse response = cediService.update(request, id);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    cediService.delete(id);
    return ResponseEntity.noContent().build();
  }
  @PatchMapping("/{id}")
  public ResponseEntity<CediResponse> path(@Validated @RequestBody CediRequest request ,@PathVariable Long id) {
    CediResponse response = cediService.patch(request, id);
    return ResponseEntity.ok(response);
  }

}
