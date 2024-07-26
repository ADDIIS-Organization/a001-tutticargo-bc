package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(BigInteger orderNumber);

    @Query("SELECT o FROM Order o JOIN o.store s JOIN s.route r ORDER BY r.routeNumber ASC" )
    Page<Order> findAllOrderedByRoute(Pageable pageable);
}