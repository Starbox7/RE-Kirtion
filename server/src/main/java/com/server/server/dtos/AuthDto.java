package com.server.server.dtos;

import lombok.Data;

@Data
public class AuthDto {
  private String email;
  private String password;
}
