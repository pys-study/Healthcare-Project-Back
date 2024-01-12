package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.dto.JwtToken;
import com.hwpBackend.hwpSpring.dto.MemberDto;
import com.hwpBackend.hwpSpring.dto.SignInDto;
import com.hwpBackend.hwpSpring.dto.SignUpDto;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import com.hwpBackend.hwpSpring.service.MemberService;
import com.hwpBackend.hwpSpring.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository repository;

    @PostMapping("/sign-in") // 모든 사용자에게 허옹
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String username = signInDto.getUsername();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(),
                jwtToken.getRefreshToken()); // Access Token을 발급받아 header에 넣은 후 "members/test"로 요청
        return jwtToken;
    }

    @PostMapping("/sign-up") // 모든 사용자에게 허옹
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(savedMemberDto);
    }

    @PostMapping("/test") // USER 권한을 가진 사용자에게 허용
    public String test() {
        return SecurityUtil.getCurrentUsername();
    } // 현재 요청을 보낸 회원의 username을 간단하게 얻을 수 있다.

    @GetMapping("/getUser")
    public Optional<Member> getUser() {
        return repository.findByUsername(SecurityUtil.getCurrentUsername());
    }

//    @GetMapping("/all")
//    public Member showMembers(){
//        return
//    }

}
