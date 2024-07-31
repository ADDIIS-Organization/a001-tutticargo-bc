package com.addiis.core.gestionlogistica.persistence.repositories.order;

import java.math.BigInteger;
import java.util.List;

import org.apache.poi.hpsf.Date;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addiis.core.gestionlogistica.persistence.entities.order.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderNumber(BigInteger orderNumber);

    // @Query("SELECT o \n" + //
    // "FROM Order o \n" + //
    // "JOIN o.store s \n" + //
    // "JOIN s.route r \n" + //
    // "ORDER BY o.date ASC, r.routeNumber ASC")
    // Page<Order> findAllOrderedByRoute(Pageable pageable);

//     Date date = new Date();

//     @Query("SELECT o \n" + //
//             "FROM Order o \n" + //
//             "JOIN o.store s \n" + //
//             "JOIN s.route r \n" + //
//             "ORDER BY o.date ASC, r.routeNumber ASC")
//     Page<Order> findAllOrderedByRoute(Pageable pageable);

//     @Query("SELECT o FROM Order o JOIN o.store s  WHERE s.code = :storeCode")
//     List<Order> findByStoreCode(@Param("storeCode") Integer storeCode);

//     @Query("SELECT s.code as storeCode, " +
//             "       s.name as storeName, " +
//             "       SUM(op.bigPallets) as totalBigPallets, " +
//             "       SUM(op.littlePallets) as totalLittlePallets " +
//             "FROM Order o " +
//             "JOIN o.store s " +
//             "JOIN o.ordersPallets op " +
//             "GROUP BY s.code, s.name " +
//             "ORDER BY s.name ASC")
//     Page<Object[]> findStoreSummary(Pageable pageable);

//     @Query("SELECT s FROM Store s JOIN s.route r")
//     Page<Object[]> findStoresByRoute(Pageable pageable);
}