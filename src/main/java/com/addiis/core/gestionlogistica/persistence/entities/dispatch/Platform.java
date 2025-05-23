package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import java.util.Set;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseActiveEntity;
import com.addiis.core.gestionlogistica.persistence.entities.common.BaseAuditEntity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="platforms")
public class Platform extends BaseActiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "observation", length = 150)
    private String observation;

    @OneToMany(mappedBy = "platform")
    private Set<Channel> channels;

    @OneToMany(mappedBy = "platform")
    private Set<Dispatch> dispatches;
}
