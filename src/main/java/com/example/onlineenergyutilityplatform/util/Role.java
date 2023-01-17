package com.example.onlineenergyutilityplatform.util;

import java.util.Arrays;
import java.util.Optional;

public enum Role {

  ROLE_CODE_0(0,"Regular"),
  ROLE_CODE_1(1,"Admin");

  private Integer roleCode;

  private String roleName;

  Role(Integer roleCode, String roleName){
    this.roleCode = roleCode;
    this.roleName = roleName;
  }

  public Integer getRoleCode() {
    return roleCode;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleCode(Integer roleCode) {
    this.roleCode = roleCode;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public static Integer getRoleCodeByRoleName(String roleName){
    Optional<Role> responseEnum = Arrays.stream(Role.values()).filter(roleEnum -> roleEnum.getRoleName().equals(roleName)).findAny();

    return responseEnum.isPresent() ? responseEnum.get().getRoleCode() : 2;
  }

  public static String getRoleNameByRoleCode(Integer roleCode){
    Optional<Role> responseEnum = Arrays.stream(Role.values()).filter(roleEnum -> roleEnum.getRoleCode().equals(roleCode)).findAny();

    return responseEnum.isPresent() ? responseEnum.get().getRoleName() : "This role doesn't exist";
  }
}
