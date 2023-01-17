package com.example.onlineenergyutilityplatform.mapper;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.dto.address.AddressDTO;
import com.example.onlineenergyutilityplatform.dto.device.CreateDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeviceMapper {

  public static Device createDeviceDTOToDevice(CreateDeviceDTO createDeviceDTO){
    AddressDTO addressDTO = AddressDTO.builder()
        .city(createDeviceDTO.getCity())
        .country(createDeviceDTO.getCountry())
        .postalCode(createDeviceDTO.getPostalCode())
        .street(createDeviceDTO.getStreet())
        .build();
    return Device.builder()
        .deviceId(UUID.randomUUID().toString())
        .description(createDeviceDTO.getDescription())
        .maximumHourlyEnergyConsumption(createDeviceDTO.getMaximumHourlyEnergyConsumption())
        .address(AddressMapper.addressDtoToAddress(addressDTO))
        .build();
  }

  public static DeviceDTO deviceToDeviceDTO(Device device){
    return DeviceDTO.builder()
        .deviceId(device.getDeviceId())
        .city(device.getAddress().getCity())
        .country(device.getAddress().getCountry())
        .street(device.getAddress().getStreet())
        .postalCode(device.getAddress().getPostalCode())
        .description(device.getDescription())
        .maximumHourlyEnergyConsumption(device.getMaximumHourlyEnergyConsumption())
        .build();
  }

  public static Device deviceDtoToDevice(DeviceDTO deviceDTO){
    AddressDTO addressDTO = AddressDTO.builder()
        .city(deviceDTO.getCity())
        .country(deviceDTO.getCountry())
        .postalCode(deviceDTO.getPostalCode())
        .street(deviceDTO.getStreet())
        .build();
    return Device.builder()
        .deviceId(deviceDTO.getDeviceId())
        .address(AddressMapper.addressDtoToAddress(addressDTO))
        .maximumHourlyEnergyConsumption(deviceDTO.getMaximumHourlyEnergyConsumption())
        .description(deviceDTO.getDescription())
        .build();
  }
}
