package com.addiis.core.gestionlogistica.domain.dto.response;

import lombok.NoArgsConstructor;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

// @AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CustomStoreResponse {
  private Long id;
  private String name;
  private Integer code;
  private String address;
  private String city;
  private String observation;
  private String priority;
  private String ruc;
  private Long routeId;
  private String routeName;
  private Long channelId;
  private String channelName;
  private Long platformId;
  private String platformName;

  public CustomStoreResponse(Long id, String name, Integer code, String address, String city, String observation,
      String priority, String ruc, Long routeId, String routeName, Long channelId, String channelName, Long platformId,
      String platformName) {
    this.id = id;
    this.name = name;
    this.code = code;
    this.address = address;
    this.city = city;
    this.observation = observation;
    this.priority = priority;
    this.ruc = ruc;
    this.routeId = routeId;
    this.routeName = routeName;
    this.channelId = channelId;
    this.channelName = channelName;
    this.platformId = platformId;
    this.platformName = platformName;
  }
}
