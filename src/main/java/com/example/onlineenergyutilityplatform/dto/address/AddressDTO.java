package com.example.onlineenergyutilityplatform.dto.address;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

  @NotNull
  @NotEmpty
  private String addressId;

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
