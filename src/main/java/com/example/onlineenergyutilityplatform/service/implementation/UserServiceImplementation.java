package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.domain.entity.User;
import com.example.onlineenergyutilityplatform.dto.SecurityUser;
import com.example.onlineenergyutilityplatform.dto.user.CreateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UpdateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UserDTO;
import com.example.onlineenergyutilityplatform.mapper.UserMapper;
import com.example.onlineenergyutilityplatform.repository.UserRepository;
import com.example.onlineenergyutilityplatform.service.UserService;
import com.example.onlineenergyutilityplatform.util.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

  private final UserRepository userRepository;

  public UserServiceImplementation (UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO createUser(CreateUserDTO createUserDTO) {
    User user = UserMapper.createUserDTOToUser(createUserDTO);
    return UserMapper.userToUserDTO(userRepository.save(user));
  }

  @Override
  public User getUser(String userId) {
    return userRepository.findById(userId).
        orElseThrow(() -> new EntityNotFoundException("User with name : "+userId+"doesn't exist"));
  }

  @Override
  public User getUserByName(String userName) {
    return userRepository.findByName(userName).
        orElseThrow(() -> new EntityNotFoundException("User with name : "+userName+"doesn't exist"));
  }

  @Override
  public UserDTO getUserDto(String userId) {
    User user = userRepository.findById(userId).
        orElseThrow(() -> new EntityNotFoundException("User with name : "+userId+"doesn't exist"));
    return UserMapper.userToUserDTO(user);
  }

  @Override
  public List<UserDTO> getUsers() {
    return userRepository.findAll().stream().map(UserMapper::userToUserDTO).collect(Collectors.toList());
  }

  @Override
  public UserDTO updateUser(UpdateUserDTO updateUserDTO) {
    User user = userRepository.findById(updateUserDTO.getUserId()).orElseThrow(() -> new EntityNotFoundException("Current user doesn't exist"));
    user.setName(updateUserDTO.getName());
    user.setRole(Role.getRoleCodeByRoleName(updateUserDTO.getRole()));
    user.setPassword(updateUserDTO.getPassword());
    userRepository.save(user);
    return UserMapper.userToUserDTO(user);
  }

  @Override
  public void deleteUser(String userId) {
    User user = getUser(userId);
     userRepository.delete(user);
  }

  @Override
  public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
    return new SecurityUser(this.getUserByName(username));
  }
}
