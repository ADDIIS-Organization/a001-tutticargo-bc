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
@Table(name="GLOG_MATERIALES", indexes = @Index(columnList = "CDMATERI"))
public class MaterialesEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMATERI")
    private Long idMaterial;

    @NotEmpty
    @Size(max = 60)
    @Column(length = 60, name = "CDMATERI")
    private String codigoMaterial;

    @Size(max = 100)
    @Column(length = 100, name = "DSMATERI")
    private String descripcionMaterial;
}
