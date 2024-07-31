package com.addiis.core.gestionlogistica.persistence.entities.warehouse;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseActiveEntity;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="zones")
public class Zone extends BaseActiveEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "code")
    private Integer code;

    @Column(name = "observation", length = 45)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "cedi_id", referencedColumnName = "id")
    private Cedi cedi;

    // @OneToMany(mappedBy = "zone")
    // private Set<Store> stores;
}
