package com.example.onlineenergyutilityplatform.mapper;

import com.example.onlineenergyutilityplatform.domain.entity.DeviceConsumption;
import com.example.onlineenergyutilityplatform.dto.deviceconsumption.ConsumptionDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceConsumptionMapper {

  public static ConsumptionDTO deviceConsumptionToConsumptionDTO(DeviceConsumption deviceConsumption){
    return ConsumptionDTO.builder()
        .energy(deviceConsumption.getEnergy())
        .timestamp(deviceConsumption.getTimestamp())
        .build();
  }

}
