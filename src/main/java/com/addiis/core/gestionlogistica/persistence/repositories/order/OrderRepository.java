package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(BigInteger orderNumber);
}

/**
 * SELECT * FROM orders o 
 *  INNER JOIN stores s ON o.stores_id = s.id 
 *  INNER JOIN routes r ON o.routes_id = r.id 
 *  INNER JOIN orders_pallets op ON o.id = op.orders_id;  
 */