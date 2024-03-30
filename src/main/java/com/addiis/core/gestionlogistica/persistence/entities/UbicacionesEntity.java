package com.addiis.core.gestionlogistica.persistence.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



/**
 * Entidad que representa la persistencia del dato maestro de ubicaciones
 *
 * @author Arenas Silva, Juan
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name="GLOG_UBICACIONES", indexes = @Index(columnList = "CDUBICAC"))
public class UbicacionesEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUBICAC")
    private Long idUbicacion;

    @NotEmpty
    @Size(max = 60)
    @Column(length = 60, name = "CDUBICAC")
    private String codigoUbicacion;

    @Size(max = 100)
    @Column(length = 100, name = "DSUBICAC")
    private String descripcionUbicacion;

    @Size(max = 40)
    @Column(length = 40, name = "DSSECTOR")
    private String sector;

    @Size(max = 40)
    @Column(length = 40, name = "DSESPACIO")
    private String espacio;

}