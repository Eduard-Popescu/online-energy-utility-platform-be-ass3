package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.domain.entity.DeviceConsumption;
import com.example.onlineenergyutilityplatform.dto.CustomMessage;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.ConsumptionDTO;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.DeviceConsumptionDto;
import com.example.onlineenergyutilityplatform.mapper.DeviceConsumptionMapper;
import com.example.onlineenergyutilityplatform.mapper.DeviceMapper;
import com.example.onlineenergyutilityplatform.repository.DeviceConsumptionRepository;
import com.example.onlineenergyutilityplatform.service.DeviceConsumptionService;
import com.example.onlineenergyutilityplatform.service.DeviceService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class DeviceConsumptionServiceImplementation implements DeviceConsumptionService {

  private final DeviceConsumptionRepository deviceConsumptionRepository;
  private final UserDeviceService userDeviceService;
  private final DeviceService deviceService;

  public DeviceConsumptionServiceImplementation(DeviceConsumptionRepository deviceConsumptionRepository, UserDeviceService userDeviceService, DeviceService deviceService) {
    this.deviceConsumptionRepository = deviceConsumptionRepository;
    this.userDeviceService = userDeviceService;
    this.deviceService = deviceService;
  }


  @Override
  public List<DeviceConsumptionDto> getDevicesReport(String userId) {
    List<DeviceConsumptionDto> deviceConsumptionDtoList = new ArrayList<>();
    List<Device> devices = userDeviceService.getAllUserDevicesByUserId(userId).stream()
        .map(DeviceMapper::deviceDtoToDevice)
        .collect(Collectors.toList());

    for(Device device : devices){
      List<DeviceConsumption> deviceConsumptions = deviceConsumptionRepository.getDeviceConsumptionByDevice(device.getDeviceId());
      List<ConsumptionDTO> consumptionDTOList = deviceConsumptions.stream()
          .map(DeviceConsumptionMapper::deviceConsumptionToConsumptionDTO)
          .collect(Collectors.toList());
      deviceConsumptionDtoList.add(DeviceConsumptionDto.builder()
          .consumptionDTOS(consumptionDTOList)
          .deviceDescription(device.getDescription())
          .build());
    }
    return deviceConsumptionDtoList;
  }

  @Override
  public void addDeviceConsumption(LocalDateTime localDateTime) {
    List<Device> devices = deviceService.getDevices();

    Random rand1 = new Random();
    int randomIndex = rand1.nextInt((devices.size()-1) + 1);

    Device selectedDevice = devices.get(randomIndex);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    DeviceConsumption deviceConsumption = DeviceConsumption.builder()
        .device(selectedDevice)
        .deviceConsumptionId(UUID.randomUUID().toString())
        .energy(selectedDevice.getMaximumHourlyEnergyConsumption() + new Random().nextDouble() * (5.0 - selectedDevice.getMaximumHourlyEnergyConsumption()))
        .timestamp(localDateTime.format(formatter))
        .build();

    deviceConsumptionRepository.save(deviceConsumption);
  }

}
