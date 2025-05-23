package com.addiis.core.gestionlogistica.persistence.repositories.dispatch;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.addiis.core.gestionlogistica.persistence.entities.dispatch.Dispatch;

public interface DispatchRepository extends JpaRepository<Dispatch, Long> {
  @Query("SELECT d FROM Dispatch d JOIN FETCH d.driver WHERE d.date = :date ORDER BY d.platform")
  Page<Dispatch> findDispatchesByDate(@Param("date") LocalDate date , Pageable pageable);

  Optional<Dispatch> findByOrderStoreId(Long id);
}
