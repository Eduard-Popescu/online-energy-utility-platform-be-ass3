package com.example.onlineenergyutilityplatform.service;

import com.example.onlineenergyutilityplatform.domain.entity.User;
import com.example.onlineenergyutilityplatform.dto.user.CreateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UpdateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {

  UserDTO createUser(CreateUserDTO createUserDTO);
  User getUser(String userId);
  UserDTO getUserDto(String userId);
  User getUserByName(String userName);
  List<UserDTO> getUsers();
  UserDTO updateUser(UpdateUserDTO updateUserDTO);
  void deleteUser(String userId);
  UserDetails loadUserByUserName(String username) throws UsernameNotFoundException;
}
