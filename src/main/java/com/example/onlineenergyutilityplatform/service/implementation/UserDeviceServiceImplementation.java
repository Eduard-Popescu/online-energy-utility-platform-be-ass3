package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.domain.compositeKey.CompositeKeyUserDevice;
import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.domain.entity.User;
import com.example.onlineenergyutilityplatform.domain.entity.UserDevice;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.AddUserDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.DeleteUserDeviceDTO;
import com.example.onlineenergyutilityplatform.mapper.DeviceMapper;
import com.example.onlineenergyutilityplatform.mapper.UserDeviceMapper;
import com.example.onlineenergyutilityplatform.repository.UserDeviceRepository;
import com.example.onlineenergyutilityplatform.service.DeviceService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import com.example.onlineenergyutilityplatform.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDeviceServiceImplementation implements UserDeviceService {

  private final UserDeviceRepository userDeviceRepository;
  private final UserService userService;
  private final DeviceService deviceService;

  public UserDeviceServiceImplementation(UserDeviceRepository userDeviceRepository, UserService userService, DeviceService deviceService) {
    this.userDeviceRepository = userDeviceRepository;
    this.userService = userService;
    this.deviceService = deviceService;
  }

  @Override
  public List<DeviceDTO> getAllUserDevicesByUserId(String userId) {
     return userDeviceRepository.getDevicesByUserId(userId).stream()
         .map(x -> DeviceMapper.deviceToDeviceDTO(x.getDevice()))
         .collect(Collectors.toList());
  }

  @Override
  public void addUserDevice(AddUserDeviceDTO addUserDeviceDTO) {
    Device device = deviceService.getDevice(addUserDeviceDTO.getDeviceId());
    User user = userService.getUser(addUserDeviceDTO.getUserId());
    UserDevice userDevice = UserDeviceMapper.userToUserDeviceDto(addUserDeviceDTO,device,user);
    userDeviceRepository.save(userDevice);
  }

  @Override
  public void deleteUserDevice(DeleteUserDeviceDTO deleteUserDeviceDTO) {
    CompositeKeyUserDevice compositeKeyUserDevice = CompositeKeyUserDevice.builder()
        .deviceId(deleteUserDeviceDTO.getDeviceId())
        .userId(deleteUserDeviceDTO.getUserId())
        .build();

    UserDevice userDevice = userDeviceRepository.findById(compositeKeyUserDevice).
        orElseThrow(() -> new EntityNotFoundException("This user doesn't have this device"));

    userDeviceRepository.delete(userDevice);
  }
}
