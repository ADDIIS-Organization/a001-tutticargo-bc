package com.addiis.core.gestionlogistica.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
// @Builder
public class StoreResponse {
  private Long id;
  private String name;
  private String code;
  private String address;
  private String city;
  private String observation;
  private Integer priority;
  private String ruc;
  private Long routeId;
  private String routeName;
  private Long channelId;
  private String channelName;
  private Long platformId;
  private String platformName;

}
