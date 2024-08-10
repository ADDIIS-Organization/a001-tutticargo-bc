package com.addiis.core.gestionlogistica.persistence.entities.user;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.addiis.core.gestionlogistica.utils.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails{
   
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
 
  @Column(length = 60 , nullable = false , unique = true)
  private String userName; 
  @Column(length = 60 , nullable = false)
  private String password;
 
  @Enumerated(EnumType.STRING)
  private Role role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    
    return List.of(new SimpleGrantedAuthority(this.role.name()));
  }

  @Override
  public String getUsername() {
    
    return this.userName;
  }

  @Override
  public boolean isAccountNonExpired() {
    
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    
    return true;
  }

  @Override
  public boolean isEnabled() {
    
    return true;
  }
}
