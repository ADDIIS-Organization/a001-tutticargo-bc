package com.addiis.core.gestionlogistica.persistence.entities;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * Entity representing the persistence of application parameters.
 * This entity stores generic parameters that can be used to store arbitrary
 * data.
 * The parameters are stored as a code and a set of fields to store different
 * types of data.
 * 
 * @author Nicolás Picón Jaimes.
 * @version 1.0.0
 * @since 2024-05-04
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "app_parameters", indexes = {@Index(columnList = "parameter_code")})
public class Parameter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Size(max = 60)
    @Column(length = 60, name = "parameter_code")
    private String parameterCode;

    @Size(max = 100)
    @Column(length = 100, name = "text1")
    private String text1;

    @Size(max = 100)
    @Column(length = 100, name = "text2")
    private String text2;

    @Column(name = "date1")
    private Date date1;

    @Column(name = "date2")
    private Date date2;

    @Column(name = "number1")
    private int number1;

    @Column(name = "number2")
    private int number2;

}
