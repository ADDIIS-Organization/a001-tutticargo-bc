package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="dispatch_status")
public class DispatchStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "status", length = 45)
    private String status;

    @OneToMany(mappedBy = "dispatchState")
    private Set<DispatchHistory> dispatchHistories;
}
