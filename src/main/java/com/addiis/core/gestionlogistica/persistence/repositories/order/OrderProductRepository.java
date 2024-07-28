package com.addiis.core.gestionlogistica.persistence.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderProduct;
import com.addiis.core.gestionlogistica.persistence.entities.order.OrderProductId;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {}
