package com.hwpBackend.hwpSpring.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class JwtAuthenticationController {

    private JwtEncoder jwtEncoder;

    public JwtAuthenticationController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/authenticate")
    public JwtResponse authenticate(Authentication authentication) { // 인증 객체를 이용해 생성한 JWT 토큰 받기
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        var claims = JwtClaimsSet       // JSON Web Token 이 전달한 클레임을 나타내는 JSON 객체
                .builder()              // 해당 객체의 builder
                .issuer("self")         // .iss(): 발행한 발행자 설정, 같은 애플리케이션이므로 "self"
                .issuedAt(Instant.now())// 시스템 시계에서 현재의 인스턴스를 획득
                .expiresAt(Instant.now().plusSeconds(60*30)) //  JWT 토큰이 만료되는 시간 설정
                .subject(authentication.getName()) // 주체
                .claim("scope", createScope(authentication)) // 특정 유저가 가진 권한
                .build();

        // claims로 부터 JwtEndoderParameters를 만든 후 인코딩, getTokenValue()를 통해 토큰 값 리턴
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private String createScope(Authentication authentication) { // 권한 취합
        return authentication.getAuthorities().stream()
                .map(a -> a.getAuthority()) // 각각의 권한을 a.getAuthority()에 매핑, String 리스트 형태로 변환
                .collect(Collectors.joining(" ")); // 공백으로 구분
    }

}

// 토큰과 함께 응답 리턴
record JwtResponse(String token) {
}