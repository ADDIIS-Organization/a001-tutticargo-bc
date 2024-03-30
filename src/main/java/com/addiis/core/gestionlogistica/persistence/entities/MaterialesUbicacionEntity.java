package com.addiis.core.gestionlogistica.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="GLOG_UBICAMATER")
public class MaterialesUbicacionEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUBIMAT")
    private Long idUbicacionMaterial;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "FKMATERI")
    private MaterialesEntity material;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "FKUBICAC")
    private MaterialesEntity ubicacion;

    @NotEmpty
    @Column(name = "NMCANTID")
    float cantidad;
}
