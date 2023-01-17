package com.example.onlineenergyutilityplatform.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Credentials {

  @NotEmpty(message = "The email cannot be null or empty")
  private String name;

  @NotEmpty(message = "The email cannot be null or empty")
  private String password;

}
