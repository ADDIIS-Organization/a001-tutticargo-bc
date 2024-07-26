package com.addiis.core.gestionlogistica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.addiis.core.gestionlogistica.domain.dto.request.OrderPalletRequest;
import com.addiis.core.gestionlogistica.domain.dto.request.PalletAttributes;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderPalletsResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderResponse;
import com.addiis.core.gestionlogistica.services.order.OrderPalletsService;
import com.addiis.core.gestionlogistica.services.order.OrderService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderPalletsService orderPalletService;

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> findAll(@RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(orderService.findAll(page, size));
    }

    @PatchMapping("/{orderId}/pallets")
    public ResponseEntity<OrderPalletsResponse> updateByOrderId(
            @RequestBody List<PalletAttributes> request,
            @PathVariable Long orderId) {
        // Let's print the id and all the request
        System.out.println("Order ID: " + orderId);
        for (PalletAttributes pallet : request) {
            System.out.println("BigPallets: " + pallet.getBigPallets());
            System.out.println("LittlePallets: " + pallet.getLittlePallets());
            System.out.println("DispoId: " + pallet.getDispoId());
        }
        return ResponseEntity.ok(orderPalletService.updateByOrderId(request, orderId));
    }

    @GetMapping("/{storeCode}")
    public ResponseEntity<List<OrderResponse>> findByStoreCode(@PathVariable Integer storeCode) {
        return ResponseEntity.ok(orderService.findByStoreCode(storeCode));
    }

}
