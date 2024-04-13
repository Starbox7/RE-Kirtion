package com.server.server.status.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Password is Not Matched")
public class PasswordNotMatchException extends RuntimeException {
  public PasswordNotMatchException(String message){
    super(message);
  }
}
