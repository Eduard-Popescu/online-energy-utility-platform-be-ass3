package com.example.onlineenergyutilityplatform.service;

import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.AddUserDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.DeleteUserDeviceDTO;

import java.util.List;

public interface UserDeviceService {

  List<DeviceDTO> getAllUserDevicesByUserId(String userId);
  void addUserDevice(AddUserDeviceDTO addUserDeviceDTO);
  void deleteUserDevice(DeleteUserDeviceDTO deleteUserDeviceDTO);
}
