package com.example.onlineenergyutilityplatform.exception;

public class ExpiredTokenException extends RuntimeException{
  public ExpiredTokenException(String message) { super(message);}
}
