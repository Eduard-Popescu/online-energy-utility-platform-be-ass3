package com.example.onlineenergyutilityplatform.repository;

import com.example.onlineenergyutilityplatform.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByName(String name);

}
