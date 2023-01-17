package com.example.onlineenergyutilityplatform.domain.entity;

import com.example.onlineenergyutilityplatform.domain.compositeKey.CompositeKeyUserDevice;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "USER_DEVICE")
public class UserDevice {

  @EmbeddedId
  @Column(name = "USER_DEVICE_ID")
  private CompositeKeyUserDevice userDeviceId;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("deviceId")
  @JoinColumn(name = "DEVICE_ID")
  private Device device;

  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("userId")
  @JoinColumn(name = "USER_ID")
  private User user;
}
