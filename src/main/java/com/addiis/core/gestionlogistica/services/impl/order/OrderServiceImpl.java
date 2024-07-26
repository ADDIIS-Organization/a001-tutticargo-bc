package com.addiis.core.gestionlogistica.services.impl.order;

import java.math.BigInteger;
import java.sql.Timestamp; // Asegúrate de importar el tipo correcto

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.addiis.core.gestionlogistica.config.AddiisLogger;
import com.addiis.core.gestionlogistica.domain.dto.request.OrderRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.OrderResponse;
import com.addiis.core.gestionlogistica.persistence.entities.order.Order;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;
import com.addiis.core.gestionlogistica.persistence.entities.route.Route;
import com.addiis.core.gestionlogistica.persistence.repositories.order.OrderRepository;
import com.addiis.core.gestionlogistica.services.order.OrderService;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
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
        if (order.getStore().getRoute() != null) {
            routeName = route.getRouteNumber();
        }
        BigInteger orderNumber = order.getOrderNumber();
        Timestamp date = order.getDate();
        String channelNumber = order.getStore().getChannel() != null ? order.getStore().getChannel().getNumber()
                : null;
        String platformNumber = order.getStore().getChannel().getPlatform() != null ? order.getStore().getChannel().getPlatform().getNumber() : null;
        String storeName = order.getStore().getName();
        Set<OrderPallet> ordersPallets = order.getOrdersPallets();
        AddiisLogger.info("ordersPallets: " + ordersPallets);

        // Si ordersPallets es null o está vacío, inicializa a un conjunto vacío para
        // evitar NullPointerException
        if (ordersPallets == null) {
            ordersPallets = Collections.emptySet();
        }

        Integer bigPallets = ordersPallets.isEmpty() ? 0
                : ordersPallets.stream().mapToInt(OrderPallet::getBigPallets).sum();
        Integer littlePallets = ordersPallets.isEmpty() ? 0
                : ordersPallets.stream().mapToInt(OrderPallet::getLittlePallets).sum();
        Integer totalPalletsNumber = bigPallets + littlePallets;

        // return new OrderResponse(
        //         order.getId(),
        //         orderNumber,
        //         storeCode,
        //         routeName,
        //         channelNumber,
        //         storeName,
        //         date,
        //         ordersPallets,
        //         bigPallets,
        //         littlePallets,
        //         totalPalletsNumber);
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
