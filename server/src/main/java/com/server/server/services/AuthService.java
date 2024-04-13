package com.server.server.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.server.server.dtos.TokenDto;
import com.server.server.global.auth.JwtTokenProvider;
import com.server.server.global.status.UnauthorizedException;
import com.server.server.interfaces.IUserRepo;
import com.server.server.models.UserModel;
import com.server.server.status.auth.EmailAlreadyException;
import com.server.server.status.auth.LoadHtmlTemplateException;
import com.server.server.status.auth.PasswordNotMatchException;
import com.server.server.status.auth.UserByTokenException;
import com.server.server.status.auth.UserNotFoundException;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
  private final IUserRepo iUserRepo;
  private final BCryptPasswordEncoder passwordEncoder;
  private final JavaMailSender mailSender;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;
  private final JwtTokenProvider jwtTokenProvider;

  public Boolean isEmailAlreadyRegistered(String email){
    if(iUserRepo.existsByEmail(email)){
      throw new EmailAlreadyException("Email is already registered");
    }
    else{
      return false;
    }
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

  public UserModel findUserByToken(String token){
    Optional<UserModel> userOptional = this.iUserRepo.findUserByToken(token);
    if(!userOptional.isPresent()){
      throw new UserByTokenException("Fail find user by Token");
    }
      return userOptional.get();

  }

  public Boolean verificationComplete(UserModel user){

    if(user.getIsValid()){
      return false;
    }
    user.setIsValid(true);
    this.iUserRepo.save(user);

    return true;
  }

  public String loadCompleteVerificationHtml(){
    ClassPathResource resource = new ClassPathResource("templates/verificationComplete.html");
    String content = loadHtmlTemplate(resource);

    return content;
  }

  public UserModel findUserByEmail (String email) {
    Optional<UserModel> userOptional = this.iUserRepo.findUserByEmail(email);
    if(!userOptional.isPresent()){
      throw new UserNotFoundException("Fail find user by Id");
    }
      return userOptional.get();
  }

  public void verifyPassword (String password, UserModel userModel){
    if (!passwordEncoder.matches(password, userModel.getPassword())) {
      throw new PasswordNotMatchException("Password is Not Matched");
    }
  }

    /**
     * 1. 로그인 요청으로 들어온 ID, PWD 기반으로 Authentication 객체 생성
     * 2. authenticate() 메서드를 통해 요청된 Member에 대한 검증이 진행 => loadUserByUsername 메서드를 실행. 해당 메서드는 검증을 위한 유저 객체를 가져오는 부분으로써, 어떤 객체를 검증할 것인지에 대해 직접 구현
     * 3. 검증이 정상적으로 통과되었다면 인증된 Authentication객체를 기반으로 JWT 토큰을 생성
     */
    @Transactional
    public TokenDto login(String email, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
 
        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
 
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.createAllToken(authentication);
 
        return tokenDto;
    }

    public void isEmailValid(UserModel user){
      if(user.getIsValid()==false){
        throw new UnauthorizedException("UNAUTHORIZED");
      }
    }
}
