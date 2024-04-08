package com.server.server.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequestMapping("/auth")
@CrossOrigin
@RestController
public class authController {

  @Value("${secret.key}") private String secretKey;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestHeader("Authorization") String authHeader) {
      try{
        log.info("test");
        String credentials = decrypt(authHeader, secretKey);
        return ResponseEntity.ok(credentials);

        // String[] parts = credentials.split(":");
        // String email = parts[0];
        // String password = parts[1];

        //already user check service
        

        // return ResponseEntity.ok(email);
      }
      catch(Exception error){
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  private String decrypt(String encryptedData, String secretKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode("VPVCNMB6bfxcL9EkVsIIc+985W3H8Ea6eRIyQC+UiEs=");
        // System.out.println("키 길이 (바이트 단위): " + keyBytes.length); // 디버깅을 위함
        
        SecretKeySpec skeySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        return "end";
        // byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        // return new String(original);
    }

    @PostMapping("/test")
    public String postMethodName() {
        
        return "entity";
    }
    
  
}
