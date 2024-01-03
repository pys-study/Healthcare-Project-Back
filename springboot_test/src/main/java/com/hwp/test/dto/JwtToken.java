package com.hwp.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JwtToken {
    private String grantType; //jwt에 대한 인증 타입
    private String accessToken; //jwt 액세스 토큰
    private String refreshToken; //jwt 리프레시 토큰
}
