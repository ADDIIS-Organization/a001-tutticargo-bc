package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseStatusEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="cedi")
public class Cedi extends BaseStatusEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;
    
    @Column(name = "code")
    private Integer code;

    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "observation", length = 150)
    private String observation;

    @OneToMany(mappedBy = "cedi")
    private Set<Zone> zones;
}
