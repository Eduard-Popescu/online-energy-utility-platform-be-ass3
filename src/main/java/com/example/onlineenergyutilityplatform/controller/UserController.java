package com.example.onlineenergyutilityplatform.controller;

import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.DeviceConsumptionDto;
import com.example.onlineenergyutilityplatform.service.DeviceConsumptionService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@Slf4j
@CrossOrigin(origins = "*")
public class UserController {

  private final UserDeviceService userDeviceService;
  private final DeviceConsumptionService deviceConsumptionService;

  public UserController(UserDeviceService userDeviceService, DeviceConsumptionService deviceConsumptionService) {
    this.userDeviceService = userDeviceService;
    this.deviceConsumptionService = deviceConsumptionService;
  }

  @GetMapping(path = "/user-device/{userId}")
  public ResponseEntity<List<DeviceDTO>> getAllDevicesForAUser(@PathVariable String userId){
    List<DeviceDTO> deviceDTOS = userDeviceService.getAllUserDevicesByUserId(userId);
    return ResponseEntity.ok(deviceDTOS);
  }

  @GetMapping(path = "/device-consumption/{userId}")
  public ResponseEntity<List<DeviceConsumptionDto>> getAllConsumptionsForADevice(@PathVariable String userId){
    List<DeviceConsumptionDto> deviceConsumptionDtoList = deviceConsumptionService.getDevicesReport(userId);
    return ResponseEntity.ok(deviceConsumptionDtoList);
  }

}
