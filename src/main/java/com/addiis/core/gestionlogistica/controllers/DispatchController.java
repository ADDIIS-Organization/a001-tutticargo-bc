package com.addiis.core.gestionlogistica.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.request.DispatchRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.DispatchResponse;
import com.addiis.core.gestionlogistica.services.dispatch.DispatchService;

@RestController
@RequestMapping("/dispatches")
public class DispatchController {
   

  @Autowired
  private DispatchService dispatchService;

  @GetMapping
  public ResponseEntity<Page<DispatchResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(dispatchService.findAll(page, size));
  }

  @PostMapping
  public ResponseEntity<DispatchResponse> create(@Validated @RequestBody DispatchRequest request) {
    DispatchResponse response = dispatchService.create(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/{dispatchDate}")
  public ResponseEntity<Page<DispatchResponse>> findDispatchesByDate( @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dispatchDate, @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
    return ResponseEntity.ok(dispatchService.findDispatchesByDate(dispatchDate, page, size));
  }
}
