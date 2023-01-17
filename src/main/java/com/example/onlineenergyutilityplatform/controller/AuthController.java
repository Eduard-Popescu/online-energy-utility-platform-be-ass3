package com.example.onlineenergyutilityplatform.controller;

import com.example.onlineenergyutilityplatform.dto.Credentials;
import com.example.onlineenergyutilityplatform.dto.Token;
import com.example.onlineenergyutilityplatform.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {

  private final AuthService authorizationService;

  public AuthController(AuthService authorizationService) {
    this.authorizationService = authorizationService;
  }

  @PostMapping("/login")
  public ResponseEntity<Token> login(@RequestBody @Valid Credentials credentials){
    return ResponseEntity.ok(authorizationService.login(credentials));
  }

}
