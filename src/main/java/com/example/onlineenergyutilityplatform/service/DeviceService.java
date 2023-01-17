package com.example.onlineenergyutilityplatform.service;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.dto.device.CreateDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.UpdateDeviceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {

  DeviceDTO createDevice(CreateDeviceDTO createDeviceDTO);
  Device getDevice(String deviceId);
  DeviceDTO getDeviceDto(String deviceId);
  List<Device> getDevices();
  List<DeviceDTO> getDevicesDto();
  DeviceDTO updateDevice(UpdateDeviceDTO updateDeviceDTO);
  void deleteDevice(String deviceId);
}
