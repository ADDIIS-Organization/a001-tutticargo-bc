package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.services.order.OrderPalletsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-pallets")
@RequiredArgsConstructor
public class OrderPalletsController {
  @Autowired
  private final OrderPalletsService orderPalletService;

  @PostMapping
  public ResponseEntity<OrderPalletsResponse> create(@RequestBody OrderPalletRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(orderPalletService.save(request));
  }

  // @PutMapping("/{orderId}/order")
  // public ResponseEntity<OrderPalletsResponse> updateByOrderId(
  //     @RequestBody List<PalletAttributes> request,
  //     @PathVariable Long orderId) {
  //   return ResponseEntity.ok(orderPalletService.updateByOrderId(request, orderId));
  // }
}
