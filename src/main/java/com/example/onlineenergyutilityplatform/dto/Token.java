package com.example.onlineenergyutilityplatform.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Token {

  @NotEmpty(message = "Token should not be null or empty")
  private String token;
  @NotEmpty(message = "Role should not be null or empty")
  private String role;
  @NotEmpty(message = "UserId should not be null or empty")
  private String userId;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Token token1 = (Token) o;
    return token.equals(token1.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }
}
