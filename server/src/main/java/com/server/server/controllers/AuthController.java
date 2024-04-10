package com.server.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.server.dtos.AuthDto;
import com.server.server.global.status.InternalServerException;
import com.server.server.models.UserModel;
import com.server.server.services.AuthService;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody AuthDto authDto) {
      try{
        authService.isEmailAlreadyRegistered(authDto.getEmail());
        UUID token = authService.createUuid();
        String encode = authService.encodedPassword(authDto.getPassword());
        String name = authService.createNameByEmail(authDto.getEmail());
        authService.createUser(token, encode, authDto.getEmail(), name);
        authService.sendEmailVerification(authDto.getEmail(), token);

        return ResponseEntity.ok(authDto.getEmail());
      }
      catch(Exception error){
        throw new InternalServerException(error.toString());
      }
    }

    @GetMapping("/register/verification")
    public String verificationUser(@RequestParam(name = "token", required = true) String token) {
      UserModel user = authService.findByToken(token);
      authService.verificationComplete(user);
      String content = authService.loadCompleteVerificationHtml();

      return content;
    }
    
  }
