package com.hwpBackend.hwpSpring.config;

import com.hwpBackend.hwpSpring.filter.JwtAuthenticationFilter;
import com.hwpBackend.hwpSpring.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // REST API 이므로 basic auth 및 csrf 보안을 사용하지 않음
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                //JWT를 사용하기 때문에 세션을 사용하지 않음
                .sessionManagement( // 세선 해제
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS) // 세션 생성 정책 설정, 사용과 설정 둘 다 안하도록
                )
                .authorizeHttpRequests((authorizeRequests) -> {
                    authorizeRequests
                            // 해당 API에 대해서는 모든 요청을 허가
                            .requestMatchers("/members/sign-up").permitAll()
                            .requestMatchers("/members/sign-in").permitAll()
                            // USER 권한이 있어야 요청할 수 있음
                            .requestMatchers("/members/test").hasRole("USER")
                            // 이 밖에 모든 요청에 대해서 인증을 필요로 한다는 설정
                            .anyRequest().authenticated();
                })
                // JWT 인증을 위하여 직접 구현한 필터를 UsernamePasswordAuthenticationFilter 전에 실행
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
