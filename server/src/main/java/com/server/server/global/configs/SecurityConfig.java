package com.server.server.global.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
      .requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
      .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
      )));
      // .formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/"));
  
    return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
}
