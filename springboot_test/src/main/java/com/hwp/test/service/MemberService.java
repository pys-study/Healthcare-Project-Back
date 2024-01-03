package com.hwp.test.service;

import com.hwp.test.dto.JwtToken;

public interface MemberService {
    public JwtToken signIn(String username, String password);
}
