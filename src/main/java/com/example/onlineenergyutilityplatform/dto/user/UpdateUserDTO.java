package com.example.onlineenergyutilityplatform.dto.user;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDTO {

  @NotNull
  @NotEmpty
  private String userId;

  @NotNull
  @NotEmpty
  private String name;

  @NotNull
  @NotEmpty
  private String role;
  @NotNull
  @NotEmpty
  private String password;
}
