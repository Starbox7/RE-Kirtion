package com.server.server.status.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to load email template")
public class LoadHtmlTemplateException extends RuntimeException {
  public LoadHtmlTemplateException(String message){
    super(message);
  }
}
