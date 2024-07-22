package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(BigInteger orderNumber);
}