package com.addiis.core.gestionlogistica.services.impl.order;

import java.math.BigInteger;
import java.sql.Timestamp; // Asegúrate de importar el tipo correcto

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderResponse;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Channel;
import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Platform;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.entities.route.Route;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderRepository;
import com.addiis.core.gestionlogistica.services.order.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Page<OrderResponse> findAll(int page, int size) {
        if (page < 0) {
            page = 0;
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        AddiisLogger.info("pageRequest: " + pageRequest);
        try {
            // Llamar al método del repositorio que ordena por routeNumber
            Page<Order> orders = orderRepository.findAllOrderedByRoute(pageRequest);
            AddiisLogger.info("Orders: " + orders);
            return orders.map(this::convertToOrderResponse);
        } catch (Exception e) {
            AddiisLogger.error("Error getting orders", this.getClass().getName(), "findAll", e.getMessage());
            throw e; // Rethrow the exception after logging it
        }
    }

    @Override
    public List<OrderResponse> findByStoreCode(Integer storeCode) {
        try{
            return orderRepository.findByStoreCode(storeCode).stream().map(this::convertToOrderResponse).collect(Collectors.toList());
        }
        catch (Exception e) {
            AddiisLogger.error("Error getting orders", this.getClass().getName(), "findByStoreCode", e.getMessage());
            throw e; // Rethrow the exception after logging it
        }
    }

private OrderResponse convertToOrderResponse(Order order) {
    Integer storeCode = order.getStore().getCode();
    Route route = order.getStore().getRoute();
    String routeName = null;
    if (route != null) {
        routeName = route.getRouteNumber();
    }
    BigInteger orderNumber = order.getOrderNumber();
    Timestamp date = order.getDate();
    
    // Obtener el canal y la plataforma de forma segura
    Store store = order.getStore();
    Channel channel = store != null ? store.getChannel() : null;
    String channelNumber = channel != null ? channel.getNumber() : null;
    Platform platform = channel != null ? channel.getPlatform() : null;
    String platformNumber = platform != null ? platform.getNumber() : null;
    
    String storeName = store != null ? store.getName() : null;
    Set<OrderPallet> ordersPallets = order.getOrdersPallets();
    AddiisLogger.info("ordersPallets: " + ordersPallets);

    // Si ordersPallets es null o está vacío, inicializa a un conjunto vacío
    if (ordersPallets == null || ordersPallets.isEmpty()) {
        // Crear un array de pallets predeterminados
        List<OrderPallet> defaultPallets = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            OrderPallet pallet = new OrderPallet();
            pallet.setLittlePallets(0);
            pallet.setBigPallets(0);
            pallet.setDispoId(i);
            defaultPallets.add(pallet);
        }
        ordersPallets = new HashSet<>(defaultPallets); // Convertir la lista a un conjunto
    }

    // Calcular bigPallets y littlePallets
    Integer bigPallets = ordersPallets.stream().mapToInt(OrderPallet::getBigPallets).sum();
    Integer littlePallets = ordersPallets.stream().mapToInt(OrderPallet::getLittlePallets).sum();
    Integer totalPalletsNumber = bigPallets + littlePallets;

    return OrderResponse.builder()
            .id(order.getId())
            .orderNumber(orderNumber)
            .storeCode(storeCode)
            .routeName(routeName)
            .channelNumber(channelNumber)
            .storeName(storeName)
            .date(date)
            .ordersPallets(ordersPallets)
            .bigPallets(bigPallets)
            .littlePallets(littlePallets)
            .totalPalletsNumber(totalPalletsNumber)
            .platformNumber(platformNumber)
            .build();
}


    @Override
    public OrderResponse create(OrderRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public OrderResponse update(OrderRequest request, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public OrderResponse findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public OrderResponse patch(OrderRequest request, Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'patch'");
    }

}
