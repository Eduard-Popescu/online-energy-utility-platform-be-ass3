package com.example.onlineenergyutilityplatform.repository;

import com.example.onlineenergyutilityplatform.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,String> {
}
