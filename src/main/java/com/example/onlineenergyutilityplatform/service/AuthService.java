package com.example.onlineenergyutilityplatform.service;

import com.example.onlineenergyutilityplatform.dto.Credentials;
import com.example.onlineenergyutilityplatform.dto.Token;

public interface AuthService {

  Token login(Credentials credentials);

}
