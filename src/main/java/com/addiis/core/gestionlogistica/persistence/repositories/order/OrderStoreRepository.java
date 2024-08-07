package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addiis.core.gestionlogistica.persistence.entities.order.OrderStore;

public interface OrderStoreRepository extends JpaRepository<OrderStore, Long> {
  @Query("SELECT o FROM OrderStore o JOIN o.store s JOIN s.route r  WHERE DATE(o.date) = :date ORDER BY r.routeNumber ASC")
    Page<OrderStore> findAllOrderByRouteNumber(Pageable pageable , @Param("date")  LocalDate date);

     @Query("SELECT o FROM OrderStore o JOIN o.store s WHERE s.code = :storeCode")
     List<OrderStore> findByStoreCode(@Param("storeCode") Integer storeCode);

}