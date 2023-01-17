package com.example.onlineenergyutilityplatform.dto.deviceconsumption;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsumptionDTO {

  @NotNull
  @NotEmpty
  private String timestamp;

  @NotNull
  @NotEmpty
  private Double energy;

}
