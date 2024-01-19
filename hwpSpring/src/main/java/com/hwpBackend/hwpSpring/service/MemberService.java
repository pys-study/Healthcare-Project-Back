package com.hwpBackend.hwpSpring.service;

import com.hwpBackend.hwpSpring.dto.JwtToken;
import com.hwpBackend.hwpSpring.dto.MemberDto;
import com.hwpBackend.hwpSpring.dto.SignUpDto;
import org.springframework.transaction.annotation.Transactional;

public interface MemberService {
    public JwtToken signIn(String username, String password);

    public MemberDto signUp(SignUpDto signUpDto);
}
