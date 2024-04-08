package com.server.server.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.server.dtos.AuthDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody AuthDto authDto) {
      try{
        //already user check service
        return ResponseEntity.ok(authDto.getEmail());
      }
      catch(Exception error){
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
  }
