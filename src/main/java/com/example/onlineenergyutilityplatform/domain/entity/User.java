package com.example.onlineenergyutilityplatform.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "USERS")
public class User {

  @Id
  @Column(name = "USER_ID")
  private String userID;

  @Column(name = "NAME")
  private String name;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ROLE")
  private Integer role;
}
