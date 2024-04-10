package com.server.server.status.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email is already registered")
public class EmailAlreadyException extends RuntimeException {
  public  EmailAlreadyException(String message){
    super(message);
  }
}
