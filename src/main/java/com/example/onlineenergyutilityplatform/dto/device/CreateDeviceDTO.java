package com.example.onlineenergyutilityplatform.dto.device;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateDeviceDTO {

  @NotNull
  @NotEmpty
  private String deviceId;

  @NotNull
  @NotEmpty
  private String description;

  @NotNull
  @NotEmpty
  private Double maximumHourlyEnergyConsumption;

  @NotNull
  @NotEmpty
  private String street;

  @NotNull
  @NotEmpty
  private String city;

  @NotNull
  @NotEmpty
  private String country;

  @NotNull
  @NotEmpty
  private String postalCode;

}
