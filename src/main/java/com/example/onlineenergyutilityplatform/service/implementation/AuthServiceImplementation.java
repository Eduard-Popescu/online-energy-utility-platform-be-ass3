package com.example.onlineenergyutilityplatform.service.implementation;

import com.example.onlineenergyutilityplatform.dto.Credentials;
import com.example.onlineenergyutilityplatform.dto.Token;
import com.example.onlineenergyutilityplatform.exception.FailedLogInException;
import com.example.onlineenergyutilityplatform.security.TokenGenerator;
import com.example.onlineenergyutilityplatform.service.AuthService;
import com.example.onlineenergyutilityplatform.service.UserService;
import com.example.onlineenergyutilityplatform.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService {

  private final TokenGenerator tokenGenerator;
  private final UserService userService;


  @Autowired
  public AuthServiceImplementation(TokenGenerator tokenGenerator, UserService userService) {
    this.tokenGenerator = tokenGenerator;
    this.userService = userService;
  }

  @Override
  public Token login(Credentials credentials){
    if(userService.getUserByName(credentials.getName()).getPassword().equals(credentials.getPassword())) {
      String role = Role.getRoleNameByRoleCode(userService.getUserByName(credentials.getName()).getRole());
      String userId = userService.getUserByName(credentials.getName()).getUserID();
      return new Token(generateToken(credentials.getName()),role,userId);
    }
    throw new FailedLogInException("Wrong Credentials");
  }

  private String generateToken(String email){
    UserDetails userDetails = this.getUserDetails(email);
    return tokenGenerator.generateToken(userDetails);
  }

  private UserDetails getUserDetails(String userName){
    return userService.loadUserByUserName(userName);
  }

}
