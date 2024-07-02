package com.addiis.core.gestionlogistica.persistence.entities.vehicle;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "document", length = 45)
    private String document;

    @Column(name = "active")
    private Integer active;

    @OneToMany(mappedBy = "driver")
    private Set<VehicleDriver> vehicleDrivers;
}
