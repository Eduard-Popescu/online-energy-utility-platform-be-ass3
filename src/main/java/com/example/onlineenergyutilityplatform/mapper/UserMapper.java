package com.example.onlineenergyutilityplatform.mapper;

import com.example.onlineenergyutilityplatform.domain.entity.User;
import com.example.onlineenergyutilityplatform.dto.user.CreateUserDTO;
import com.example.onlineenergyutilityplatform.dto.user.UserDTO;
import com.example.onlineenergyutilityplatform.util.Role;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public static User userDtoToUser(UserDTO userDTO) {
        return User.builder()
                .userID(UUID.randomUUID().toString())
                .name(userDTO.getName())
                .role(Role.getRoleCodeByRoleName(userDTO.getRole()))
                .password(userDTO.getPassword())
                .build();
    }

    public static User createUserDTOToUser(CreateUserDTO createUserDTO) {
        return User.builder()
                .userID(UUID.randomUUID().toString())
                .name(createUserDTO.getName())
                .role(Role.getRoleCodeByRoleName(createUserDTO.getRole()))
                .password(createUserDTO.getPassword())
                .build();
    }

    public static UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .userId(user.getUserID())
                .name(user.getName())
                .role(Role.getRoleNameByRoleCode(user.getRole()))
                .password(user.getPassword())
                .build();
    }

}
