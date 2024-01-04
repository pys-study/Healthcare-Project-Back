package com.hwpBackend.hwpSpring.controller;

import com.hwpBackend.hwpSpring.dto.JwtToken;
import com.hwpBackend.hwpSpring.dto.MemberDto;
import com.hwpBackend.hwpSpring.dto.SignInDto;
import com.hwpBackend.hwpSpring.dto.SignUpDto;
import com.hwpBackend.hwpSpring.entity.Member;
import com.hwpBackend.hwpSpring.exception.UserNotFoundException;
import com.hwpBackend.hwpSpring.repository.MemberRepository;
import com.hwpBackend.hwpSpring.service.MemberService;
import com.hwpBackend.hwpSpring.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository repository;

    private final MemberService memberService;

    // GET /members
    @GetMapping("/members")
    public List<Member> retrieveAllMember() {
        return repository.findAll();
    }

    // GET /members
    @GetMapping("/members/{id}")
    public Optional<Member> retrieveMember(@PathVariable(value = "id") String id) { // String인 경우 반드시 value값을 지정해줄 것
        Optional<Member> member = repository.findById(id);

        if (member.isEmpty()) throw new UserNotFoundException("id:" + id); // 존재하지 않는 사용자 예외처리
        return member;
    }

    // POST /members
    @PostMapping("/members")
    public ResponseEntity<Member> createMember(@RequestBody Member member) {

        Member savedMember = repository.save(member);

        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMember.getUserID())
                .toUri();

        return ResponseEntity.created(localtion).build();

    }

    // DELETE /members
    @DeleteMapping("/members/{id}")
    public void deleteMember(@PathVariable(value = "id") String id) { // String인 경우 반드시 value값을 지정해줄 것
        repository.deleteById(id);
    }

    @PostMapping("/members/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto) {
        String ID = signInDto.getUserID();
        String password = signInDto.getPassword();
        JwtToken jwtToken = memberService.signIn(ID, password);
        log.info("request username = {}, password = {}", ID, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/members/test")
    public String test() {
        return "success";
    }

    @PostMapping("/members/sign-up")
    public ResponseEntity<MemberDto> signUp(@RequestBody SignUpDto signUpDto) {
        MemberDto savedMemberDto = memberService.signUp(signUpDto);
        return ResponseEntity.ok(savedMemberDto);
    }
}
