package com.example.onlineenergyutilityplatform.domain.compositeKey;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompositeKeyUserDevice implements Serializable {

  @NotNull
  private String deviceId;

  @NotNull
  private String userId;

}
