package com.example.onlineenergyutilityplatform.mapper;

import com.example.onlineenergyutilityplatform.domain.compositeKey.CompositeKeyUserDevice;
import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.domain.entity.User;
import com.example.onlineenergyutilityplatform.domain.entity.UserDevice;
import com.example.onlineenergyutilityplatform.dto.userdevice.AddUserDeviceDTO;
import org.springframework.stereotype.Component;

@Component
public class UserDeviceMapper {

  public static UserDevice userToUserDeviceDto(AddUserDeviceDTO addUserDeviceDTO, Device device, User user){
      return UserDevice.builder()
          .userDeviceId(new CompositeKeyUserDevice(addUserDeviceDTO.getDeviceId(), addUserDeviceDTO.getUserId()))
          .device(device)
          .user(user)
          .build();
  }

}
