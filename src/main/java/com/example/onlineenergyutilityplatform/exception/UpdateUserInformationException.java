package com.example.onlineenergyutilityplatform.exception;

public class UpdateUserInformationException extends RuntimeException{

  private String message;

  public UpdateUserInformationException (String message){
    super(message);
    this.message = message;
  }

}
