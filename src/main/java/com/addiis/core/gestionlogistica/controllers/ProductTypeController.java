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

import com.addiis.core.gestionlogistica.domain.dto.request.ProductTypeRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductTypeResponse;
import com.addiis.core.gestionlogistica.services.product.ProductTypeService;

@RestController
@RequestMapping("/product-types")
public class ProductTypeController {
  
  @Autowired 
  private ProductTypeService productTypeService;

  @GetMapping
  public ResponseEntity<Page<ProductTypeResponse>> findAll( @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
     Page<ProductTypeResponse> response = productTypeService.findAll(page - 1, size);
     return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<ProductTypeResponse> create(@Validated @RequestBody ProductTypeRequest request) {

    ProductTypeResponse response = productTypeService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }
  @GetMapping("/{id}")
  public ResponseEntity<ProductTypeResponse> findById(@PathVariable Long id) {
    ProductTypeResponse response = productTypeService.findById(id);
    return ResponseEntity.ok(response);
  }
  @PutMapping("/{id}")
  public ResponseEntity<ProductTypeResponse> update(@Validated @RequestBody ProductTypeRequest request,
            @PathVariable Long id) {
    ProductTypeResponse response = productTypeService.update(request, id);
    return ResponseEntity.ok(response);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    productTypeService.delete(id);
    return ResponseEntity.noContent().build();
  }
  @PatchMapping("/{id}/disable")
  public ResponseEntity<ProductTypeResponse> disable(@Validated @RequestBody ProductTypeRequest request ,@PathVariable Long id) {
    ProductTypeResponse response = productTypeService.patch(request ,id);
    return ResponseEntity.ok(response);
  }
}

