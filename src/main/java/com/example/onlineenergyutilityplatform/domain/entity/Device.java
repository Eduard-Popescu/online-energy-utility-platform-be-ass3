package com.example.onlineenergyutilityplatform.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "DEVICE")
public class Device {

  @Id
  @Column(name = "DEVICE_ID")
  private String deviceId;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "MAXIMUM_HOURLY_ENERGY_CONSUMPTION")
  private Double maximumHourlyEnergyConsumption;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ADDRESS_ID", nullable = false)
  private Address address;

}
