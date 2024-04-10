package com.server.server.global.status;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error : code 500")
public class InternalServerException extends RuntimeException {
  public InternalServerException(String message){
    super(message);
  }
}
