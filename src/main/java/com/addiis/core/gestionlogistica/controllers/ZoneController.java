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

import com.addiis.core.gestionlogistica.domain.dto.request.ZoneRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.ZoneResponse;
import com.addiis.core.gestionlogistica.services.warehouse.ZoneService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/zones")
@AllArgsConstructor
public class ZoneController {
  
 @Autowired
 private final ZoneService zoneService;

 @GetMapping
 public ResponseEntity<Page<ZoneResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {


  return ResponseEntity.ok(this.zoneService.findAll(page,size));
 }

 @PostMapping
 public ResponseEntity<ZoneResponse> create(@Validated @RequestBody ZoneRequest request) {
   ZoneResponse response = zoneService.create(request);
   return ResponseEntity.status(HttpStatus.CREATED).body(response);
 }

 @GetMapping("/{id}")
 public ResponseEntity<ZoneResponse> findById(@PathVariable Long id) {
   ZoneResponse response = zoneService.findById(id);
   return ResponseEntity.ok(response);
 }

 @PutMapping("/{id}")
 public ResponseEntity<ZoneResponse> update(@Validated @RequestBody ZoneRequest request,
            @PathVariable Long id) {
   ZoneResponse response = zoneService.update(request, id);
   return ResponseEntity.ok(response);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<Void> delete(@PathVariable Long id) {
   zoneService.delete(id);
   return ResponseEntity.noContent().build();
 }
 @PatchMapping("/{id}/disable")
 public ResponseEntity<ZoneResponse> patch(@Validated @RequestBody ZoneRequest request ,@PathVariable Long id) {
   ZoneResponse response = zoneService.patch(request,id);
   return ResponseEntity.ok(response);
 }
}
