package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.dto.address.AddressDTO;
import com.example.onlineenergyutilityplatform.dto.device.CreateDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.UpdateDeviceDTO;
import com.example.onlineenergyutilityplatform.mapper.AddressMapper;
import com.example.onlineenergyutilityplatform.mapper.DeviceMapper;
import com.example.onlineenergyutilityplatform.repository.AddressRepository;
import com.example.onlineenergyutilityplatform.repository.DeviceRepository;
import com.example.onlineenergyutilityplatform.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImplementation implements DeviceService {

  private final DeviceRepository deviceRepository;
  private final AddressRepository addressRepository;
  public DeviceServiceImplementation(DeviceRepository deviceRepository, AddressRepository addressRepository){
    this.deviceRepository = deviceRepository;
    this.addressRepository = addressRepository;
  }

  @Override
  public DeviceDTO createDevice(CreateDeviceDTO createDeviceDTO) {
    Device device = DeviceMapper.createDeviceDTOToDevice(createDeviceDTO);
    addressRepository.save(device.getAddress());
    return DeviceMapper.deviceToDeviceDTO(deviceRepository.save(device));
  }

  @Override
  public Device getDevice(String deviceId) {
    return deviceRepository.findById(deviceId).
        orElseThrow(() -> new EntityNotFoundException("This device doesn't exist"));
  }

  @Override
  public DeviceDTO getDeviceDto(String deviceId) {
    return DeviceMapper.deviceToDeviceDTO(deviceRepository.findById(deviceId).
        orElseThrow(() -> new EntityNotFoundException("This device doesn't exist")));
  }

  @Override
  public List<DeviceDTO> getDevicesDto() {
    return deviceRepository.findAll().stream().map(DeviceMapper::deviceToDeviceDTO).collect(Collectors.toList());
  }

  public List<Device> getDevices() {
    return deviceRepository.findAll();
  }

  @Override
  @Transactional
  public DeviceDTO updateDevice(UpdateDeviceDTO updateDeviceDTO) {
    AddressDTO addressDTO = AddressDTO.builder()
        .street(updateDeviceDTO.getStreet())
        .postalCode(updateDeviceDTO.getPostalCode())
        .country(updateDeviceDTO.getCountry())
        .city(updateDeviceDTO.getCity())
        .build();
    Device device = getDevice(updateDeviceDTO.getDeviceId());
    device.setAddress(AddressMapper.addressDtoToAddressOnUpdate(addressDTO,device.getAddress().getAddressId()));
    device.setDescription(updateDeviceDTO.getDescription());
    device.setMaximumHourlyEnergyConsumption(updateDeviceDTO.getMaximumHourlyEnergyConsumption());
    deviceRepository.saveAndFlush(device);
    return DeviceMapper.deviceToDeviceDTO(device);
  }

  @Override
  public void deleteDevice(String deviceId) {
     Device device = getDevice(deviceId);
     deviceRepository.delete(device);
  }
}