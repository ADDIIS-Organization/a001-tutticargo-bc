package com.addiis.core.gestionlogistica.controllers;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletInfo;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderStoreResponse;
import com.addiis.core.gestionlogistica.services.order.OrderPalletsService;
import com.addiis.core.gestionlogistica.services.order.OrderStoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order-stores")
@RequiredArgsConstructor
public class OrderStoreController {
  
  @Autowired
  private final OrderStoreService orderStoreService;

  @Autowired
  private final OrderPalletsService orderPalletService;
  

  @GetMapping
  public ResponseEntity<Page<OrderStoreResponse>> findAll(int page, int size) {
    return ResponseEntity.ok(orderStoreService.findAll(page, size));
  }

  @GetMapping("/byDate/{date}")
  public ResponseEntity<Page<OrderStoreResponse>> findAllOrderByRouteNumber(int page, int size, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return ResponseEntity.ok(orderStoreService.findAllOrderByRouteNumber(page, size, date));
  }

  @GetMapping("/{storeCode}")
  public ResponseEntity<List<OrderStoreResponse>> findByStoreCode(@PathVariable Integer storeCode) {
    return ResponseEntity.ok(orderStoreService.findByStoreCode(storeCode));
  }

   @PatchMapping("/{orderStoreId}/pallets")
   public ResponseEntity<OrderPalletsResponse> updateByOrderId(
   @RequestBody OrderPalletRequest request,
   @PathVariable Long orderStoreId) {
   // Print the orderId and channelId
   System.out.println("Order ID: " + orderStoreId);
   System.out.println("Channel ID: " + request.getChannelId());
   // Print the details of each pallet
   for (OrderPalletInfo pallet : request.getOrderPalletsInfo()) {
   System.out.println("BigPallets: " + pallet.getBigPallets());
   System.out.println("LittlePallets: " + pallet.getLittlePallets());
   System.out.println("DispoId: " + pallet.getDispoId());
   }
   // Update the order pallets using the service
   return ResponseEntity.ok(orderPalletService.updateByOrderStoreId(request,
   orderStoreId));
   }
}
