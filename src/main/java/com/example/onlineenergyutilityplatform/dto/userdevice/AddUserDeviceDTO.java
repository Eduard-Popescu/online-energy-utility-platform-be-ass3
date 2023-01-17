package com.example.onlineenergyutilityplatform.dto.userdevice;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddUserDeviceDTO {

  @NotNull
  @NotEmpty
  private String userId;

  @NotNull
  @NotEmpty
  private String deviceId;

}
