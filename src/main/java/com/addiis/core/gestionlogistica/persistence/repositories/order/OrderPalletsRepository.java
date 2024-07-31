package com.addiis.core.gestionlogistica.persistence.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderPallet;

@Repository
public interface OrderPalletsRepository extends JpaRepository<OrderPallet, Long> {
    OrderPallet findByOrderStoreId(Long orderStoreId);
}