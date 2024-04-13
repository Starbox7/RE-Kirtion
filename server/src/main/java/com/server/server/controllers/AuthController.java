package com.server.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.server.dtos.AuthDto;
import com.server.server.dtos.InitDto;
import com.server.server.dtos.TokenDto;
import com.server.server.global.status.InternalServerException;
import com.server.server.models.PersonalspaceModel;
import com.server.server.models.UserModel;
import com.server.server.models.WorkspaceModel;
import com.server.server.services.AuthService;
import com.server.server.services.InitService;
import com.server.server.services.PageService;
import com.server.server.services.SpaceService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;
  private final InitService initService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody AuthDto authDto) {
      try{
        authService.isEmailAlreadyRegistered(authDto.getEmail());
        UUID token = UUID.randomUUID();
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
    UserModel user = authService.findUserByToken(token);
    boolean isValid = authService.verificationComplete(user);
    if(isValid){
      String name = authService.createNameByEmail(user.getEmail());
      initService.kirtionInit(user, name);
    }

    String content = authService.loadCompleteVerificationHtml();

    return content;
  }

  @PostMapping("/login")
  public InitDto login(@RequestBody AuthDto authDto) {
    String email = authDto.getEmail();
    String password = authDto.getPassword();

    UserModel user = authService.findUserByEmail(email);
    authService.verifyPassword(password, user);

    authService.isEmailValid(user);

    TokenDto tokenDto = authService.login(email, password);
    String accessToken = tokenDto.getAccessToken();
    String refreshToken = tokenDto.getRefreshToken();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Access-Token", accessToken);
    headers.add("Refresh-Token", refreshToken);

    
    
    return null;
  }
  
    
}
