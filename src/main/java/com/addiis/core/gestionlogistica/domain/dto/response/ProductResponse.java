package com.addiis.core.gestionlogistica.domain.dto.response;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductResponse {
  private Long id;
  private String name;
  private BigInteger code;
  private BigInteger ean;
  private String uxc;
  private String cxp;
  private String observation;
}
