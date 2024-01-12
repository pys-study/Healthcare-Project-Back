package com.hwpBackend.hwpSpring.filter;


import com.hwpBackend.hwpSpring.provider.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 1. Request Header에서 JWT 토큰 추출
        String accessToken = resolveAccessToken((HttpServletRequest) request);
        String refreshToken = resolveRefreshToken((HttpServletRequest) request);

        // 2. validateToken으로 유효성 검사
        if (accessToken != null) {
            // 3. 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장 -> 요청을 처리하는 동안 인증 정보가 유지된다
            if (jwtTokenProvider.validateToken(accessToken)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            // access token이 만료된 상황 | refresh token은 존재하는 상황
            else if (!jwtTokenProvider.validateToken(accessToken) && refreshToken != null){
//                boolean validateRefreshToken = jwtTokenProvider.validateToken(refreshToken); // 리프레시 토큰 검증
//                boolean isRefreshToken = jwtTokenProvider.existsRefreshToken(refreshToken); // 리프레시 토큰 저장소 유무 확인
//                if(validateRefreshToken && isRefreshToken){
//                    // 토큰 발급
//                    String newAccessToken = jwtTokenProvider.generateToken(authentication);
//                    Authentication authentication = jwtTokenProvider.getAuthentication(newAccessToken);
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
            }
        }
        chain.doFilter(request, response); // 다음 필터로 요청 전달
    }

    // Request Header에서 토큰 정보 추출
    private String resolveAccessToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    // RefreshToken 값 가져오기
    public String resolveRefreshToken(HttpServletRequest request) {
        if(request.getHeader("refreshToken") != null )
            return request.getHeader("refreshToken").substring(7);
        return null;
    }
}
