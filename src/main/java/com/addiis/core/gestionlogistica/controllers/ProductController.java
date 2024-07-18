package com.addiis.core.gestionlogistica.controllers;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.addiis.core.gestionlogistica.annotation.ApiResponseWrapper;
import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.ProductRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductLocationResponseDTO;
import com.addiis.core.gestionlogistica.domain.dto.response.ProductResponse;
import com.addiis.core.gestionlogistica.persistence.entities.product.Product;
import com.addiis.core.gestionlogistica.services.product.ProductService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{ean}")
    @ApiResponseWrapper
    public ResponseEntity<?> getproductBySku(@PathVariable BigInteger ean) {
        AddiisLogger.info("Requesting product location for sku: " + ean);
        if (ean == null || ean.toString().length() <= 0) {
            return ResponseEntity.badRequest().body("Invalid ean provided"); // Just return the message
        }
        try {
            ProductLocationResponseDTO productLocation = productService.getByEan(ean);
            return ResponseEntity.ok(productLocation);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findAll( @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ProductResponse> response = productService.findAll(page - 1, size);
        return ResponseEntity.ok(response);
    }
     @PostMapping
     public ResponseEntity<?> create(@Validated @RequestBody ProductRequest request) {
          productService.save(request);
         return ResponseEntity.ok().build();
     }



    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id) {
        Optional<Product> response = productService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@Validated @RequestBody ProductRequest request,
            @PathVariable Long id) {
        ProductResponse response = productService.update(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/is-active/{id}")
    public ResponseEntity<ProductResponse> disable(@PathVariable Long id) {
        ProductResponse response = productService.disableProduct(id);
        return ResponseEntity.ok(response);
    }
}
