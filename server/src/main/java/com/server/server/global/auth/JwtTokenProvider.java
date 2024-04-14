package com.server.server.global.auth;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.server.server.dtos.TokenDto;
import com.server.server.status.auth.UnauthorizedTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * JwtTokenProvider는 토큰 생성, 토큰 복호화 및 정보 추출, 토큰 유효성 검증의 기능이 구현된 클래스.
 * @author rimsong
 * application.properties에 jwt.secret: 값을 넣어 설정 추가해준 뒤 사용합니다.
 * jwt.secret는 토큰의 암호화, 복호화를 위한 secret key로서 이후 HS256알고리즘을 사용하기 위해, 256비트보다 커야합니다.
 * 알파벳이 한 단어당 8bit니, 32글자 이상이면 됩니다! 너무 짧으면 에러가 뜹니다.
 */

@Slf4j
@Component
public class JwtTokenProvider {
	private final Key accessKey;
    private final Key refreshKey;

    public JwtTokenProvider(@Value("${jwt.access-key}") String accessSecretKey, @Value("${jwt.refresh-key}") String refreshSecretKey) {
        byte[] accessKeyBytes = Decoders.BASE64.decode(accessSecretKey);
        this.accessKey = Keys.hmacShaKeyFor(accessKeyBytes);

        byte[] refreshKeyBytes = Decoders.BASE64.decode(refreshSecretKey);
        this.refreshKey = Keys.hmacShaKeyFor(refreshKeyBytes);
    }
 
    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenDto createAllToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
 
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + 900000);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
 
        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 1209600000))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
 
        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDto createAccessToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
 
        long now = (new Date()).getTime();
        Date accessTokenExpiresIn = new Date(now + 900000);
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(accessKey, SignatureAlgorithm.HS256)
                .compact();
 
        return TokenDto.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .build();
    }

        public TokenDto createRefreshToken(Authentication authentication) {
        long now = (new Date()).getTime();
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 1209600000))
                .signWith(refreshKey, SignatureAlgorithm.HS256)
                .compact();
 
        return TokenDto.builder()
                .grantType("Bearer")
                .refreshToken(refreshToken)
                .build();
    }
 
    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
        public Authentication getAuthentication(String accessToken) {
            Claims claims = parseClaims(accessToken);
            // 'auth' 클레임의 존재와 비어 있지 않은지 확인
            String authClaims = claims.get("auth", String.class);
            if (authClaims == null || authClaims.isEmpty()) {
                throw new UnauthorizedTokenException("Unauthorized Token: 'auth' claim is missing or empty");
            }
            // 권한 정보를 권한 객체로 변환
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(authClaims.split(","))
                    .filter(StringUtils::hasText)
                    .map(String::trim)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            UserDetails principal = new User(claims.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principal, "", authorities);
        }

 
    // 토큰 정보를 검증하는 메서드
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // 보안 문제 또는 형식이 잘못된 JWT
            throw new RuntimeException("Invalid JWT: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // JWT 토큰이 만료됨
            throw new RuntimeException("Expired JWT: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT 형식
            throw new RuntimeException("Unsupported JWT: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // JWT 파싱 시 잘못된 인자 제공
            throw new RuntimeException("Invalid arguments provided to JWT parsing: " + e.getMessage());
        }
        return true;
    }
        public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(refreshKey).build().parseClaimsJws(token);
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            // 보안 문제 또는 형식이 잘못된 JWT
            throw new RuntimeException("Invalid JWT: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            // JWT 토큰이 만료됨
            throw new RuntimeException("Expired JWT: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            // 지원되지 않는 JWT 형식
            throw new RuntimeException("Unsupported JWT: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // JWT 파싱 시 잘못된 인자 제공
            throw new RuntimeException("Invalid arguments provided to JWT parsing: " + e.getMessage());
        }
        return true;
    }
 
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}