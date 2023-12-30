package com.hwpBackend.hwpSpring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicAuthenticationSecurityConfiguration {
    //스프링 시큐리티 필터 체인
    //모든 요청 인증
    //기본 인증
    //csrf 비활성화 - 세션 사용하지 않음

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //1. 프리플라이트에 대한 응답이 액세스 제어 체크를 통과하게 함
        //2. 기본 인증 url
        return http
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //프리플라이트 요청(OPTION 요청) 액세스 허용
                                        .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(
                        session -> session.sessionCreationPolicy
                                (SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}