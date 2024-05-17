package com.addiis.core.gestionlogistica.persistence.entities;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "last_scanned_products")
@Getter
@Setter
public class LastScannedProduct extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_location_id", referencedColumnName = "id")
    private ProductLocation productLocation;

    @NotNull // Es mas adecuado usar @NotNull en lugar de @NotEmpty para campos de tipo LocalDate
    @Column(name = "expiration_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Ensures the date is in YYYY-MM-DD format
    private LocalDate expirationDate;

    @Column(name = "manufacture_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // Ensures the date is in YYYY-MM-DD format
    private LocalDate manufactureDate;
}
