package com.example.onlineenergyutilityplatform.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "ADDRESS")
public class Address {

  @Id
  @Column(name = "ADDRESS_ID")
  private String addressId;

  @Column(name = "STREET")
  private String street;

  @Column(name = "CITY")
  private String city;

  @Column(name = "COUNTRY")
  private String country;

  @Column(name = "POSTAL_CODE")
  private String postalCode;

}
