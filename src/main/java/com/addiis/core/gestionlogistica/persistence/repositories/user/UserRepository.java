package com.addiis.core.gestionlogistica.persistence.repositories.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.addiis.core.gestionlogistica.persistence.entities.user.User;

public interface UserRepository extends  JpaRepository<User, Long> { 
  Optional<User> findByUserName(String userName);
}
