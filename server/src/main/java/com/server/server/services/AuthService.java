package com.server.server.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.server.interfaces.IUserRepo;
import com.server.server.models.UserModel;
import com.server.server.status.auth.EmailAlreadyException;
import com.server.server.status.auth.LoadHtmlTemplateException;
import com.server.server.status.auth.UserByTokenException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {
  private final IUserRepo iUserRepo;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JavaMailSender mailSender;

  public Boolean isEmailAlreadyRegistered(String email){
    if(iUserRepo.existsByEmail(email)){
      throw new EmailAlreadyException("Email is already registered");
    }
    else{
      return false;
    }
  }

  public UUID createUuid() {
    return UUID.randomUUID();
  }

  public String encodedPassword (String password){
    return passwordEncoder.encode(password);
  }

  public String createNameByEmail(String email){
    String[] parts = email.split("@");
    return parts[0];
  }

  public void createUser(UUID token, String encode, String email, String name){
    UserModel user = new UserModel();

    user.setEmail(email);
    user.setPassword(encode);
    user.setPlan("basic");
    user.setName(name);
    user.setIsValid(false);
    user.setToken(token.toString());

    this.iUserRepo.save(user);
  }

  public void sendEmailVerification(String to, UUID token) throws IOException, MessagingException{

      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      ClassPathResource resource = new ClassPathResource("templates/emailVerificationTemplate.html");

      String content = loadHtmlTemplate(resource);
      content = String.format(content, token);

      helper.setTo(to);
      helper.setSubject("Kirtion Email Verification Service");
      helper.setText(content, true);

      mailSender.send(message);

  }

  public String loadHtmlTemplate(ClassPathResource resource) {
    try {
      String content = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
      return content;
    } catch (IOException error) {
      throw new LoadHtmlTemplateException("Failed to load email template: " + error.toString());
    }
  }

  public UserModel findByToken(String token){
    Optional<UserModel> userOptional = this.iUserRepo.findByToken(token);
    if(userOptional.isPresent()){
      UserModel user = userOptional.get();
      return user;
    }else{
      throw new UserByTokenException("Fail find user by Token");

    }
  }

  public void verificationComplete(UserModel user){
    user.setIsValid(true);
    this.iUserRepo.save(user);
  }

  public String loadCompleteVerificationHtml(){
    ClassPathResource resource = new ClassPathResource("templates/verificationComplete.html");
    String content = loadHtmlTemplate(resource);

    return content;
  }
  
}
