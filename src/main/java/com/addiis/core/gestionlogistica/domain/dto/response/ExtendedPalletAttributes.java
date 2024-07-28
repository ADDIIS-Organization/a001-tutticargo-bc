package com.addiis.core.gestionlogistica.domain.dto.response;

import com.addiis.core.gestionlogistica.domain.dto.request.PalletAttributes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedPalletAttributes extends PalletAttributes {
  private Long id;
}
