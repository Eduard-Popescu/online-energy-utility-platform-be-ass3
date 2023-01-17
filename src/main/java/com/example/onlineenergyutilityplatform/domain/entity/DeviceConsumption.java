package com.example.onlineenergyutilityplatform.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "DEVICE_CONSUMPTION")
public class DeviceConsumption {

  @Id
  @Column(name = "DEVICE_CONSUMPTION_ID")
  private String deviceConsumptionId;

  @Column(name = "TIMESTAMP")
  private String timestamp;

  @Column(name = "ENERGY")
  private Double energy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "DEVICE_ID", nullable = false)
  private Device device;
}
