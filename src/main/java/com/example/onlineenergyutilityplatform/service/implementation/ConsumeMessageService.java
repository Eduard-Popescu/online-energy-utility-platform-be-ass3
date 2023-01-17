package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.domain.entity.DeviceConsumption;
import com.example.onlineenergyutilityplatform.dto.CustomMessage;
import com.example.onlineenergyutilityplatform.dto.TextMessageDTO;
import com.example.onlineenergyutilityplatform.repository.DeviceConsumptionRepository;
import com.example.onlineenergyutilityplatform.service.DeviceService;
import com.example.onlineenergyutilityplatform.service.UserDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ConsumeMessageService {

  private final DeviceConsumptionRepository deviceConsumptionRepository;
  private final SimpMessagingTemplate simpMessagingTemplate;
  private final UserDeviceService userDeviceService;
  private final DeviceService deviceService;

  public ConsumeMessageService(DeviceConsumptionRepository deviceConsumptionRepository, SimpMessagingTemplate simpMessagingTemplate, UserDeviceService userDeviceService, DeviceService deviceService) {
    this.deviceConsumptionRepository = deviceConsumptionRepository;
    this.simpMessagingTemplate = simpMessagingTemplate;
    this.userDeviceService = userDeviceService;
    this.deviceService = deviceService;
  }

  @RabbitListener(queues = "myQueue")
  public void listener(CustomMessage message) {

    if(message.getDevice_id() != null && message.getTimestamp() != null && message.getMeasurement_value() != null) {
      log.info("Sensor Message from Broker : {}",message);
      Device device = deviceService.getDevice(message.getDevice_id());
      if(message.getMeasurement_value() > device.getMaximumHourlyEnergyConsumption()){
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        String messageToSend = "The value read by the sensor for DEVICE :" +device.getDescription() + " is over the maximumHourlyEnergyConsumption";
        textMessageDTO.setMessage(messageToSend);
        log.info(messageToSend);
        log.info(message.getDevice_id());
        if(message.getDevice_id().equals("cf991a66-5775-11ed-9b6a-0242ac121399")) {
          textMessageDTO.setUser("01960160-5775-11ed-9b6a-0242ac120011");
          simpMessagingTemplate.convertAndSend( "/all/messages", textMessageDTO);
        }
        if(message.getDevice_id().equals("cf991a66-5775-11ed-9b6a-0242ac120002")){
          textMessageDTO.setUser("01960160-5775-11ed-9b6a-0242ac120002");
          simpMessagingTemplate.convertAndSend("/all/messages", textMessageDTO);
        }
      }else {
        DeviceConsumption deviceConsumption = DeviceConsumption.builder()
            .timestamp(message.getTimestamp())
            .device(device)
            .energy(message.getMeasurement_value())
            .deviceConsumptionId(UUID.randomUUID().toString())
            .build();
        deviceConsumptionRepository.save(deviceConsumption);
      }
    }else{
      log.info("Waiting for new sensor message");
    }
  }
}
