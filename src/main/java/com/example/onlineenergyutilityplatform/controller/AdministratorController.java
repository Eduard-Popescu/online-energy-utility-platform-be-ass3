package com.example.onlineenergyutilityplatform.controller;

import com.example.onlineenergyutilityplatform.dto.device.CreateDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.device.UpdateDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.user.CreateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UpdateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UserDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.AddUserDeviceDTO;
import com.example.onlineenergyutilityplatform.dto.userdevice.DeleteUserDeviceDTO;
import com.example.onlineenergyutilityplatform.service.DeviceService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import com.example.onlineenergyutilityplatform.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@Slf4j
@CrossOrigin(origins = "*")
public class AdministratorController {

  private final DeviceService deviceService;
  private final UserService userService;
  private final UserDeviceService userDeviceService;

  public AdministratorController(DeviceService deviceService, UserService userService, UserDeviceService userDeviceService) {
    this.deviceService = deviceService;
    this.userService = userService;
    this.userDeviceService = userDeviceService;
  }

  @GetMapping(path = "/user/{userId}")
  public ResponseEntity<UserDTO> getUserById(@PathVariable String userId){
    UserDTO userDTO = userService.getUserDto(userId);
    log.info("UserDto from getUserById end-point : {}",userDTO);
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping(path = "/user")
  public ResponseEntity<List<UserDTO>> getUserById() {
    List<UserDTO> userDTOS = userService.getUsers();
    return ResponseEntity.ok(userDTOS);
  }

  @PostMapping(path = "/user")
  public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO){
    UserDTO userDTO = userService.createUser(createUserDTO);
    log.info("Created user : {}",userDTO);
    return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
  }

  @PutMapping(path = "/user")
  public ResponseEntity<UserDTO> updatedUser(@RequestBody UpdateUserDTO updateUserDTO){
    UserDTO userDTO = userService.updateUser(updateUserDTO);
    log.info("Updated user : {}",userDTO);
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }

  @DeleteMapping(path = "/user/{userId}")
  public ResponseEntity<Void> deleteUser(@PathVariable String userId){
    userService.deleteUser(userId);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  /**
   Device CRUD
   */

  @GetMapping(path = "/device/{deviceId}")
  public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable String deviceId){
    DeviceDTO deviceDTO = deviceService.getDeviceDto(deviceId);
    log.info("DeviceDTO from getDeviceById end-point : {}",deviceDTO);
    return ResponseEntity.ok(deviceDTO);
  }

  @GetMapping(path = "/device")
  public ResponseEntity<List<DeviceDTO>> getAllDevices() {
    List<DeviceDTO> deviceDTOS = deviceService.getDevicesDto();
    return ResponseEntity.ok(deviceDTOS);
  }

  @PostMapping(path = "/device")
  public ResponseEntity<DeviceDTO> createDevice(@RequestBody CreateDeviceDTO createDeviceDTO){
    DeviceDTO deviceDTO = deviceService.createDevice(createDeviceDTO);
    log.info("Created device : {}",deviceDTO);
    return new ResponseEntity<>(deviceDTO, HttpStatus.CREATED);
  }

  @PutMapping(path = "/device")
  public ResponseEntity<DeviceDTO> updateDevice(@RequestBody UpdateDeviceDTO updateDeviceDTO){
    DeviceDTO deviceDTO = deviceService.updateDevice(updateDeviceDTO);
    log.info("Updated device : {}",deviceDTO);
    return new ResponseEntity<>(deviceDTO, HttpStatus.OK);
  }

  @DeleteMapping(path = "/device/{deviceId}")
  public ResponseEntity<Void> deleteDevice(@PathVariable String deviceId){
    deviceService.deleteDevice(deviceId);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

  /**
   * Device user mapping
   */

  @PostMapping(path = "/user-device")
  public ResponseEntity<Void> addUserDevice(@RequestBody AddUserDeviceDTO addUserDeviceDTO){
    log.info(" Request AddUserDeviceDTO:{}",addUserDeviceDTO);
    userDeviceService.addUserDevice(addUserDeviceDTO);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping(path = "/user-device")
  public ResponseEntity<Void> deleteUserDevice(@RequestBody DeleteUserDeviceDTO deleteUserDeviceDTO){
    userDeviceService.deleteUserDevice(deleteUserDeviceDTO);
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }

}
