package com.addiis.core.gestionlogistica.domain.dto.response;

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
public class StoreResponse {
  private Long id;
  private String name;
  private Integer code;
  private String address;
  private String city;
  private String observation;
  private String priority;
  private String ruc;
  private Long zoneId;
}
