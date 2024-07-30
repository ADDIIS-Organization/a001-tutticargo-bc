package com.addiis.core.gestionlogistica.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPalletRequest {

  @NotNull(message = "Channel ID cannot be null")
  private Integer channelId;

  @NotEmpty(message = "The orderPalletsInfo list cannot be empty")
  @Size(min = 6, message = "The orderPalletsInfo list must have at least 6 elements")
  private List<OrderPalletInfo> orderPalletsInfo;
}