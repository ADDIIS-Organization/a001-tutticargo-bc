package com.addiis.core.gestionlogistica.persistence.entities.dispatch;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseActiveEntity;
import com.addiis.core.gestionlogistica.persistence.entities.route.Route;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "channels")
public class Channel  extends BaseActiveEntity {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "observation", length = 150)
    private String observation;

    @ManyToOne
    @JoinColumn(name = "platform_id", referencedColumnName = "id")
    private Platform platform;

    @OneToOne(mappedBy = "channel", cascade = CascadeType.ALL)
    private Route route;
}
