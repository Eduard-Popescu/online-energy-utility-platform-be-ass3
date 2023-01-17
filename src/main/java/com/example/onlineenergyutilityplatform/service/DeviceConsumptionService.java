package com.example.onlineenergyutilityplatform.service;


import com.example.onlineenergyutilityplatform.dto.CustomMessage;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.DeviceConsumptionDto;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceConsumptionService {

  List<DeviceConsumptionDto> getDevicesReport(String userId);
  void addDeviceConsumption(LocalDateTime localDateTime);
}
