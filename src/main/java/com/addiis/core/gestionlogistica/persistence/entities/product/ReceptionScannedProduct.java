package com.addiis.core.gestionlogistica.persistence.entities.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.addiis.core.gestionlogistica.persistence.entities.common.BaseAuditEntity;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.WarehouseLocation;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="reception_scanned_products")
public class ReceptionScannedProduct extends BaseAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull // Es mas adecuado usar @NotNull en lugar de @NotEmpty para campos de tipo LocalDate
    @Column(name = "expiration_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Ensures the date is in YYYY-MM-DD format
    private LocalDate expirationDate;

    @Column(name = "manufacture_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Ensures the date is in YYYY-MM-DD format
    private LocalDate manufactureDate;

    @Column(name = "useful_life")
    private Integer usefulLife;

    @Column(name = "lot")
    private String lot;
    @Column(name = "amount_received")
    private Integer amountReceived;

    @Column(name = "reception_percentage")
    @Min(0)
    @Max(100)
    private Integer receptionPercentage;

    @ManyToOne
    @JoinColumn(name = "warehouse_locations_id", referencedColumnName = "id")
    @JsonBackReference
    private WarehouseLocation warehouseLocation;

    
}
