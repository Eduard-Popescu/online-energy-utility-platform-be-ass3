package com.example.onlineenergyutilityplatform.repository;

import com.example.onlineenergyutilityplatform.domain.compositeKey.CompositeKeyUserDevice;
import com.example.onlineenergyutilityplatform.domain.entity.UserDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDeviceRepository extends JpaRepository<UserDevice, CompositeKeyUserDevice> {

  @Query("SELECT ud FROM UserDevice ud WHERE ud.userDeviceId.userId=?1")
  List<UserDevice> getDevicesByUserId(String userId);
}
