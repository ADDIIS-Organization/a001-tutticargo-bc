package com.addiis.core.gestionlogistica.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.addiis.core.gestionlogistica.persistence.entities.ParametrosEntity;


@Repository
public interface ParametrosRepository extends JpaRepository<ParametrosEntity, Long>{
	
	Optional<ParametrosEntity> findByParametro(String parametro);

}
