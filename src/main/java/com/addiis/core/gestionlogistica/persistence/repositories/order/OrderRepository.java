package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(BigInteger orderNumber);

    @Query("SELECT o FROM Order o JOIN o.store s JOIN s.route r ORDER BY r.routeNumber ASC")
    Page<Order> findAllOrderedByRoute(Pageable pageable);

    @Query("SELECT o FROM Order o JOIN o.store s  WHERE s.code = :storeCode" )
    List<Order> findByStoreCode(@Param("storeCode") Integer storeCode);
}