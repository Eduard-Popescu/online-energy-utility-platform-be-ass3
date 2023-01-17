package com.example.onlineenergyutilityplatform.scheduler;

import com.example.onlineenergyutilityplatform.dto.TextMessageDTO;
import com.example.onlineenergyutilityplatform.dto.device.DeviceDTO;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.ConsumptionDTO;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.DeviceConsumptionDto;
import com.example.onlineenergyutilityplatform.service.DeviceConsumptionService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class DeviceConsumptionScheduler {

  private final SimpMessagingTemplate simpMessagingTemplate;
  private final DeviceConsumptionService deviceConsumptionService;
  private final UserDeviceService userDeviceService;
  private LocalDateTime localDateTime;

  public DeviceConsumptionScheduler(SimpMessagingTemplate simpMessagingTemplate, DeviceConsumptionService deviceConsumptionService, UserDeviceService userDeviceService) {
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.deviceConsumptionService = deviceConsumptionService;
    this.userDeviceService = userDeviceService;
    this.localDateTime = LocalDateTime.now();
  }

  @Scheduled(cron = "*/59 * 1 * * *")
  public void execute(){
    localDateTime = localDateTime.plusHours(1L);
    log.info("Scheduler executed at {}", LocalDateTime.now());
   // deviceConsumptionService.addDeviceConsumption(localDateTime);
  }

//  @Scheduled(cron = "*/59 * * * * *")
//  @CrossOrigin(origins = "*")
//  public void checkForAlertFirstUser(){
//    String userId = "01960160-5775-11ed-9b6a-0242ac120002";
//    List<DeviceConsumptionDto> deviceConsumptionDtoList = deviceConsumptionService.getDevicesReport(userId);
//    List<DeviceDTO> deviceDTOS = userDeviceService.getAllUserDevicesByUserId(userId);
//    for(DeviceConsumptionDto dcd : deviceConsumptionDtoList){
//      for(ConsumptionDTO cons : dcd.getConsumptionDTOS()){
//        if (cons.getEnergy() > 200.0){
//          TextMessageDTO textMessageDTO = new TextMessageDTO();
//          String message = "The value read by the sensor for DEVICE :" +dcd.getDeviceDescription() + " is over the maximumHourlyEnergyConsumption";
//          textMessageDTO.setMessage(message);
//          log.info(message);
//          simpMessagingTemplate.convertAndSend("/all/messages", textMessageDTO);
//          break;
//        }
//      }
//    }
//  }
}
