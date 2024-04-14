package com.server.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.server.dtos.InitDto;
import com.server.server.global.auth.JwtTokenProvider;
import com.server.server.models.UserModel;
import com.server.server.services.AuthService;
import com.server.server.services.InitService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/space")
public class SpaceController {
  private final JwtTokenProvider jwtTokenProvider;

  private final AuthService authService;
  private final InitService initService;

  @GetMapping("/init")
  public ResponseEntity<InitDto> initSpacEntity(
    @RequestHeader(value = "authorization", required = true) String  accessToken) {
      Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      String email = userDetails.getUsername();
      UserModel user = authService.findUserByEmail(email);
      InitDto initDto = initService.currentInit(user);

      return new ResponseEntity<>(initDto, HttpStatus.OK);
  }
  
}
