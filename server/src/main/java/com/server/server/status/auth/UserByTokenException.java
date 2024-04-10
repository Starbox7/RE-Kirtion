package com.server.server.status.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Fail find user by Token")
public class UserByTokenException  extends RuntimeException{
  public UserByTokenException(String message){
    super(message);
  }
}
