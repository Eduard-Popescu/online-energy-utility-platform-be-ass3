package com.example.onlineenergyutilityplatform.repository;

import com.example.onlineenergyutilityplatform.domain.entity.Device;
import com.example.onlineenergyutilityplatform.domain.entity.DeviceConsumption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceConsumptionRepository extends JpaRepository<DeviceConsumption,String> {

  @Query("SELECT dc FROM DeviceConsumption dc WHERE dc.device.deviceId=?1")
  List<DeviceConsumption> getDeviceConsumptionByDevice(String deviceId);
}
