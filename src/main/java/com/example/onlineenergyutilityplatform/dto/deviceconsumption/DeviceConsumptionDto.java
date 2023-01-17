package com.example.onlineenergyutilityplatform.dto.deviceconsumption;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceConsumptionDto {

  @NotNull
  @NotEmpty
  private String deviceDescription;

  @NotNull
  private List<ConsumptionDTO> consumptionDTOS;

}
